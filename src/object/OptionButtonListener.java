package object;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import game.GameFrame;
import game.GameModeServiceImpl;
import game.GameModeServiceToolImpl;

public class OptionButtonListener implements ActionListener {
	private GameFrame gameFrame;

	public OptionButtonListener(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GameModeServiceImpl gameModeService = new GameModeServiceImpl(new GameModeServiceToolImpl());
		gameFrame.nextImage();
		gameFrame.getOptionPanel().setVisible(false);
		gameFrame.setSelectedMode(false);
		int choice = gameModeService.selectByOption(e.getActionCommand());
		gameFrame.getChoiceList().add(choice);
		boolean answer = gameFrame.getMissonList().contains(choice);
		if (answer) {
			gameFrame.setScore(gameFrame.getScore() + 10);
		}
		System.out.println(gameFrame.getChoiceList());
	}
}
