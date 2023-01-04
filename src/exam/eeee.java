package exam;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dbutil.ConnectionProvider;

import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ScrollPaneConstants;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class eeee extends JFrame {

	private JScrollPane scrollPane;
	private JPanel contentPane;
	private JRadioButton radio[] = new JRadioButton[4];
	private static JRadioButton radioTest[] = new JRadioButton[41];
	private static JLabel lblTest[] = new JLabel[10];
	static List<String> QuestionList = new ArrayList<>();
	static List<examOption> optionList = new ArrayList<>();
	private static int count;
	
	//질문지 db에서 가져오기
	public static String readQuestion() {
		try (Connection conn = ConnectionProvider.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM exam")) {
			
			for (int j = 0; j < 10 ; j++) {
				lblTest[j] = new JLabel();
			}
			
			int k = 0;
			while (rs.next()) {
				String question = rs.getString("question");
				lblTest[k].setText(question);
				QuestionList.add(question);
				k++;
				
			}
			System.out.println(QuestionList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//답변지 db에서 가져오기
	public static String readOption() {
		try (Connection conn = ConnectionProvider.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM exam_option Where examNo = 1")) {

			for (int j = 0; j < 41; j++) {
				radioTest[j] = new JRadioButton();
			}
			
			count = 0;
			while (rs.next()) {
				int examNo = rs.getInt("examNo");
				String option = rs.getString("option");
				radioTest[count].setText(option);
				optionList.add(new examOption(examNo,option));
				count++;
				
			}
			System.out.println("개수 "+count);
			System.out.println(optionList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eeee frame = new eeee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public eeee() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 723);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 질문지 제목
		JLabel lblNewLabel = new JLabel("질문지");
		lblNewLabel.setBounds(218, 10, 97, 54);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		contentPane.add(lblNewLabel);

		// 가입하기 버튼
		JButton btnNewButton = new JButton("가입하기");
		btnNewButton.setBounds(194, 627, 135, 32);
		btnNewButton.setBackground(new Color(255, 228, 225));
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnNewButton.setForeground(Color.BLACK);
		contentPane.add(btnNewButton);

		// 스크롤
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(12, 62, 509, 555);
		contentPane.add(scrollPane_1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(panel);
		panel.setPreferredSize(new Dimension(405, 200 * 10 + 15));
		panel.setLayout(null);
		
		//확인용
		String exam1 = readQuestion();
		String exam2 = readOption();


		for (int i = 1; i < 11; i++) {
			//질문지 패널
			JPanel panel_i = new JPanel();
			panel_i.setBounds(12, 10 + (200 * (i - 1)), 470, 191);
			panel.add(panel_i);
			panel_i.setLayout(null);
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			
			//질문지 라벨
			lblTest[i - 1] = new JLabel();
			lblTest[i - 1].setText(QuestionList.get(i-1));
			lblTest[i - 1].setFont(new Font("맑은 고딕", Font.BOLD, 14));
			lblTest[i - 1].setBounds(30, 20, 295, 25);
			panel_i.add(lblTest[i - 1]);

			//라디오버튼
			for (int j = 1; j < count; j++) {
				radio[j - 1] = new JRadioButton();
				radio[j - 1].setText("dddd");
				radio[j - 1].setBounds(20 , 50 +(30*(j-1)), 400, 23);
				radio[j - 1].setFont(new Font("맑은 고딕", Font.PLAIN, 12));
				panel_i.add(radio[j - 1]);
			}

			ButtonGroup group = new ButtonGroup();
			for (int j = 0; j < radio.length; j++) {
				group.add(radio[j]);
			}
		}
	}
}
