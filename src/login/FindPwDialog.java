package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import exam.ExamFrame;
import mbti.MbtiDialog;
import mbti.MbtiImageManager;

public class FindPwDialog extends JDialog implements FocusListener, KeyListener {
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfId;
	private JTextField tfMbti;
	private JLabel lblResult;
	private JButton btnNext;
	private JLabel checkId;
	private JLabel checkName;
	private MbtiImageManager im = new MbtiImageManager();
	private UserinfoRepository rep = new UserinfoRepositoryImpl();

	public JTextField getTfMbti() {
		return tfMbti;
	}

	public void setTfMbti(JTextField tfMbti) {
		this.tfMbti = tfMbti;
	}

//	public static void main(String[] args) {
//		new FindPwDialog().setVisible(true);
//	}

	public FindPwDialog() {
		setModal(true);
		setBounds(700, 180, 378, 465);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		textPane.setText("비밀번호 찾기");
		textPane.setEditable(false);
		textPane.setBounds(100, 60, 160, 39);
		contentPane.add(textPane);

		JLabel lblId = new JLabel("아이디");
		lblId.setBounds(66, 130, 100, 32);
		lblSetting(lblId);
		tfId = new JTextField("아이디");
		tfId.setBounds(63, 159, 234, 33);
		tfSetting(tfId);
		checkId = checkImage(lblId, 38);

		JLabel lblName = new JLabel("이름");
		lblName.setBounds(66, 195, 100, 32);
		lblSetting(lblName);
		tfName = new JTextField("이름");
		tfName.setBounds(63, 224, 234, 33);
		tfSetting(tfName);
		checkName = checkImage(lblName, 26);

		lblResult = new JLabel("");
		lblResult.setBounds(64, 266, 220, 39);
		lblResult.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lblResult.setBorder(null);
		lblResult.setForeground(Color.RED);
		contentPane.add(lblResult);

		btnNext = new JButton("다음");
		btnNext.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnNext.setBackground(new Color(96, 182, 230));
		btnNext.setBorderPainted(false);
		btnNext.setForeground(Color.white);
		btnNext.setBounds(62, 300, 236, 39);
		contentPane.add(btnNext);
		btnNext.addKeyListener(this);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tfId.getText();
				String name = tfName.getText();
				if (checkNotInput(id, name)) {
					String pw = checkInput(id, name);
					System.out.println(pw);
					new FindPwResultDialog(pw).setVisible(true);
					dispose();
				}
			}
		});
	}

	// 텍스트필드 설정
	private void tfSetting(JTextField tf) {
		tf.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf.setBackground(new Color(240, 241, 242));
		tf.setForeground(Color.gray);
		tf.setBorder(new EmptyBorder(0, 7, 0, 7));
		tf.setColumns(20);
		tf.addFocusListener(this);
		tf.addKeyListener(this);
		contentPane.add(tf);
	}

	// 라벨 설정
	private void lblSetting(JLabel lbl) {
		lbl.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lbl.setBackground(Color.white);
		lbl.setForeground(Color.DARK_GRAY);
		contentPane.add(lbl);
	}

	// 체크이미지(입력값 확인)
	private JLabel checkImage(JLabel lbl, int i) {
		JLabel lblCheck = new JLabel();
		lblCheck.setIcon(new ImageIcon(im.getUnchecked()));
		lblCheck.setBounds(i, 10, 15, 15);
		lblCheck.setBackground(null);
		lbl.add(lblCheck);
		return lblCheck;
	}

	// 입력값 확인 후 체크이미지 변경
	private void checkInputImage(KeyEvent e) {
		if (e.getSource() == tfId) {
			if (rep.countById(tfId.getText()) != 0) {
				checkId.setIcon(new ImageIcon(im.getChecked()));
			} else {
				checkId.setIcon(new ImageIcon(im.getUnchecked()));
			}
		} else if (e.getSource() == tfName) {
			String name = rep.loginUser(tfId.getText()).getName();
			if (name.equals(tfName.getText())) {
				checkName.setIcon(new ImageIcon(im.getChecked()));
			} else {
				checkName.setIcon(new ImageIcon(im.getUnchecked()));
			}
		}
	}

	// 입력값 없는지 확인
	public boolean checkNotInput(String id, String name) {
		if (name.equals("이름")) {
			lblResult.setText("이름를 입력해주세요.");
			return false;
		} else if (id.equals("아이디")) {
			lblResult.setText("아이디를 입력해주세요.");
			return false;
		} else {
			return true;
		}
	}

	public String checkInput(String id, String name) {
		String pw = "";
		pw = rep.loginUser(id).getPw();
		return pw;
	}

	@Override
	public void focusGained(FocusEvent e) { // 입력시 초기값 지우기
		if (e.getSource() == tfName && tfName.getText().equals("이름")) {
			tfName.setText("");
		} else if (e.getSource() == tfId && tfId.getText().equals("아이디")) {
			tfId.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) { // 내용을 지우면 초기값 설정
		if (e.getSource() == tfName && tfName.getText().equals("")) {
			tfName.setText("이름");
		} else if (e.getSource() == tfId && tfId.getText().equals("")) {
			tfId.setText("아이디");
		}
		if (e.getSource() == tfId) { // 코드정리하기..
			if (rep.countById(tfId.getText()) != 0) {
				checkId.setIcon(new ImageIcon(im.getChecked()));
			} else {
				checkId.setIcon(new ImageIcon(im.getUnchecked()));
			}
		} else if (e.getSource() == tfName) {
			String name = rep.loginUser(tfId.getText()).getName();
			if (name.equals(tfName.getText())) {
				checkName.setIcon(new ImageIcon(im.getChecked()));
			} else {
				checkName.setIcon(new ImageIcon(im.getUnchecked()));
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			btnNext.doClick();
		}
		checkInputImage(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		checkInputImage(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		checkInputImage(e);
	}
}
