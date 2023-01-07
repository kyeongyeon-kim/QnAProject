package gamemode;

import java.awt.Image;
import java.util.List;

import object.User;

public interface GameModeService {
	public List<Image> makeFirstHalfList(User user);
	public List<Image> makeSecondHalfList(User user, int score);
	public List<Image> decideSecondHalf(User attacker, List<Image> resultImages, int score);
}
