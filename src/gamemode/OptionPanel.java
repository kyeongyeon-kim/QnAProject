package gamemode;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import exam.examServiceImpl;

public class OptionPanel extends JPanel implements ActionListener {
	
	public OptionPanel(int examNo) {
		setPreferredSize(new Dimension(500, 370));
		setLayout(null);
		
		examServiceImpl esi = new examServiceImpl();
		int optionNum = esi.countOptionByExamNo(examNo);
		List<String> optionList = esi.readOptionByExamNo(examNo);
		List<JButton> btns = new ArrayList<>();
		int[] y = {130, 190, 70, 250, 10, 310};
		
		for (int i = 0; i < optionNum; i++) {
			btns.add(new JButton());
			btns.get(i).setBounds(12, y[i], 476, 50);
			btns.get(i).setOpaque(true);
			btns.get(i).setText(optionList.get(i));
			btns.get(i).addActionListener(this);
			add(btns.get(i));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
