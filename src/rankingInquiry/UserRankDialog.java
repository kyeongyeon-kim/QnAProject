package rankingInquiry;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.lang.management.GarbageCollectorMXBean;
import java.awt.Color;
import javax.swing.JButton;

public class UserRankDialog extends JDialog {
	private showRankService showRank;
	
	public UserRankDialog() {
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setModal(false);

		JLabel rankingLabel = new JLabel("000님 랭킹");
		rankingLabel.setBackground(Color.BLACK);
		rankingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rankingLabel.setForeground(Color.BLACK);
		rankingLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		rankingLabel.setBounds(92, 28, 108, 19);
		getContentPane().add(rankingLabel);

		rankingListPanel mainPanel = new rankingListPanel();
		mainPanel.setBounds(12, 70, 270, 455);
		getContentPane().add(mainPanel);

		JButton closeBtn = new JButton("확인");
		closeBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		closeBtn.setForeground(new Color(255, 255, 255));
		closeBtn.setBounds(92, 535, 97, 23);
		closeBtn.setBackground(new Color(92, 180, 229));
		getContentPane().add(closeBtn);
		
		setSize(300, 600);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new UserRankDialog().setVisible(true);
			}
		});
	}
}
