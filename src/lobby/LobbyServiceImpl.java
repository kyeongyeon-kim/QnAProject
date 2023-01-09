package lobby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.x.protobuf.MysqlxCrud.CollectionOrBuilder;

import dbutil.ConnectionProvider;
import mypage.MypageDialog;
import object.Attacker;
import object.User;
import ranking.UserRankDialog;

public class LobbyServiceImpl implements LobbyService {
	private LobbyServiceTool lst;

	public LobbyServiceImpl(LobbyServiceTool lst) {
		super();
		this.lst = lst;
	}

	@Override
	public void readUserinfo(DefaultTableModel model, User user) {
		String sql = "SELECT name, gender, id, mbti FROM userinfo WHERE id <> ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getId());

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					String[] inputStr = lst.makeUserinfoArr(rs);
					model.addRow(inputStr);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void infomationFiltering(String getText, TableRowSorter<TableModel> sorter) {
		if (getText.length() == 0) {
			sorter.setRowFilter(null);
		} else {
			try {
				sorter.setRowFilter(RowFilter.regexFilter(getText));
			} catch (PatternSyntaxException pse) {
				System.out.println("Bad regex pattern");
			}
		}
	}

	@Override
	public boolean isRowSelected(JTable table) {
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.isRowSelected(i)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Attacker> makeAttackerList(User user) {
		String sql2 = "SELECT defender, attacker FROM answer GROUP BY attacker HAVING defender = ?";
		List<String> attackerNameList = new ArrayList<>();
		List<Attacker> attackerList = new ArrayList<>();
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
			stmt2.setString(1, user.getId());

			attackerNameList = lst.makeAttackerList(attackerNameList, stmt2);

			for (String attacker : attackerNameList) {
				Attacker a = lst.attackerCaculationScore(user, attacker, conn);
				attackerList.add(a);
			}

			Collections.sort(attackerList, new Comparator<Object>() {
				@Override
				public int compare(Object o1, Object o2) {
					if (((Attacker) o1).getScore() == ((Attacker) o2).getScore()) {
						return ((CollectionOrBuilder) o1).getName().compareTo(((CollectionOrBuilder) o2).getName());
					}
					return ((Attacker) o2).getScore() - ((Attacker) o1).getScore();
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attackerList;
	}

	@Override
	public List<Attacker> makeMyAttackList(User user) { // 파라미터 user는 로그인한 사람
		String sql2 = "SELECT attacker, defender FROM answer GROUP BY defender HAVING attacker = ?";
		List<String> attackerNameList = new ArrayList<>();
		List<Attacker> attackerList = new ArrayList<>();
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
			stmt2.setString(1, user.getId());

			attackerNameList = lst.makeAttackerList(attackerNameList, stmt2);

			for (String attacker : attackerNameList) {
				Attacker a = lst.myAttackCaculationScore(attacker, (String) user.getId(), conn);
				attackerList.add(a);
			}

			Collections.sort(attackerList, new Comparator<Object>() {
				@Override
				public int compare(Object o1, Object o2) {
					if (((Attacker) o1).getScore() == ((Attacker) o2).getScore()) {
						return ((CollectionOrBuilder) o1).getName().compareTo(((CollectionOrBuilder) o2).getName());
					}
					return ((Attacker) o2).getScore() - ((Attacker) o1).getScore();
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attackerList;
	}
	
	@Override
	public List<Integer> makeMissonList(User user) {
		String sql = "SELECT choice FROM mission WHERE id = ?";
		List<Integer> missionList = new ArrayList<>();
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getId());

			missionList = lst.makeChoiceList(missionList, stmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return missionList;
	}

	@Override
	public void setUserRanking(UserRankDialog urd, List<Attacker> attackerList) {
		Object[] inputInfo = new Object[3];
		int rankingNum = 1;
		for (Attacker attacker : attackerList) {
			inputInfo[0] = rankingNum;
			inputInfo[1] = attacker.getName();
			inputInfo[2] = attacker.getScore();
			urd.getModel().addRow(inputInfo);
			rankingNum++;
		}
	}
	
	@Override
	public void setDefendRanking(MypageDialog mypageDialog, List<Attacker> list) {
		Object[] inputInfo = new Object[3];
		int rankingNum = 1;
		for (Attacker attacker : list) {
			inputInfo[0] = rankingNum;
			inputInfo[1] = attacker.getName();
			inputInfo[2] = attacker.getScore();
			mypageDialog.getModelDefend().addRow(inputInfo);
			rankingNum++;
		}
	}

	@Override
	public void setAttackRanking(MypageDialog mypageDialog, List<Attacker> list) {
		Object[] inputInfo = new Object[3];
		int rankingNum = 1;
		for (Attacker attacker : list) {
			inputInfo[0] = rankingNum;
			inputInfo[1] = attacker.getName();
			inputInfo[2] = attacker.getScore();
			mypageDialog.getModelAttack().addRow(inputInfo);
			rankingNum++;
		}
	}

}
