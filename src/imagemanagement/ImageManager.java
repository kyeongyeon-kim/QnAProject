package imagemanagement;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageManager {
	List<Image> images;
	
	public ImageManager() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		ClassLoader classLoader = getClass().getClassLoader();

		File imagesDir = new File(classLoader.getResource("images").getFile());
		int countImages = imagesDir.listFiles().length;
		images = new ArrayList<>();
		for (int i = 0; i < countImages; i++) {
			images.add(kit.getImage(classLoader.getResource("images//대화" + i + ".jpg")));
		}
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	
}
