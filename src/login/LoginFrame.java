package login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame implements FocusListener {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfPw;
	private JButton btnFind;
	private JButton btnLogin;
	private JButton btnJoin;

	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 250, 350, 447);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		textPane.setText("로그인");
		textPane.setEditable(false);
		textPane.setBounds(118, 64, 115, 39);
		contentPane.add(textPane);
		
		tfId = new JTextField("아이디");
		tfId.setBounds(58, 141, 220, 39);
		tfSetting(tfId);
		
		tfPw = new JTextField("비밀번호");
		tfPw.setBounds(58, 190, 220, 39);
		tfSetting(tfPw);
		
		btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnLogin.setBackground(new Color(96,182,230));
		btnLogin.setBorderPainted(false);
		btnLogin.setForeground(Color.white);
		btnLogin.setBounds(58, 252, 220, 39);
		contentPane.add(btnLogin);
		
		btnJoin = new JButton("회원가입");
		btnJoin.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		btnJoin.setBackground(Color.WHITE);
		btnJoin.setBorderPainted(false);
		btnJoin.setBounds(79, 301, 81, 23);
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
		btnFind.setBackground(Color.WHITE);
		btnFind.setBounds(155, 301, 95, 23);
		contentPane.add(btnFind);
	}
	
	// 텍스트필드 설정
	public void tfSetting(JTextField j) {
		j.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		j.setBackground(new Color(240,241,242));
		j.setBorder(null);
		j.setForeground(new Color(88,89,91));
		j.setBorder(new EmptyBorder(0, 7, 0, 7));
		j.setColumns(20);
		j.addFocusListener(this);
		contentPane.add(j);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == tfId && tfId.getText().equals("아이디")) {
			tfId.setText("");
		} else if (e.getSource() == tfPw && tfPw.getText().equals("비밀번호")) {
			tfPw.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == tfId && tfId.getText().equals("")) {
			tfId.setText("아이디");
		} else if (e.getSource() == tfPw && tfPw.getText().equals("")) {
			tfPw.setText("비밀번호");
		}
	}
}
