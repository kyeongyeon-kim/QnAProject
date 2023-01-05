package mypage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import login.User;

import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;

public class ChangeInfoDialog extends JDialog implements FocusListener {
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfId;
	private JTextField tfMbti;
	private JTextField tfPw;
	private JLabel lblResult;
	private JButton btnNext;
	private JRadioButton btnWoman;
	private JRadioButton btnMan;

	public ChangeInfoDialog(User user) {
		setModal(true);
		setBounds(700, 180, 361, 566);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		textPane.setText("정보수정");
		textPane.setEditable(false);
		textPane.setBounds(115, 60, 131, 39);
		contentPane.add(textPane);
		
		JLabel lblName = new JLabel("이름");
		lblName.setBounds(63, 145, 220, 39);
		lblName.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentPane.add(lblName);
		
		tfName = new JTextField(user.getName());
		tfName.setBounds(120, 145, 160, 39);
		tfSetting(tfName);
		
		JLabel lblId = new JLabel("아이디");
		lblId.setBounds(63, 195, 220, 39);
	
		lblId.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentPane.add(lblId);
		
		tfId = new JTextField(user.getId());
		tfId.setBounds(120, 195, 160, 39);
		tfId.setEditable(false);
		tfSetting(tfId);
		
		JLabel lblMbti = new JLabel("MBTI");
		lblMbti.setBounds(63, 245, 220, 39);
		lblMbti.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentPane.add(lblMbti);
		
		tfMbti = new JTextField(user.getMbti());
		tfMbti.setBounds(120, 245, 160, 39);
		tfSetting(tfMbti);
		
		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setBounds(63, 295, 220, 39);
		lblPw.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		contentPane.add(lblPw);

		JPasswordField pfPw = new JPasswordField(user.getPw());
		pfPw.setBounds(120, 295, 160, 39);
		tfSetting(pfPw); 
		
		JLabel lblGender = new JLabel();
		lblGender.setBackground(Color.WHITE);
		lblGender.setText("성별");
		lblGender.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblGender.setBounds(71, 354, 116, 21);
		contentPane.add(lblGender);
		
		btnMan = new JRadioButton("남자");
		btnMan.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnMan.setBackground(Color.WHITE);
		btnMan.setBounds(133, 354, 60, 21);
		contentPane.add(btnMan);
		
		btnWoman = new JRadioButton("여자");
		btnWoman.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnWoman.setBackground(Color.WHITE); 
		btnWoman.setBounds(201, 354, 80, 21);
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
		lblResult.setBounds(64, 380, 220, 39);
		lblResult.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lblResult.setBorder(null);
		lblResult.setForeground(Color.RED);
		contentPane.add(lblResult);
		
		btnNext = new JButton("완료");
		btnNext.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnNext.setBackground(new Color(96,182,230));
		btnNext.setBorderPainted(false);
		btnNext.setForeground(Color.white);
		btnNext.setBounds(63, 413, 220, 39);
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
				updateInput(user, pw, name, mbti, gender);
			}
		});
	}
	
	// 텍스트필드 설정
	private void tfSetting(JTextField j) {
		j.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		j.setBackground(new Color(240,241,242));
		j.setForeground(new Color(88,89,91));
		j.setBorder(null);
		j.setBorder(new EmptyBorder(0, 7, 0, 7));
		j.setColumns(20);
		j.addFocusListener(this);
		contentPane.add(j);
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
	
	public void updateInput(User user, String pw, String name, String mbti, String gender) {
		ChangeInfo change = new ChangeInfo(user);
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
}
