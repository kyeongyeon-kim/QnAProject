package lobby;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public interface LobbyService {
	public void readUserinfo(DefaultTableModel model);
	public void infomationFiltering(String getText, TableRowSorter<TableModel> sorter);
	public boolean isRowSelected(JTable table);
}
