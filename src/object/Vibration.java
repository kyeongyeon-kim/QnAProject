package object;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Vibration {
	private Timer timer;

	public Vibration(JLabel lbl) {
		
		timer = new Timer(1, new ActionListener() {
			int dx = 5; 
			int x = 0; 
			int movesCount = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				lbl.setLocation(x, 0);
				lbl.repaint();

				x += dx;
				
				if (x > 10 || x < 0) {
					dx = -dx;
					movesCount++;
				}
				if (movesCount == 10) {
					timer.stop();
				}
			}
		});
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	

}
