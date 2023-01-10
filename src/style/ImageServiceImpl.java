package style;

import java.awt.Image;
import java.util.List;

import object.User;

public class ImageServiceImpl implements ImageService {
	private ImageServiceToolImpl ist;
	
	public ImageServiceImpl(ImageServiceToolImpl ist) {
		super();
		this.ist = ist;
	}

	@Override
	public List<Image> readIntro() {
		return ist.readImages("집", 12);
	}

	@Override
	public List<Image> readKatalkMan() {
		return ist.readImages("집남자카톡", 33);
	}

	@Override
	public List<Image> readKatalkWoman() {
		return ist.readImages("집여자카톡", 33);
	}

	@Override
	public List<Image> getLikeAbilityMan() {
		return ist.readImages("남자호감도통과", 1);
	}

	@Override
	public List<Image> getLikeAbilityWoman() {
		return ist.readImages("여자호감도통과", 1);
	}

	@Override
	public List<Image> failedLikeAbilityMan() {
		return ist.readImages("남자호감도실패", 2);
	}

	@Override
	public List<Image> failedLikeAbilityWoman() {
		return ist.readImages("여자호감도실패", 2);
	}

	@Override
	public List<Image> theDayWeMeet() {
		return ist.readImages("데이트", 3);
	}

	@Override
	public List<Image> theDayWeMeet1(boolean answer, User user) {
		List<Image> list = user.getGender().equals("남") ? ist.readImages("강준의데이트1_", 10) : ist.readImages("소희의데이트1_", 10);
		if (answer == true) {
			list.remove(1);
		} else {
			list.remove(0);
		}
		return list;
	}

	@Override
	public List<Image> theDayWeMeet2(boolean answer, User user) {
		List<Image> list = user.getGender().equals("남") ? ist.readImages("강준의데이트2_", 13) : ist.readImages("소희의데이트2_", 13);
		if (answer == true) {
			list.remove(1);
		} else {
			list.remove(0);
		}
		return list;
	}

	@Override
	public List<Image> theDayWeMeet3(boolean answer, User user) {
		List<Image> list = user.getGender().equals("남") ? ist.readImages("강준의데이트3_", 6) : ist.readImages("소희의데이트3_", 6);
		if (answer == true) {
			list.remove(1);
		} else {
			list.remove(0);
		}
		return list;
	}

	@Override
	public List<Image> theDayWeMeet4(boolean answer, User user) {
		List<Image> list = user.getGender().equals("남") ? ist.readImages("강준의데이트4_", 3) : ist.readImages("소희의데이트4_", 3);
		if (answer == true) {
			list.remove(1);
		} else {
			list.remove(0);
		}
		return list;
	}

	@Override
	public List<Image> gameOver() {
		return ist.readImages("gameOver", 1);
	}
}
