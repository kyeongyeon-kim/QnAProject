package game;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import lobby.LobbyFrame;
import lobby.LobbyServiceImpl;
import lobby.LobbyServiceToolImpl;
import object.Attacker;
import object.User;
import ranking.UserRankDialog;
import style.ImageServiceImpl;
import style.ImageServiceToolImpl;

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
		ImageServiceImpl isi = new ImageServiceImpl(new ImageServiceToolImpl());
		if (user.getGender().equals("남")) {
			secondHalfList = score >= 60 ? isi.getLikeAbilityWoman() : isi.failedLikeAbilityWoman();
		} else {
			secondHalfList = score >= 60 ? isi.getLikeAbilityMan() : isi.failedLikeAbilityMan();
		}
		for (Image secondHalf : secondHalfList) {
			secondHalfImages.add(secondHalf);
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
}
