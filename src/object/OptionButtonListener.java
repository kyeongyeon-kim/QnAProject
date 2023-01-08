package object;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import game.GameFrame;

public class OptionButtonListener implements ActionListener {
	private GameFrame gameFrame;

	public OptionButtonListener(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameFrame.nextImage();
		gameFrame.getOptionPanel().setVisible(false);
		gameFrame.setSelectedMode(false);
	}
}
