package lobby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import object.Attacker;
import object.User;

public class LobbyServiceToolImpl implements LobbyServiceTool {

	@Override
	public String[] makeUserinfoArr(ResultSet rs) throws SQLException {
		String[] inputStr = new String[4];
		for (int i = 0; i < inputStr.length; i++) {
			inputStr[i] = rs.getString(i + 1);
		}
		return inputStr;
	}

	@Override
	public List<Integer> makeChoiceList(List<Integer> list, PreparedStatement stmt) throws SQLException {
		try (ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				int missonChoice = rs.getInt(1);
				list.add(missonChoice);
			}
		}
		return list;
	}
	
	@Override
	public List<String> makeAttackerList(List<String> list, PreparedStatement stmt) throws SQLException {
		try (ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String attackerName = rs.getString(2);
				list.add(attackerName);
			}
		}
		return list;
	}

	@Override
	public Attacker attackerCaculationScore(User user, String attacker, Connection conn) throws SQLException {
		String sql2 = "SELECT choice FROM answer WHERE defender = ? AND attacker = ?";
		LobbyServiceImpl lobbyService = new LobbyServiceImpl(new LobbyServiceToolImpl());
		List<Integer> missionList = lobbyService.makeMissonList(user);
		List<Integer> answerList = new ArrayList<>();

		try (PreparedStatement stmt = conn.prepareStatement(sql2)) {
			stmt.setString(1, user.getId());
			stmt.setString(2, attacker);

			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					int answer = rs.getInt(1);
					answerList.add(answer);
				}
//				System.out.println(user.getId() + " 미션리스트 사이즈 : " + missionList.size());
//				System.out.println(missionList);
//				System.out.println(attacker + " 답변리스트 사이즈 : " + answerList.size());
//				System.out.println(answerList);
				
				int count = 0;
				for (Integer answer : answerList) {
					if (missionList.contains(answer)) {
						count++;
					}
				}
//				System.out.println("점수 : " + count);
				return new Attacker(attacker, count * 10);
			}
		}

	}

	@Override
	public Attacker myAttackCaculationScore(String defenderId, String myId, Connection conn) throws SQLException {
		String sql = "SELECT choice FROM mission WHERE id = ?";
		String sql2 = "SELECT choice FROM answer WHERE defender = ? AND attacker = ?";
		List<Integer> missionList = new ArrayList<>();
		List<Integer> answerList = new ArrayList<>();

		try (PreparedStatement stmt = conn.prepareStatement(sql);
				PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
			stmt.setString(1, defenderId);
			stmt2.setString(1, defenderId);
			stmt2.setString(2, myId);

			try (ResultSet rs = stmt.executeQuery(); 
					ResultSet rs2 = stmt2.executeQuery()) {

				while (rs.next()) {
					int mission = rs.getInt(1);
					missionList.add(mission);
				}
				System.out.println(defenderId + " 미션리스트 사이즈 : " + missionList.size());

				while (rs2.next()) {
					int answer = rs2.getInt(1);
					answerList.add(answer);
				}
				System.out.println(myId + " 답변리스트 사이즈 : " + answerList.size());
				
				int count = 0;
				for (Integer answer : answerList) {
					if (missionList.contains(answer)) {
						count++;
					}
				}
				System.out.println(defenderId + "가 맞힌 문제 개수: " + count);
				return new Attacker(defenderId, count * 10);
			}
		}
	}
}
