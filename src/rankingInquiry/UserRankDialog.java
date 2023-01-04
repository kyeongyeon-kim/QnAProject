package rankingInquiry;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.GarbageCollectorMXBean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class UserRankDialog extends JDialog implements ActionListener {
	private showRankService showRank;
	
	public UserRankDialog(String str) {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setModal(false);
		
		String resultText = "<HTML><body style='text-align:center;'>" + str + "님 <br>Attacker 랭킹</body></HTML>";
		JLabel rankingLabel = new JLabel(resultText);
		rankingLabel.setBackground(Color.BLACK);
		rankingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rankingLabel.setForeground(Color.BLACK);
		rankingLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		rankingLabel.setBounds(21, 10, 261, 50);
		getContentPane().add(rankingLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		String[] header = { "랭킹", "아이디", "점수" };
		DefaultTableModel model = new DefaultTableModel(header, 30);
		JTable table = new JTable(model);
		table.setPreferredSize(new Dimension(270, 450));
		mainPanel.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		mainPanel.add(scrollPane, "Center");
		mainPanel.setBounds(12, 70, 270, 455);
		getContentPane().add(mainPanel);

		JButton closeBtn = new JButton("확인");
		closeBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		closeBtn.setForeground(new Color(255, 255, 255));
		closeBtn.setBounds(92, 535, 97, 23);
		closeBtn.setBackground(new Color(92, 180, 229));
		closeBtn.addActionListener(this);
		getContentPane().add(closeBtn);
		
		setSize(300, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
