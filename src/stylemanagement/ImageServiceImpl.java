package stylemanagement;

import java.awt.Image;
import java.util.List;

public class ImageServiceImpl implements ImageService {
	private ImageServiceToolImpl ist;
	
	public ImageServiceImpl(ImageServiceToolImpl ist) {
		super();
		this.ist = ist;
	}

	@Override
	public List<Image> readIntro() {
		int countIntro = ist.countFiles(Paths.INTRO);
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

	
	
}
