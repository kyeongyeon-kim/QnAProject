package mbti;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;
import javax.swing.JLabel;

public class MbtiDialog extends JDialog implements ItemListener {

	private final JPanel contentPanel = new JPanel();
	JToggleButton[][] btnMbti = new JToggleButton[4][4];
	private MbtiImageManager im = new MbtiImageManager();
	private JButton btnNo;
	private JButton btnOk;

	public MbtiDialog(JTextField tfMbti) {
		setModal(true);
		setBounds(280, 100, 867, 730);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		for (int i = 0; i < btnMbti.length; i++) {
			for (int j = 0; j < btnMbti[i].length; j++) {
				JToggleButton btn = new JToggleButton();
				btn.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				btn.setBackground(Color.white);
				System.out.println(i* 4 + j);
				btn.setIcon(new ImageIcon(im.getImages().get(i * 4 + j)));
				btn.setSelectedIcon(new ImageIcon(im.getImagesSelect().get(i * 4 + j)));
				btn.setBorderPainted(false);
				btn.setBounds(33 + (200 * j), 90 + (130 * i), 187, 120);
				btn.addItemListener(this);
				contentPanel.add(btn);
				btnMbti[i][j] = btn;
			}
		}
		
		JLabel lblMbti = new JLabel("MBTI");
		lblMbti.setFont(new Font("맑은 고딕", Font.BOLD, 32));
		lblMbti.setBounds(333, 20, 200, 45);
		contentPanel.add(lblMbti);
		
		JLabel lblMbti2 = new JLabel("성격 유형");
		lblMbti2.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		lblMbti2.setBounds(426, 22, 500, 45);
		contentPanel.add(lblMbti2);
		
		btnNo = new JButton("선택안함");
		btnNo.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnNo.setBounds(330, 625, 88, 33);
		btnNo.setBackground(Color.white);
		contentPanel.add(btnNo);
		btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfMbti.setText("선택안함");
				dispose();
			}
		});
		
		btnOk = new JButton("확인");
		btnOk.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnOk.setBounds(434, 625, 88, 33);
		btnOk.setBackground(Color.white);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfMbti.setText(getSelectMbti(tfMbti));
				dispose();
			}
		});
		contentPanel.add(btnOk);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == 1) {
			for (int i = 0; i < btnMbti.length; i++) {
				for (int j = 0; j < btnMbti[i].length; j++) {
					if (e.getSource() == btnMbti[i][j]) {
						btnMbti[i][j].setSelected(true);
					} else {
						btnMbti[i][j].setSelected(false);
					}
				}
			}
		}
	}
	public String getSelectMbti(JTextField tfMbti) {
		for (int i = 0; i < btnMbti.length; i++) {
			for (int j = 0; j < btnMbti[i].length; j++) {
				if (btnMbti[i][j].isSelected()) {
					return im.getMbti()[i * 4 + j]; // im의 Mbti 배열의 값 가져오기
				}
			}
		}
		return tfMbti.getText();
	}
}
