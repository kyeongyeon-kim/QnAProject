package lobby;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LobbyServiceToolImpl implements LobbyServiceTool {

	@Override
	public String[] makeUserinfoArr(ResultSet rs) throws SQLException {
		String[] inputStr = new String[4];
		for (int i = 0; i < inputStr.length; i++) {
			inputStr[i] = rs.getString(i + 1);
		}
		return inputStr;
	}

}
