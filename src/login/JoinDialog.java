package login;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinDialog extends JDialog {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfId;
	private JTextField tfMbti;
	private JTextField tfPw;
	private JButton btnNext;
	private JRadioButton btnWoman;
	private JRadioButton btnMan;

	
	private void tfSetting(JTextField j) {
		j.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		j.setBackground(SystemColor.inactiveCaptionBorder);
		j.setForeground(Color.DARK_GRAY);
		contentPane.add(j);
		j.setColumns(20);
	}
	
	public JoinDialog() {
		setModal(true);
		setBounds(100, 100, 353, 570);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		textPane.setText("회원가입");
		textPane.setEditable(false);
		textPane.setBounds(110, 60, 130, 39);
		contentPane.add(textPane);
		
		tfName = new JTextField("이름");
		tfName.setBounds(58, 145, 220, 39);
		tfSetting(tfName);
		
		tfId = new JTextField("아이디");
		tfId.setBounds(58, 195, 220, 39);
		tfSetting(tfId);
		
		tfMbti = new JTextField("MBTI");
		tfMbti.setBounds(58, 245, 220, 39);
		tfSetting(tfMbti);
		
		tfPw = new JTextField("비밀번호");
		tfPw.setBounds(58, 295, 220, 39);
		tfSetting(tfPw);
		
		JLabel lblGender = new JLabel();
		lblGender.setBackground(Color.WHITE);
		lblGender.setText("성별");
		lblGender.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblGender.setBounds(75, 355, 116, 21);
		contentPane.add(lblGender);
		
		btnMan = new JRadioButton("남");
		btnMan.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnMan.setBackground(Color.WHITE);
		btnMan.setBounds(135, 354, 60, 23);
		contentPane.add(btnMan);
		
		btnWoman = new JRadioButton("여");
		btnWoman.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnWoman.setBackground(Color.WHITE);
		btnWoman.setBounds(200, 354, 80, 23);
		contentPane.add(btnWoman);	
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnMan);
		group.add(btnWoman);
		
		btnNext = new JButton("다음");
		btnNext.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnNext.setBackground(new Color(96,182,230));
		btnNext.setBorderPainted(false);
		btnNext.setForeground(Color.white);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNext.setBounds(58, 407, 220, 39);
		contentPane.add(btnNext);
	}
}
