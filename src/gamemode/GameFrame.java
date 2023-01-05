package gamemode;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import imagemanagement.ImageManager;

public class GameFrame extends JFrame {
	JLabel label;
	int currentImageIndex = 0;
	

	public GameFrame() {
		GameModeServiceImpl gms = new GameModeServiceImpl(new GameModeServiceToolImpl());
		ImageManager im = new ImageManager();
		
		label = new JLabel(new ImageIcon(im.getImages().get(currentImageIndex)));
		getContentPane().add(label, BorderLayout.CENTER);

		setSize(1280, 845);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentImageIndex = (currentImageIndex + 1) % im.getImages().size();
				label.setIcon(new ImageIcon(im.getImages().get(currentImageIndex)));
			}
		});

		
		label.setFocusable(true);
		label.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					currentImageIndex = (currentImageIndex + 1) % im.getImages().size();
					label.setIcon(new ImageIcon(im.getImages().get(currentImageIndex)));
				}
			}
		});
	}

	public static void main(String[] args) {
		GameFrame gf = new GameFrame();
		gf.setVisible(true);
	}
}