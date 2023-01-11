package login;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import lobby.LobbyFrame;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame implements FocusListener, KeyListener {
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfPw;
	private JPasswordField pfPw;
	private JLabel lblResult;
	private JButton btnFind;
	private JButton btnLogin;
	private JButton btnJoin;
	private JoinDialog joinDialog;
	private String pw;
	private char[] charPw;

	public LoginFrame(Login login) {
		charPw = new char[0];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 230, 350, 460);
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

		JLabel lblId = new JLabel("아이디");
		lblId.setBounds(61, 130, 100, 32);
		lblSetting(lblId);
		tfId = new JTextField("");
		tfId.setBounds(58, 159, 220, 33);
		tfSetting(tfId);

		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setBounds(61, 195, 100, 32);
		lblSetting(lblPw);
		pfPw = new JPasswordField("");
		pfPw.setBounds(58, 224, 220, 33);
		tfSetting(pfPw);

		lblResult = new JLabel("");
		lblResult.setBounds(59, 260, 220, 39);
		lblResult.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lblResult.setBorder(null);
		lblResult.setForeground(Color.RED);
		contentPane.add(lblResult);

		btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnLogin.setBackground(new Color(96, 182, 230));
		btnLogin.setBorderPainted(false);
		btnLogin.setForeground(Color.white);
		btnLogin.setBounds(58, 292, 220, 39);
		btnLogin.addKeyListener(this);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectLogin(login);
			}
		});

		contentPane.add(btnLogin);

		btnJoin = new JButton("회원가입");
		btnJoin.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		btnJoin.setBackground(Color.WHITE);
		btnJoin.setBorderPainted(false);
		btnJoin.setBounds(79, 338, 81, 23);
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				joinDialog = new JoinDialog();
				joinDialog.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnJoin);

		btnFind = new JButton("비밀번호찾기");
		btnFind.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		btnFind.setBorderPainted(false);
		btnFind.setBackground(Color.WHITE);
		btnFind.setBounds(161, 338, 100, 23);
		contentPane.add(btnFind);
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FindPwDialog().setVisible(true);
			}
		});
	}

	// 비밀번호값 구하기
	private String getPassword(JPasswordField pfPw) {
		char[] c = pfPw.getPassword();
		String pw = "";
		for (int i = 0; i < c.length; i++) {
			pw += c[i];
		}
		return pw;
	}

	// 라벨 설정
	private void lblSetting(JLabel lbl) {
		lbl.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lbl.setBackground(Color.white);
		lbl.setForeground(Color.DARK_GRAY);
		contentPane.add(lbl);
	}

	// 텍스트필드 설정
	public void tfSetting(JTextField j) {
		j.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		j.setBackground(new Color(240, 241, 242));
		j.setBorder(null);
		j.setForeground(new Color(88, 89, 91));
		j.setBorder(new EmptyBorder(0, 7, 0, 7));
		j.setColumns(20);
		j.addKeyListener(this);
		j.addFocusListener(this);
		contentPane.add(j);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == tfId && tfId.getText().equals("아이디")) {
			tfId.setText("");
		} else if (e.getSource() == pfPw && getPassword(pfPw).equals("비밀번호")) {
			pfPw.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == tfId && tfId.getText().equals("")) {
			tfId.setText("아이디");
		} else if (e.getSource() == pfPw && getPassword(pfPw).equals("")) {
			pfPw.setText("비밀번호");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			btnLogin.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void selectLogin(Login login) {
		int result = login.checkLogin(tfId.getText(), getPassword(pfPw));
		if (result == login.loginComplete) {
			// 로그인 완료
			lblResult.setText("");
			LobbyFrame frame = new LobbyFrame(login.loginUser(tfId.getText()));
			frame.setVisible(true);
			dispose();
		} else if (result == login.loginFailByPw) {
			lblResult.setText("비밀번호가 일치하지 않습니다.");
		} else if (result == login.loginFailById) {
			lblResult.setText("존재하지 않는 아이디입니다.");
		}
	}

	public void makePf() {
		pfPw.setBounds(58, 190, 220, 39);
		pfPw.addKeyListener(this);
		pfPw.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		pfPw.setBackground(new Color(240, 241, 242));
		pfPw.setBorder(null);
		pfPw.setForeground(new Color(88, 89, 91));
		pfPw.setBorder(new EmptyBorder(0, 7, 0, 7));
		pfPw.setColumns(20);
		pfPw.addFocusListener(this);
		contentPane.add(pfPw);
	}
}
