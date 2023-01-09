package lobby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import object.Attacker;

public interface LobbyServiceTool {
	public String[] makeUserinfoArr(ResultSet rs) throws SQLException;
	public List<Integer> makeChoiceList(List<Integer> list, PreparedStatement stmt) throws SQLException;
	public List<String> makeAttackerList(List<String> list, PreparedStatement stmt) throws SQLException;
	public Attacker attackerCaculationScore(String id, String attacker, Connection conn) throws SQLException;
	public Attacker myAttackCaculationScore(String defenderId, String myId, Connection conn) throws SQLException;
}
