package style;

import java.awt.Image;
import java.util.List;

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
}
