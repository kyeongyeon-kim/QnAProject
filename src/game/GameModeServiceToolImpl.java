package game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.ConnectionProvider;
import object.User;

public class GameModeServiceToolImpl implements GameModeServiceTool {

	@Override
	public void deleteAnswer(User user, User defender) {
		String sql = "DELETE FROM answer WHERE defender = ? AND attacker = ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, defender.getId());
			stmt.setString(1, user.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
