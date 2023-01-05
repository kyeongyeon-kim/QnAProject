package mypage;



import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import login.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MypageDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTable tableAttack;
	private JTable tableDefend;
	private JPanel attackDefendPanel;

	public MypageDialog(User user) {
		setModal(true);
		setBounds(555, 175, 602, 567);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblMypage = new JLabel("마이페이지");
		lblMypage.setFont(new Font("맑은 고딕", Font.BOLD, 31));
		lblMypage.setBounds(216, 20, 166, 61);
		contentPanel.add(lblMypage);
		
		attackDefendPanel = new JPanel();
		attackDefendPanel.setBackground(new Color(240, 248, 253));
		attackDefendPanel.setBounds(20, 90, 545, 345);
		contentPanel.add(attackDefendPanel);
		attackDefendPanel.setLayout(null);
		
		
		JTextField tfAttack = new JTextField("내가 공략한 사람");
		tfSetting(tfAttack);
		tfAttack.setBounds(75, 12, 130, 35);
		
		JScrollPane attackPane = new JScrollPane();
		attackPane.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		attackPane.setBounds(17, 55, 245, 275);
		attackDefendPanel.add(attackPane);
		
		String[] header = { "랭킹", "아이디", "점수" };
		
		tableAttack = new JTable();
		tableAttack.setModel(new DefaultTableModel(header,10
				));
		tableAttack.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableAttack.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		attackPane.setViewportView(tableAttack);
		
		JTextField tfDefend = new JTextField("나를 공략한 사람");
		tfSetting(tfDefend);
		tfDefend.setBounds(343, 12, 130, 35);
		
		JScrollPane defendPane = new JScrollPane();
		defendPane.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		defendPane.setBounds(282, 55, 245, 275);
		attackDefendPanel.add(defendPane);
	
		tableDefend = new JTable();
		tableDefend.setModel(new DefaultTableModel(header, 0));
		tableDefend.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableDefend.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		defendPane.setViewportView(tableDefend);
		
		JButton btnChangeInfo = new JButton("개인 정보 수정");
		btnSetting(btnChangeInfo);
		btnChangeInfo.setBounds(62, 456, 130, 35);
		btnChangeInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangeInfo(user);
			}
		});
		
		JButton btnChangeAns = new JButton("질문지 답변 수정");
		btnSetting(btnChangeAns);
		btnChangeAns.setBounds(230, 456, 130, 35);
		
		JButton btnBefore = new JButton("이전화면");
		btnSetting(btnBefore);
		btnBefore.setBounds(392, 456, 130, 35);

	}
	
	// 버튼 설정
	private void btnSetting(JButton btn) {
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btn.setBorderPainted(false);
		btn.setBackground(new Color(96,182,230));
		btn.setForeground(Color.white);
		btn.addActionListener(this);
		contentPanel.add(btn);
	}
	
	// 텍스트필드 설정
	private void tfSetting(JTextField j) {
		j.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		j.setBackground(new Color(96,182,230));
		j.setForeground(Color.white);
		j.setEditable(false);
		j.setBorder(new EmptyBorder(0, 18, 0, 0));
		attackDefendPanel.add(j);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("이전화면")) {
			dispose();
		} 
	}
}
