package lobby;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LobbyServiceTool {
	public String[] makeUserinfoArr(ResultSet rs) throws SQLException;
}
