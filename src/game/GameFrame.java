package game;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lobby.LobbyFrame;
import lobby.LobbyService;
import lobby.LobbyServiceImpl;
import lobby.LobbyServiceToolImpl;
import object.OptionButtonListener;
import object.OptionPanel;
import object.User;
import object.Vibration;

public class GameFrame extends JFrame {
	private JLabel label;
	private int currentImageIndex = 0;
	private List<Image> resultImages;
	private List<Integer> examNo = new ArrayList<>(Arrays.asList(17, 22, 28, 37, 41));
	private OptionPanel optionPanel;
	private boolean selectedMode = false;
	private OptionButtonListener listener = new OptionButtonListener(this);
	private List<Integer> missonList = new ArrayList<>();
	private List<Integer> choiceList = new ArrayList<>();
	private int score = 0;
	
	public GameFrame(User user, User defender) {
		GameModeServiceImpl gameModeService = new GameModeServiceImpl(new GameModeServiceToolImpl());
		resultImages = gameModeService.makeFirstHalfList(defender);
		LobbyService lobbyService = new LobbyServiceImpl(new LobbyServiceToolImpl());
		missonList = lobbyService.makeMissonList(defender);
		JPanel pnl = new JPanel();
		pnl.setLayout(null);

		label = new JLabel(new ImageIcon(resultImages.get(currentImageIndex)));
		label.setBounds(0, 0, 944, 701);
		pnl.add(label);
		label.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// 게임 정상적으로 완료 시 동작
				if (selectedMode == false) {
					int lastImageNum = resultImages.size() - 1;
					if (currentImageIndex == lastImageNum) {
						dispose();
						if (gameModeService.isUserPlayedGameBefore(user, defender)) {
							
						} else {
							gameModeService.dataTransferToDB(user, defender, GameFrame.this);
						}
						gameModeService.gameComplete(user, defender);
					}

					// 맞춘 개수에 따른 2부 결정
					if (currentImageIndex != lastImageNum) {
						nextImage();
						boolean startSecondHalf = currentImageIndex == 44;
						if (startSecondHalf) {
							gameModeService.decideSecondHalf(defender, resultImages, score);
						}
					}

					// 선택지 발생 필요시 동작
					if (examNo.contains(currentImageIndex)) {
						int optionSceneNum = examNo.indexOf(currentImageIndex) + 1;
						optionPanel = new OptionPanel(optionSceneNum);
						optionPanel.setBounds(200, 200, 500, 370);
						for (JButton btn : optionPanel.getBtns()) {
							btn.addActionListener(listener);
						}
						label.add(optionPanel);
						selectedMode = true;
					}
					// 8페이지 진동효과 동작
					boolean phoneScene = currentImageIndex == 8;
					if (phoneScene) {
						Vibration vibe = new Vibration(label);
						vibe.getTimer().start();
					}
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
			// 게임을 완료하지않고 닫기창 누를 때 발생
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

	public void nextImage() {
		currentImageIndex += 1;
		label.setIcon(new ImageIcon(resultImages.get(currentImageIndex)));
	}
	
	

	public boolean isSelectedMode() {
		return selectedMode;
	}

	public void setSelectedMode(boolean selectedMode) {
		this.selectedMode = selectedMode;
	}

	public OptionPanel getOptionPanel() {
		return optionPanel;
	}

	public void setOptionPanel(OptionPanel optionPanel) {
		this.optionPanel = optionPanel;
	}

	public List<Integer> getMissonList() {
		return missonList;
	}

	public void setMissonList(List<Integer> missonList) {
		this.missonList = missonList;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Integer> getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(List<Integer> choiceList) {
		this.choiceList = choiceList;
	}
	
	
}
