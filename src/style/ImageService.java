package style;

import java.awt.Image;
import java.util.List;

public interface ImageService {
	int WIDTH = 960;
	int HEIGHT = 810;
	
	public List<Image> readIntro(String Path);
	public List<Image> readKatalkMan(String Path);
	public List<Image> readKatalkWoman(String Path);
	public List<Image> getLikeAbilityMan(String Path);
	public List<Image> getLikeAbilityWoman(String Path);
	public List<Image> failedLikeAbilityMan(String Path);
	public List<Image> failedLikeAbilityWoman(String Path);
	public boolean isProtocolFile(String path);
}
