package gamemode;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lobby.LobbyFrame;
import object.User;
import stylemanagement.ImageServiceImpl;
import stylemanagement.ImageServiceToolImpl;

public class GameFrame extends JFrame {
	JLabel label;
	int currentImageIndex = 0;
	
	public GameFrame(User defender, User attacker) {
		GameModeServiceImpl gms = new GameModeServiceImpl(new GameModeServiceToolImpl());
		ImageServiceImpl isi = new ImageServiceImpl(new ImageServiceToolImpl());
		
		label = new JLabel(new ImageIcon(isi.readIntro().get(currentImageIndex)));
		getContentPane().add(label, BorderLayout.CENTER);

		label.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				currentImageIndex = (currentImageIndex + 1) % isi.readIntro().size();
				label.setIcon(new ImageIcon(isi.readIntro().get(currentImageIndex)));
				if (attacker.getGender().equals("남") && currentImageIndex == 0) {
					currentImageIndex = (currentImageIndex + 1) % isi.readIntro().size();
					label.setIcon(new ImageIcon(isi.readKatalkMan().get(currentImageIndex)));
				}
			}
		});
		
		label.setFocusable(true);
		label.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					currentImageIndex = (currentImageIndex + 1) % isi.readIntro().size();
					label.setIcon(new ImageIcon(isi.readIntro().get(currentImageIndex)));
				}
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				LobbyFrame lf = new LobbyFrame(defender);
				lf.setVisible(true);
			}
			
		});
		
		setSize(960, 740);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}