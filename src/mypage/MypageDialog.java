package mypage;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import lobby.LobbyServiceImpl;
import lobby.LobbyServiceToolImpl;
import login.UserinfoRepositoryImpl;
import object.Attacker;
import object.User;
import ranking.UserRankDialog;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class MypageDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTable tableAttack;
	private JTable tableDefend;
	private JPanel attackDefendPanel;
	private DefaultTableModel modelAttack;
	private DefaultTableModel modelDefend;
	public boolean mypageDispose = false;
	public boolean isMypageDispose() {
		return mypageDispose;
	}

	public void setMypageDispose(boolean mypageDispose) {
		this.mypageDispose = mypageDispose;
	}

	public DefaultTableModel getModelAttack() {
		return modelAttack;
	}

	public void setModelAttack(DefaultTableModel modelAttack) {
		this.modelAttack = modelAttack;
	}

	public DefaultTableModel getModelDefend() {
		return modelDefend;
	}

	public void setModelDefend(DefaultTableModel modelDefend) {
		this.modelDefend = modelDefend;
	}

	public JTable getTableAttack() {
		return tableAttack;
	}

	public void setTableAttack(JTable tableAttack) {
		this.tableAttack = tableAttack;
	}

	public JTable getTableDefend() {
		return tableDefend;
	}

	public void setTableDefend(JTable tableDefend) {
		this.tableDefend = tableDefend;
	}

	public MypageDialog(User user) {
		setModal(true);
		setBounds(580, 195, 602, 567);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblMypage = new JLabel("???????????????");
		lblMypage.setFont(new Font("?????? ??????", Font.BOLD, 31));
		lblMypage.setBounds(216, 20, 166, 61);
		contentPanel.add(lblMypage);
		
		attackDefendPanel = new JPanel();
		attackDefendPanel.setBackground(new Color(240, 248, 253));
		attackDefendPanel.setBounds(20, 90, 545, 345);
		contentPanel.add(attackDefendPanel);
		attackDefendPanel.setLayout(null);
		
		// ?????? ????????? ??????
		JTextField tfAttack = new JTextField("?????? ????????? ??????");
		tfSetting(tfAttack);
		tfAttack.setBounds(75, 12, 130, 35);
		
		JScrollPane attackPane = new JScrollPane();
		attackPane.setFont(new Font("?????? ??????", Font.PLAIN, 12));
		attackPane.setBounds(17, 55, 245, 275);
		attackDefendPanel.add(attackPane);
		
		String[] header1 = { "??????", "?????????", "????????????" };
		modelAttack = new DefaultTableModel(header1, 0);
		tableAttack = new JTable(modelAttack);
		tableAttack.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAttack.setDefaultEditor(Object.class, null);
		tableAttack.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableAttack.setFont(new Font("?????? ??????", Font.PLAIN, 12));
		tableAttack.setPreferredSize(new Dimension(270, 450));
		attackPane.setViewportView(tableAttack);
		
		LobbyServiceImpl lsi = new LobbyServiceImpl(new LobbyServiceToolImpl());
		List<Attacker> attakList = lsi.makeMyAttackList(user);
		lsi.setAttackRanking(this, attakList);
		// ?????? ????????? ?????? ????????????
		
		
		// ?????? ????????? ??????
		JTextField tfDefend = new JTextField("?????? ????????? ??????");
		tfSetting(tfDefend);
		tfDefend.setBounds(343, 12, 130, 35);
		
		JScrollPane defendPane = new JScrollPane();
		defendPane.setFont(new Font("?????? ??????", Font.PLAIN, 12));
		defendPane.setBounds(282, 55, 245, 275);
		attackDefendPanel.add(defendPane);
		
		String[] header2 = { "??????", "?????????", "??????" };
		modelDefend = new DefaultTableModel(header2, 0);
		tableDefend = new JTable(modelDefend);
		tableDefend.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDefend.setDefaultEditor(Object.class, null);
		tableDefend.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableDefend.setFont(new Font("?????? ??????", Font.PLAIN, 12));
		tableDefend.setPreferredSize(new Dimension(270, 450));
		defendPane.setViewportView(tableDefend);
		
		List<Attacker> defendList = lsi.makeAttackerList(user);
		lsi.setDefendRanking(this, defendList);
		// ?????? ????????? ?????? ????????????
		
		
		JButton btnChangeInfo = new JButton("?????? ?????? ??????");
		btnSetting(btnChangeInfo);
		btnChangeInfo.setBounds(50, 456, 120, 35);
		btnChangeInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangeInfoDialog(user).setVisible(true);
			}
		});
		
		JButton btnChangeAns = new JButton("????????? ?????? ??????");
		btnSetting(btnChangeAns);
		btnChangeAns.setBounds(180, 456, 130, 35);
		btnChangeAns.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangeExamDialog(user).setVisible(true);
			}
		});
		
		JButton btnBefore = new JButton("????????????");
		btnSetting(btnBefore);
		btnBefore.setBounds(320, 456, 100, 35);
		
		JButton btnSecession = new JButton("?????? ??????");
		btnSetting(btnSecession);
		btnSecession.setBounds(430, 456, 100, 35);
		btnSecession.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SecessionDialog sd = new SecessionDialog(user);
				sd.setVisible(true);
				if (sd.isSucessionDispose()) {
					dispose();
					mypageDispose = true;
				}
			}
		});
	}

	
	
	// ?????? ??????
	private void btnSetting(JButton btn) {
		btn.setFont(new Font("?????? ??????", Font.BOLD, 12));
		btn.setBorderPainted(false);
		btn.setBackground(new Color(96,182,230));
		btn.setForeground(Color.white);
		btn.addActionListener(this);
		contentPanel.add(btn);
	}
	
	// ??????????????? ??????
	private void tfSetting(JTextField j) {
		j.setFont(new Font("?????? ??????", Font.BOLD, 12));
		j.setBackground(new Color(96,182,230));
		j.setForeground(Color.white);
		j.setEditable(false);
		j.setBorder(new EmptyBorder(0, 18, 0, 0));
		attackDefendPanel.add(j);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("????????????")) {
			dispose();
		} 
	}
}
