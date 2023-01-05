package gamemode;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public GameFrame() {
		GameModeServiceImpl gms = new GameModeServiceImpl(new GameModeServiceToolImpl());
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		Image image = kit.getImage(classLoader.getResource("대화1.jpg"));
		
		
		
		setSize(1280, 845);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		GameFrame gf = new GameFrame();
		gf.setVisible(true);
	}
}
