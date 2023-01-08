package style;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageServiceToolImpl implements ImageServiceTool {
	private Toolkit kit = Toolkit.getDefaultToolkit();
	private ClassLoader classLoader = getClass().getClassLoader();
	
	@Override
	public List<Image> readImages(String path, int count) {
		List<Image> images = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Image image = kit.getImage(classLoader.getResource(path + i + ".jpg"));
			Image scaledImage = image.getScaledInstance(ImageService.WIDTH, ImageService.HEIGHT, Image.SCALE_SMOOTH);
			images.add(scaledImage);
		}
		return images;
	}

	@Override
	public int countFiles(String path) {
		File imagesDir = new File(classLoader.getResource(path).getFile());
		int countImages = imagesDir.listFiles().length;
		return countImages;
	}

}