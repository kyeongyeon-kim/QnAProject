package lobby;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dbutil.ConnectionProvider;

public class LobbyServiceImpl implements LobbyService {
	private LobbyServiceTool lst;

	public LobbyServiceImpl(LobbyServiceTool lst) {
		super();
		this.lst = lst;
	}

	@Override
	public void readUserinfo(DefaultTableModel model) {
		String sql = "SELECT name, gender, id, mbti FROM userinfo";
		try (Connection conn = ConnectionProvider.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				String[] inputStr = lst.makeUserinfoArr(rs);
				model.addRow(inputStr);
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
}
