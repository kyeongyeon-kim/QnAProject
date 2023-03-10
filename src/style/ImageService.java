package style;

import java.awt.Image;
import java.util.List;

import object.User;

public interface ImageService {
	int WIDTH = 960;
	int HEIGHT = 810;
	
	public List<Image> readIntro();
	public List<Image> readKatalkMan();
	public List<Image> readKatalkWoman();
	public List<Image> getLikeAbilityMan();
	public List<Image> getLikeAbilityWoman();
	public List<Image> failedLikeAbilityMan();
	public List<Image> failedLikeAbilityWoman();
	public List<Image> theDayWeMeet();
	public List<Image> gameOver();
	public List<Image> theDayWeMeet1(boolean answer, User user);
	public List<Image> theDayWeMeet2(boolean answer, User user);
	public List<Image> theDayWeMeet3(boolean answer, User user);
	public List<Image> theDayWeMeet4(boolean answer, User user);
}
