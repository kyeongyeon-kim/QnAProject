package style;

import java.awt.Image;
import java.net.URL;
import java.util.List;

import main.Main;

public class ImageServiceImpl implements ImageService {
	private ImageServiceToolImpl ist;
	
	public ImageServiceImpl(ImageServiceToolImpl ist) {
		super();
		this.ist = ist;
	}

	@Override
	public List<Image> readIntro(String path) {
		return ist.readImages("집", 12);
	}

	@Override
	public List<Image> readKatalkMan(String path) {
		return ist.readImages("집남자카톡", 33);
	}

	@Override
	public List<Image> readKatalkWoman(String path) {
		return ist.readImages("집여자카톡", 33);
	}

	@Override
	public List<Image> getLikeAbilityMan(String path) {
		return ist.readImages("남자호감도통과", 1);
	}

	@Override
	public List<Image> getLikeAbilityWoman(String path) {
		return ist.readImages("여자호감도통과", 1);
	}

	@Override
	public List<Image> failedLikeAbilityMan(String path) {
		return ist.readImages("남자호감도실패", 2);
	}

	@Override
	public List<Image> failedLikeAbilityWoman(String path) {
//		int countIntro = isProtocolFile(path) == true ? ist.countFiles(path) : ist.countFilesAfterPackageJar(path);
		return ist.readImages("여자호감도실패", 2);
	}

	@Override
	public boolean isProtocolFile(String path) {
		URL url = null;
		try {
			url = Main.class.getClassLoader().getResource(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 프로토콜 구하기
		String protocol = url.getProtocol();
		System.out.println(protocol);
		if (protocol.equals("file")) {
			return true;
		}
		return false;
	}
}
