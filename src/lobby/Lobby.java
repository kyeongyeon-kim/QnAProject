package lobby;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Lobby extends JFrame {

	private JPanel contentPane;
	private JTextField inputID;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lobby frame = new Lobby();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Lobby() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton startBtn = new JButton("공략 시작");
		startBtn.setForeground(Color.WHITE);
		startBtn.setBorderPainted(false);
		startBtn.setBackground(new Color(92, 180, 229));
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		startBtn.setBounds(38, 44, 101, 200);
		contentPane.add(startBtn);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 253));
		panel.setBounds(151, 44, 350, 380);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 37, 326, 300);
		panel.add(scrollPane);
		
		String[] header = { "이름", "성별", "아이디", "MBTI" };
		DefaultTableModel model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JLabel listLabel = new JLabel("공략 리스트");
		listLabel.setHorizontalAlignment(SwingConstants.CENTER);
		listLabel.setBounds(136, 12, 80, 15);
		panel.add(listLabel);
		
		JLabel searchLabel = new JLabel("조회 아이디");
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setBounds(22, 347, 80, 15);
		panel.add(searchLabel);
		
		inputID = new JTextField();
		inputID.setBounds(114, 344, 116, 21);
		panel.add(inputID);
		inputID.setColumns(10);
		
		JButton searchBtn = new JButton("검색");
		searchBtn.setBackground(SystemColor.control);
//		searchBtn.setForeground(Color.WHITE);
//		searchBtn.setBorderPainted(false);
//		searchBtn.setBackground(new Color(92, 180, 229));
		searchBtn.setBounds(242, 343, 74, 23);
		panel.add(searchBtn);
		
		JButton endBtn = new JButton("종료");
		endBtn.setForeground(Color.WHITE);
		endBtn.setBorderPainted(false);
		endBtn.setBackground(new Color(92, 180, 229));
		endBtn.setBounds(39, 374, 100, 50);
		contentPane.add(endBtn);
		
		JButton mypageBtn = new JButton("마이페이지");
		mypageBtn.setForeground(Color.WHITE);
		mypageBtn.setBorderPainted(false);
		mypageBtn.setBackground(new Color(92, 180, 229));
		mypageBtn.setBounds(39, 314, 100, 50);
		contentPane.add(mypageBtn);
		
		JButton rankBtn = new JButton("랭킹 조회");
		rankBtn.setForeground(Color.WHITE);
		rankBtn.setBorderPainted(false);
		rankBtn.setBackground(new Color(92, 180, 229));
		rankBtn.setBounds(38, 254, 100, 50);
		contentPane.add(rankBtn);
		rankBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
