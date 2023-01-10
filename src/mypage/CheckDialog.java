package mypage;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CheckDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	
	public static void main(String[] args) {
		try {
			CheckDialog dialog = new CheckDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public CheckDialog() {
		setBounds(100, 100, 292, 193);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(74, 83, 134, 39);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		JLabel lblNewLabel = new JLabel("탈퇴되었습니다.");
		lblNewLabel.setBounds(36, 34, 210, 39);
		contentPanel.add(lblNewLabel);
	}
}