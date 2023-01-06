package gametest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class KyungtaeButton extends JButton {
	public KyungtaeButton(String setText) {
		this.setText(setText);
		this.revalidate();
		this.repaint();
//		this.setBorderPainted(false);
	}
}

