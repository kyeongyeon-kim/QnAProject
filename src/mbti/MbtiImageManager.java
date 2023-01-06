package mbti;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MbtiImageManager {
	List<Image> images;
	List<Image> imagesSelect;
	String[] mbti = { "ISTJ", "ISFJ", "INFJ", "INTJ", "ISTP", "ISFP", "INFP", "INTP"
			, "ESTP", "ESFP", "ENFP", "ENTP", "ESTJ", "ESFJ", "ENFJ", "ENTJ" };
	Image qImage;
	Image qImage2;
	
	
	public String[] getMbti() {
		return mbti;
	}

	public void setMbti(String[] mbti) {
		this.mbti = mbti;
	}

	public MbtiImageManager() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();
		images = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			images.add(kit.getImage(classLoader.getResource("imagesMbti//" + mbti[i] + ".png")));
		}
		
		imagesSelect = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			imagesSelect.add(kit.getImage(classLoader.getResource("imagesMbti//" + mbti[i] + "-01.png")));
		}
		qImage = kit.getImage(classLoader.getResource("imagesMbti//Q.png"));
		qImage2 = kit.getImage(classLoader.getResource("imagesMbti//Q2.png"));
		
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Image> getImagesSelect() {
		return imagesSelect;
	}

	public void setImagesSelect(List<Image> imagesSelect) {
		this.imagesSelect = imagesSelect;
	}

	public Image getqImage() {
		return qImage;
	}

	public void setqImage(Image qImage) {
		this.qImage = qImage;
	}

	public Image getqImage2() {
		return qImage2;
	}

	public void setqImage2(Image qImage2) {
		this.qImage2 = qImage2;
	}
}