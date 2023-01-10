package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import dbutil.ConnectionProvider;
import lobby.LobbyFrame;
import lobby.LobbyServiceImpl;
import lobby.LobbyServiceToolImpl;
import object.Attacker;
import object.User;
import ranking.UserRankDialog;
import style.ImageServiceImpl;
import style.ImageServiceToolImpl;
import style.Paths;

public class GameModeServiceImpl implements GameModeService {
	private GameModeServiceTool gmst;

	public GameModeServiceImpl(GameModeServiceTool gmst) {
		super();
		this.gmst = gmst;
	}

	@Override
	public List<Image> makeFirstHalfList(User user) {
		List<Image> firstHalfImages = new ArrayList<>();
		ImageServiceImpl isi = new ImageServiceImpl(new ImageServiceToolImpl());
		List<Image> introList = isi.readIntro();
		List<Image> firstHalfList = user.getGender().equals("남") ? isi.readKatalkMan() : isi.readKatalkWoman();
		for (Image intro : introList) {
			firstHalfImages.add(intro);
		}

		for (Image firstHalf : firstHalfList) {
			firstHalfImages.add(firstHalf);
		}

		return firstHalfImages;
	}

	@Override
	public List<Image> makeSecondHalfList(User user, int score) {
		List<Image> secondHalfImages = new ArrayList<>();
		List<Image> secondHalfList = new ArrayList<>();
		int passScore = 0;
		ImageServiceImpl isi = new ImageServiceImpl(new ImageServiceToolImpl());
		if (user.getGender().equals("남")) {
			secondHalfList = score >= passScore ? isi.getLikeAbilityMan() : isi.failedLikeAbilityMan();
		} else {
			secondHalfList = score >= passScore ? isi.getLikeAbilityWoman() : isi.failedLikeAbilityWoman();
		}
		for (Image secondHalf : secondHalfList) {
			secondHalfImages.add(secondHalf);
		}
		if (score >= passScore) {
			for (Image date : isi.theDayWeMeet()) {
				secondHalfImages.add(date);
			}
		} else {
			for (Image gameOver : isi.gameOver()) {
				secondHalfImages.add(gameOver);
			}
		}
		return secondHalfImages;
	}

	@Override
	public void decideSecondHalf(User attacker, List<Image> resultImages, int score) {
		List<Image> secondHalfList = makeSecondHalfList(attacker, score);
		for (Image secondHalf : secondHalfList) {
			resultImages.add(secondHalf);
		}
	}

	@Override
	public void gameComplete(User user, User defender) {
		UserRankDialog urd = new UserRankDialog(defender);
		LobbyServiceImpl lsi = new LobbyServiceImpl(new LobbyServiceToolImpl());
		List<Attacker> attackerList = lsi.makeAttackerList(defender);
		lsi.setUserRanking(urd, attackerList);
		for (int i = 0; i < urd.getModel().getRowCount(); i++) {
			String attackerId = (String) urd.getModel().getValueAt(i, 1);
			if (user.getId().equals(attackerId)) {
				// 게임이 끝난 후 랭킹 화면에서 자신의 행이 하이라이트 되는 기능 구현 예정
			}
		}
		urd.setVisible(true);
		LobbyFrame lf = new LobbyFrame(user);
		lf.setVisible(true);
	}

	@Override
	public int selectByOption(String option) {
		String sql = "SELECT choice FROM exam_option WHERE `option` = ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, option);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int choice = rs.getInt(1);
					return choice;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void dataTransferToDB(User user, User defender, GameFrame gameFrame) {
		if (isUserPlayedGameBefore(user, defender)) {
			gmst.deleteAnswer(user, defender);
		}
		List<Integer> choiceList = gameFrame.getChoiceList();
		System.out.println(choiceList);
		String sql = "INSERT INTO answer VALUES (?, ?, ?);";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			for (Integer option : choiceList) {
				stmt.setString(1, defender.getId());
				stmt.setString(2, user.getId());
				stmt.setInt(3, option);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isUserPlayedGameBefore(User user, User defender) {
		String sql = "SELECT EXISTS "
				+ "(SELECT defender, attacker FROM answer GROUP BY attacker HAVING defender = ? AND attacker = ?)"
				+ " AS `isNOTNULL`;";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, defender.getId());
			stmt.setString(2, user.getId());
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int answer = rs.getInt(1);
					if (answer == 1) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public JLabel setTextByImageIndex(GameFrame gameFrame) {
		JLabel lbl = new JLabel();
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		int currentImageIndex = gameFrame.getCurrentImageIndex();
		if (currentImageIndex == 0) {
			lbl.setText(gameFrame.getUser().getId());
			lbl.setBounds(200, 300, 400, 300);
			lbl.setFont(font);
			gameFrame.getLabel().add(lbl);
		}
		return lbl;
	}
}
