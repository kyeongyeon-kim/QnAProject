package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfPw;
	private JButton btnFind;
	private JButton btnLogin;
	private JButton btnJoin;

	private void tfSetting(JTextField j) {
		j.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		j.setBackground(SystemColor.inactiveCaptionBorder);
		j.setForeground(Color.DARK_GRAY);
		contentPane.add(j);
		j.setColumns(20);
	}
	
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 456);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		textPane.setText("로그인");
		textPane.setEditable(false);
		textPane.setBounds(115, 64, 115, 39);
		contentPane.add(textPane);
		
		tfId = new JTextField("아이디");
		tfId.setBounds(53, 141, 220, 39);
		tfSetting(tfId);
		
		tfPw = new JTextField("비밀번호");
		tfPw.setBounds(53, 190, 220, 39);
		tfSetting(tfPw);
		
		btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnLogin.setBackground(new Color(96,182,230));
		btnLogin.setBorderPainted(false);
		btnLogin.setForeground(Color.white);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(53, 252, 220, 39);
		contentPane.add(btnLogin);
		
		btnJoin = new JButton("회원가입");
		btnJoin.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		btnJoin.setBackground(Color.WHITE);
		btnJoin.setBorderPainted(false);
		btnJoin.setBounds(74, 301, 81, 23);
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JoinDialog dialog = new JoinDialog();
				dialog.setVisible(true);
			}
		});
		contentPane.add(btnJoin);
		
		btnFind = new JButton("ID/PW 찾기");
		btnFind.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		btnFind.setBorderPainted(false);
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFind.setBackground(Color.WHITE);
		btnFind.setBounds(152, 301, 95, 23);
		contentPane.add(btnFind);
	}
}
