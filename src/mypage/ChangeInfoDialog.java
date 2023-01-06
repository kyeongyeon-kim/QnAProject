package mypage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import login.JoinDialog;
import mbti.MbtiDialog;
import mbti.MbtiImageManager;
import object.User;

import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class ChangeInfoDialog extends JDialog implements FocusListener, MouseListener  {
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfId;
	private JTextField tfMbti;
	private JTextField tfPw;
	private JLabel lblResult;
	private JButton btnNext;
	private JRadioButton btnWoman;
	private JRadioButton btnMan;
	private JButton btnMbti;
	MbtiImageManager im = new MbtiImageManager();

	public ChangeInfoDialog(User user, ChangeInfo changeInfo) {
		setModal(true);
		setBounds(700, 180, 361, 616);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		textPane.setText("정보수정");
		textPane.setEditable(false);
		textPane.setBounds(110, 40, 131, 39);
		contentPane.add(textPane);

		JLabel lblName = new JLabel("이름");
		lblName.setBounds(65, 110, 220, 39);
		lblSetting(lblName);

		tfName = new JTextField(user.getName());
		tfName.setBounds(63, 143, 220, 32);
		tfSetting(tfName);

		JLabel lblId = new JLabel("아이디");
		lblId.setBounds(65, 175, 220, 39);
		lblSetting(lblId);

		tfId = new JTextField(user.getId());
		tfId.setBounds(63, 208, 220, 32);
		tfId.setEditable(false);
		tfSetting(tfId);

		JLabel lblMbti = new JLabel("MBTI");
		lblMbti.setBounds(65, 240, 220, 39);
		lblSetting(lblMbti);

		tfMbti = new JTextField(user.getMbti());
		tfMbti.setBounds(63, 273, 220, 32);
		tfSetting(tfMbti);

		btnMbti = new JButton(); // mbti 물음표버튼
		btnMbti.setIcon(new ImageIcon(im.getqImage()));
		btnMbti.setBounds(188, 1, 30, 30);
		btnMbti.setBorderPainted(false);
		btnMbti.setBackground(null);
		tfMbti.add(btnMbti);
		btnMbti.addMouseListener(this);
		btnMbti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MbtiDialog(tfMbti).setVisible(true);
			}
		});

		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setBounds(65, 305, 220, 39);
		lblSetting(lblPw);

		JPasswordField pfPw = new JPasswordField(user.getPw());
		pfPw.setBounds(63, 338, 220, 32);
		tfSetting(pfPw);

		JLabel lblGender = new JLabel("성별");
		lblGender.setBounds(65, 387, 116, 21);
		lblSetting(lblGender);

		btnMan = new JRadioButton("남자");
		btnMan.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnMan.setBackground(Color.WHITE);
		btnMan.setBounds(128, 387, 60, 21);
		contentPane.add(btnMan);

		btnWoman = new JRadioButton("여자");
		btnWoman.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnWoman.setBackground(Color.WHITE);
		btnWoman.setBounds(200, 387, 80, 21);
		contentPane.add(btnWoman);

		ButtonGroup group = new ButtonGroup();
		group.add(btnMan);
		group.add(btnWoman);

		String gender = user.getGender();
		if (gender.equals("남")) {
			btnMan.setSelected(true);
		} else if (gender.equals("여")) {
			btnWoman.setSelected(true);
		}

		lblResult = new JLabel("");
		lblResult.setBounds(64, 414, 220, 39);
		lblResult.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lblResult.setBorder(null);
		lblResult.setForeground(Color.RED);
		contentPane.add(lblResult);

		btnNext = new JButton("완료");
		btnNext.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnNext.setBackground(new Color(96, 182, 230));
		btnNext.setBorderPainted(false);
		btnNext.setForeground(Color.white);
		btnNext.setBounds(63, 448, 220, 39);
		contentPane.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] c = pfPw.getPassword();
				String pw = "";
				for (int i = 0; i < c.length; i++) {
					pw += c[i];
				}
				String name = tfName.getText();
				String mbti = tfMbti.getText().toUpperCase();
				String gender = "";
				if (btnMan.isSelected()) {
					gender = "남";
				} else if (btnWoman.isSelected()) {
					gender = "여";
				}
				if (checkNotInput(pw, name, mbti, gender)) {
					updateInput(changeInfo, user, pw, name, mbti, gender);
					user.setName(name);
					user.setPw(pw);
					user.setMbti(mbti);
					user.setGender(gender);
				}
				
			}
		});
	}

	// 텍스트필드 설정
	private void tfSetting(JTextField tf) {
		tf.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf.setBackground(new Color(240, 241, 242));
		tf.setForeground(new Color(88, 89, 91));
		tf.setBorder(null);
		tf.setBorder(new EmptyBorder(0, 7, 0, 7));
		tf.setColumns(20);
		tf.addFocusListener(this);
		contentPane.add(tf);
	}

	private void lblSetting(JLabel lbl) {
		lbl.setForeground(new Color(65, 64, 66));
		lbl.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentPane.add(lbl);
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) { // 내용을 지우면 초기값 설정
		if (e.getSource() == tfName && tfName.getText().equals("")) {
			tfName.setText("이름");
		} else if (e.getSource() == tfMbti && tfMbti.getText().equals("")) {
			tfMbti.setText("MBTI");
		} else if (e.getSource() == tfPw && tfPw.getText().equals("")) {
			tfPw.setText("비밀번호");
		}
	}

	// 입력값 없는지 확인
	public boolean checkNotInput(String pw, String name, String mbti, String gender) {
		if (name.equals("이름")) {
			lblResult.setText("이름를 입력해주세요.");
			return false;
		} else if (mbti.equals("MBTI")) {
			lblResult.setText("MBTI를 입력해주세요.");
			return false;
		} else if (pw.equals("비밀번호")) {
			lblResult.setText("비밀번호를 입력해주세요.");
			return false;
		} else {
			return true;
		}
	}

	public void updateInput(ChangeInfo change, User user, String pw, String name, String mbti, String gender) {
		int result = change.checkInput(pw, name, mbti, gender);
		if (result == change.updateComplete) {
			lblResult.setText("업데이트 완료");
			change.updateInfo(new User(user.getId(), pw, name, mbti, gender));
			this.dispose();
		} else if (result == change.updateFailByName) {
			lblResult.setText("이름 입력이 잘못되었습니다.(1~15자)");
		} else if (result == change.updateFailByMbti) {
			lblResult.setText("MBTI 입력이 잘못되었습니다.(ex.INFP)");
		} else if (result == change.updateFailByPw) {
			lblResult.setText("비밀번호 입력이 잘못되었습니다.(4~20자)");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnMbti) {
			btnMbti.setIcon(new ImageIcon(im.getqImage2()));
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnMbti) {
			btnMbti.setIcon(new ImageIcon(im.getqImage()));
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
