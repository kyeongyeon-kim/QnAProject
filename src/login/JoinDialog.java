package login;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import mbti.MbtiDialog;
import mbti.MbtiImageManager;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class JoinDialog extends JDialog implements FocusListener, MouseListener {
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
	
	public JTextField getTfMbti() {
		return tfMbti;
	}
	public void setTfMbti(JTextField tfMbti) {
		this.tfMbti = tfMbti;
	}
	public static void main(String[] args) {
		new JoinDialog().setVisible(true);
	}
	public JoinDialog() {
		setModal(true);
		setBounds(700, 180, 361, 566);
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
		
		btnMbti = new JButton(); // mbti 물음표버튼
		btnMbti.setIcon(new ImageIcon(im.getqImage()));
		btnMbti.setBounds(185, 5, 30, 30);
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
		btnMan.setSelected(true);
		contentPane.add(btnMan);
		
		btnWoman = new JRadioButton("여자");
		btnWoman.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnWoman.setBackground(Color.WHITE); 
		btnWoman.setBounds(201, 354, 80, 21);
		contentPane.add(btnWoman);	
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnMan);
		group.add(btnWoman);
		
		lblResult = new JLabel("");
		lblResult.setBounds(64, 380, 220, 39);
		lblResult.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lblResult.setBorder(null);
		lblResult.setForeground(Color.RED);
		contentPane.add(lblResult);
		
		btnNext = new JButton("다음");
		btnNext.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnNext.setBackground(new Color(96,182,230));
		btnNext.setBorderPainted(false);
		btnNext.setForeground(Color.white);
		btnNext.setBounds(63, 413, 220, 39);
		contentPane.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tfId.getText();
				String pw = tfPw.getText();
				String name = tfName.getText();
				String mbti = tfMbti.getText().toUpperCase();
				String gender = "";
				if (btnMan.isSelected()) {
					gender = "남";
				} else if (btnWoman.isSelected()) {
					gender = "여";
				}
				if (checkNotInput(id, pw, name, mbti, gender)) {
					checkInput(id, pw, name, mbti, gender);
				}
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
	public void focusGained(FocusEvent e) { // 입력시 초기값 지우기
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
	public void focusLost(FocusEvent e) { // 내용을 지우면 초기값 설정
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
	
	// 입력값 없는지 확인
	public boolean checkNotInput(String id, String pw, String name, String mbti, String gender) {
		if (name.equals("이름")) {
			lblResult.setText("이름를 입력해주세요.");
			return false;
		} else if (id.equals("아이디")) {
			lblResult.setText("아이디를 입력해주세요.");
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
	
	public void checkInput(String id, String pw, String name, String mbti, String gender) {
		Join join = new Join();
		int result = join.checkInput(id, pw, name, mbti, gender);
		if (result == join.joinComplete) {
			System.out.println("회원가입 완료");
			lblResult.setText("");
			join.insert();
		} else if (result == join.joinFailByName) {
			lblResult.setText("이름 입력이 잘못되었습니다.(1~15자)");
		} else if (result == join.joinFailByDuplicate) {
			lblResult.setText("이미 가입된 아이디입니다.");
		} else if (result == join.joinFailById) {
			lblResult.setText("아이디 입력이 잘못되었습니다.(4~20자)");
		} else if (result == join.joinFailByMbti) {
			lblResult.setText("MBTI 입력이 잘못되었습니다.(ex.INFP)");
		} else if (result == join.joinFailByPw) {
			lblResult.setText("비밀번호 입력이 잘못되었습니다.(4~20자)");
		} 
	}
	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
