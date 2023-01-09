package style;

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
}
