package game;

import java.awt.Image;
import java.util.List;

import object.User;

public interface GameModeService {
	public List<Image> makeFirstHalfList(User user);
	public List<Image> makeSecondHalfList(User user, int score);
	public void decideSecondHalf(User attacker, List<Image> resultImages, int score);
	public void gameComplete(User user, User defender);
}
