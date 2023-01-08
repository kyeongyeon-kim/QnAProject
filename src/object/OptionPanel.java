package object;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import exam.examServiceImpl;
import game.GameFrame;

public class OptionPanel extends JPanel {
	private List<JButton> btns;
	
	
	public OptionPanel(int examNo) {
		setLayout(null);
		setOpaque(false);
		examServiceImpl esi = new examServiceImpl();
		int optionNum = esi.countOptionByExamNo(examNo);
		List<String> optionList = esi.readOptionByExamNo(examNo);
		btns = new ArrayList<>();
		int[] y = { 130, 190, 70, 250, 10, 310 };

		for (int i = 0; i < optionNum; i++) {
			btns.add(new JButton());
			btns.get(i).setBounds(12, y[i], 476, 50);
			btns.get(i).setOpaque(true);
			btns.get(i).setText(optionList.get(i));
			add(btns.get(i));
		}
	}

	public List<JButton> getBtns() {
		return btns;
	}

	public void setBtns(List<JButton> btns) {
		this.btns = btns;
	}
}
