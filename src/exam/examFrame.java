package exam;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dbutil.ConnectionProvider;

import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ScrollPaneConstants;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class examFrame extends JFrame implements ActionListener {

	private JScrollPane scrollPane;
	private JPanel contentPane;
	private static JRadioButton radioTest[] = new JRadioButton[41];
	private static JLabel lblTest[] = new JLabel[10];
	private JButton signUpButton;
	private examServiceImpl esi;
	static List<Integer> test = new ArrayList<>();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					examFrame frame = new examFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public examFrame() {
		esi = new examServiceImpl();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 723);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 질문지 제목
		JLabel questionTitle = new JLabel("질문지");
		questionTitle.setBounds(218, 10, 97, 54);
		questionTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		contentPane.add(questionTitle);
		
		//가입하기 버튼
		signUpButton = new JButton("가입하기");
		signUpButton.setBounds(194, 627, 135, 32);
		signUpButton.setBackground(new Color(255, 228, 225));
		signUpButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		signUpButton.setForeground(Color.BLACK);
		contentPane.add(signUpButton);
		signUpButton.addActionListener(this);

		// 스크롤
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(12, 62, 509, 555);
		contentPane.add(scrollPane_1);
		scrollPane_1.getVerticalScrollBar().setUnitIncrement(16);
		

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(panel);
		panel.setPreferredSize(new Dimension(405, 200 * 13 - 85));
		panel.setLayout(null);
		
		int count = 0;
		for (int i = 1; i < 11; i++) {
			//질문지 패널
			JPanel panel_i = new JPanel();
			panel_i.setBounds(12, 10 + (250 * (i - 1)), 470, 240);
			panel.add(panel_i);
			panel_i.setLayout(null);
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			
			List<String> QuestionList = esi.readQuestion();
			
			//질문지 라벨
			lblTest[i - 1] = new JLabel();
			lblTest[i - 1].setText(QuestionList.get(i-1));
			lblTest[i - 1].setFont(new Font("맑은 고딕", Font.BOLD, 14));
			lblTest[i - 1].setBounds(30, 20, 295, 25);
			panel_i.add(lblTest[i - 1]);

			List<Integer> examEachNum = esi.readEachNum();
			List<String> optionMap = esi.readOption();
			
			//라디오버튼
			int z=0;
			for (int j = count; j < count + examEachNum.get(i-1); j++) {
				radioTest[j] = new JRadioButton();
				radioTest[j].setText(optionMap.get(j));
				radioTest[j].setBounds(40 , 53 +(30*z), 400, 23);
				radioTest[j].setFont(new Font("맑은 고딕", Font.PLAIN, 12));
				panel_i.add(radioTest[j]);
				z++;
			}
			
			
			ButtonGroup group = new ButtonGroup();
			for (int j = count; j < count + examEachNum.get(i-1); j++) {
				group.add(radioTest[j]);
			}
			count += examEachNum.get(i-1); 
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(signUpButton.equals(e.getSource())) {
			
			for(int i =0; i < radioTest.length; i++) {
				if(radioTest[i].isSelected()) {
					test.add(i);
				}
			}
			System.out.println(test);
		//	System.out.println(radioTest[1].getText());
			
		//	System.out.println(map.get(1));
			
			
		}
	}
}
