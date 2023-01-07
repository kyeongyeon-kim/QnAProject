package gamemode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import lobby.LobbyFrame;
import lobby.LobbyServiceImpl;
import lobby.LobbyServiceToolImpl;
import object.Attacker;
import object.User;
import rankingInquiry.UserRankDialog;

public class GameFrame extends JFrame {
	private JLabel label;
	private int currentImageIndex = 0;
	private List<Image> resultImages;
	private int count = 1;
	
	public GameFrame(User user, User defender) {
		GameModeServiceImpl gms = new GameModeServiceImpl(new GameModeServiceToolImpl());
		resultImages = gms.makeFirstHalfList(defender);
		JPanel pnl = new JPanel();
		pnl.setLayout(null);

		label = new JLabel(new ImageIcon(resultImages.get(currentImageIndex)));
		label.setBounds(0, 0, 944, 701);
		pnl.add(label);
		label.addMouseListener(new MouseAdapter() {

			

			@Override
			public void mouseClicked(MouseEvent e) {
				int lastImageNum = resultImages.size() - 1;
				if (currentImageIndex == lastImageNum) {
					dispose();
					UserRankDialog urd = new UserRankDialog(defender);
					LobbyServiceImpl lsi = new LobbyServiceImpl(new LobbyServiceToolImpl());
					List<Attacker> attackerList = lsi.makeAttackerList(defender.getId());
					lsi.setUserRanking(urd, attackerList);
					for (int i = 0; i < urd.getModel().getRowCount(); i++) {
						String attackerId = (String) urd.getModel().getValueAt(i, 1);
						if (user.getId().equals(attackerId)) {
							
						}
					}
					urd.setVisible(true);
					LobbyFrame lf = new LobbyFrame(user);
					lf.setVisible(true);
				}
				
				if (currentImageIndex != lastImageNum) {
					currentImageIndex += 1;
					label.setIcon(new ImageIcon(resultImages.get(currentImageIndex)));
					if (currentImageIndex == 44) {
						gms.decideSecondHalf(defender, resultImages, 60);
					}
				}
				
				boolean examScene = (currentImageIndex == 17 || currentImageIndex == 22 
						|| currentImageIndex == 28 || currentImageIndex == 37 || currentImageIndex == 41);
				if (examScene) {
					OptionPanel op = new OptionPanel(count);
					op.setBounds(200, 200, 500, 370);
					op.setOpaque(false);
					label.add(op);
					count++;
				}
			}
		});

		label.setFocusable(true);
		label.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// mouseClicked메소드와 똑같이 들어가면 됨
				}
			}
		});

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				int lastImageNum = resultImages.size() - 1;
				if (currentImageIndex != lastImageNum) {
					LobbyFrame lf = new LobbyFrame(user);
					lf.setVisible(true);
				}
			}

		});
		
		getContentPane().add(pnl);
		
		setSize(960, 740);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}