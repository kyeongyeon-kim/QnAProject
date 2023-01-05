package lobby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import object.Attacker;

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
	public Attacker attackerCaculationScore(String id, String attacker, Connection conn) throws SQLException {
		String sql = "SELECT choice FROM mission WHERE id = ?";
		String sql2 = "SELECT choice FROM answer WHERE attacker = ?";
		List<Integer> missionList = new ArrayList<>();
		List<Integer> answerList = new ArrayList<>();

		try (PreparedStatement stmt = conn.prepareStatement(sql);
				PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
			stmt.setString(1, id);
			stmt2.setString(1, attacker);

			try (ResultSet rs = stmt.executeQuery();
				ResultSet rs2 = stmt2.executeQuery()) {

				while (rs.next()) {
					int mission = rs.getInt(1);
					missionList.add(mission);
				}

				while (rs2.next()) {
					int answer = rs2.getInt(1);
					answerList.add(answer);
				}

				int count = 0;
				for (int i = 0; i < missionList.size(); i++) {
					if (missionList.get(i) == answerList.get(i)) {
						count++;
					}
				}
				return new Attacker(attacker, count * 10);
			}

		}

	}
}
