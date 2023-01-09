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
		int countIntro = isProtocolFile(path) == true ? ist.countFiles(path) : ist.countFilesAfterPackageJar(path);
		return ist.readImages("images/01_intro/집", countIntro);
	}

	@Override
	public List<Image> readKatalkMan() {
		
		int countIntro = ist.countFiles(Paths.KATALK_MAN);
		return ist.readImages("images/02_katalk_Man/집남자카톡", countIntro);
	}

	@Override
	public List<Image> readKatalkWoman() {
		int countIntro = ist.countFiles(Paths.KATALK_WOMAN);
		return ist.readImages("images/02_katalk_Woman/집여자카톡", countIntro);
	}

	@Override
	public List<Image> getLikeAbilityMan() {
		int countIntro = ist.countFiles(Paths.LIKE_ABILITY_SUCCESS_MAN);
		return ist.readImages("images/03_likeability_Man/남자호감도통과", countIntro);
	}

	@Override
	public List<Image> getLikeAbilityWoman() {
		int countIntro = ist.countFiles(Paths.LIKE_ABILITY_SUCCESS_WOMAN);
		return ist.readImages("images/03_likeability_Woman/여자호감도통과", countIntro);
	}

	@Override
	public List<Image> failedLikeAbilityMan() {
		int countIntro = ist.countFiles(Paths.LIKE_ABILITY_FAIL_MAN);
		return ist.readImages("images/03_likeability_Fail_Man/남자호감도실패", countIntro);
	}

	@Override
	public List<Image> failedLikeAbilityWoman() {
		int countIntro = ist.countFiles(Paths.LIKE_ABILITY_FAIL_WOMAN);
		return ist.readImages("images/03_likeability_Fail_Woman/여자호감도실패", countIntro);
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
		if (protocol.equals("file")) {
			return true;
		}
		return false;
	}
}
