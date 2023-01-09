package style;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

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
	
	@Override
	public int countFilesAfterPackageJar(String path) {
		int count = 0;
		
		URL url = classLoader.getResource(path);
		if (url != null) { // 조건 대충만듬.
			return -100;
		} else {
			try {
				String jarPath = getJarPath();
				count = (int) walkingJarTree(path, jarPath, 1).count();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return count;
		}
	}
	
	private Stream<Path> walkingJarTree(String path, String jarPath, int depth) throws IOException {
		// file walks JAR
		URI uri = URI.create("jar:file:" + jarPath);
		try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
			Stream<Path> result = Files.walk(fs.getPath(path), depth);
			return result;
		}
	}

	private String getJarPath() throws URISyntaxException {
		String jarPath;
		jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		return jarPath;
	}
	
//	@Override
//	public int countFiles(String path) {
//		int count = 0;
////		System.out.println(path);
//		URL url = classLoader.getResource(path);
////		System.out.println(url);
//		String pathJar = url.getPath();
//
//		if (path.contains(".jar!")) {
//		    // resource is packaged inside a JAR file
//		    String[] parts = pathJar.split(".jar!");
//		    String jarFilePath = parts[0] + ".jar";
//		    try (JarFile jarFile = new JarFile(URLDecoder.decode(jarFilePath, "UTF-8"))) {
//		        Enumeration<JarEntry> entries = jarFile.entries();
//		        while (entries.hasMoreElements()) {
//		            JarEntry entry = entries.nextElement();
//		            String entryName = entry.getName();
//		            // do something with the resource
//		            count++;
//		        }
//		    } catch (IOException e) {
//		        // handle exception
//		    }
//		} else {
//		    // resource is not packaged inside a JAR file
//			File file = new File(url.getFile());
//			File[] files = file.listFiles();
//			for (File f : files) {
////			    System.out.println(f.getName());
//			    count++;
//			}
//		}
//		return count;
//	}

}