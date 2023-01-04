package rankingInquiry;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class rankingListPanel extends JPanel {
	
	public rankingListPanel() {
		setPreferredSize(new Dimension(270, 450));
		setLayout(new BorderLayout());
		
		
		String[] header = { "랭킹", "아이디", "점수" };
		DefaultTableModel model = new DefaultTableModel(header, 30);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, "Center");
	}
}
