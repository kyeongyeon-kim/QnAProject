package login;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;

public class JoinDialog extends JDialog implements FocusListener {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfId;
	private JTextField tfMbti;
	private JTextField tfPw;
	private JButton btnNext;
	private JRadioButton btnWoman;
	private JRadioButton btnMan;

	public JoinDialog() {
		setModal(true);
		setBounds(700, 200, 361, 560);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		textPane.setText("회원가입");
		textPane.setEditable(false);
		textPane.setBounds(115, 60, 131, 39);
		contentPane.add(textPane);
		
		tfName = new JTextField("이름");
		tfName.setBounds(63, 145, 220, 39);
		tfSetting(tfName);
		
		tfId = new JTextField("아이디");
		tfId.setBounds(63, 195, 220, 39);
		tfSetting(tfId);
		
		tfMbti = new JTextField("MBTI");
		tfMbti.setBounds(63, 245, 220, 39);
		tfSetting(tfMbti);
		
		tfPw = new JTextField("비밀번호");
		tfPw.setBounds(63, 295, 220, 39);
		tfSetting(tfPw);
		
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

		btnNext = new JButton("다음");
		btnNext.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnNext.setBackground(new Color(96,182,230));
		btnNext.setBorderPainted(false);
		btnNext.setForeground(Color.white);
		btnNext.setBounds(63, 407, 220, 39);
		contentPane.add(btnNext);
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
		if (e.getSource() == tfName && tfName.getText().equals("이름")) {
			tfName.setText("");
		} else if (e.getSource() == tfId && tfId.getText().equals("아이디")) {
			tfId.setText("");
		} else if (e.getSource() == tfMbti && tfMbti.getText().equals("MBTI")) {
			tfMbti.setText("");
		} else if (e.getSource() == tfPw && tfPw.getText().equals("비밀번호")) {
			tfPw.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == tfName && tfName.getText().equals("")) {
			tfName.setText("이름");
		} else if (e.getSource() == tfId && tfId.getText().equals("")) {
			tfId.setText("아이디");
		} else if (e.getSource() == tfMbti && tfMbti.getText().equals("")) {
			tfMbti.setText("MBTI");
		} else if (e.getSource() == tfPw && tfPw.getText().equals("")) {
			tfPw.setText("비밀번호");
		}
	}
}
