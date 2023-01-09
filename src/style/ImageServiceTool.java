package style;

import java.awt.Image;
import java.util.List;

public interface ImageServiceTool {
	public List<Image> readImages(String path, int count);
	public int countFiles(String path);
	int countFilesAfterPackageJar(String path);
}
