package gamemode;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import object.User;
import stylemanagement.ImageServiceImpl;
import stylemanagement.ImageServiceToolImpl;

public class GameModeServiceImpl implements GameModeService {
	private GameModeServiceTool gmst;

	public GameModeServiceImpl(GameModeServiceTool gmst) {
		super();
		this.gmst = gmst;
	}

	@Override
	public List<Image> makeFirstHalfList(User user) {
		List<Image> firstHalfImages = new ArrayList<>();
		ImageServiceImpl isi = new ImageServiceImpl(new ImageServiceToolImpl());
		List<Image> introList = isi.readIntro();
		List<Image> firstHalfList = user.getGender().equals("남") ? isi.readKatalkMan() : isi.readKatalkWoman();
		for (Image intro : introList) {
			firstHalfImages.add(intro);
		}

		for (Image firstHalf : firstHalfList) {
			firstHalfImages.add(firstHalf);
		}

		return firstHalfImages;
	}

	@Override
	public List<Image> makeSecondHalfList(User user, int score) {
		List<Image> secondHalfImages = new ArrayList<>();
		List<Image> secondHalfList = new ArrayList<>();
		ImageServiceImpl isi = new ImageServiceImpl(new ImageServiceToolImpl());
		if (user.getGender().equals("남")) {
			secondHalfList = score >= 60 ? isi.getLikeAbilityWoman() : isi.failedLikeAbilityWoman();
		} else {
			secondHalfList = score >= 60 ? isi.getLikeAbilityMan() : isi.failedLikeAbilityMan();
		}
		for (Image secondHalf : secondHalfList) {
			secondHalfImages.add(secondHalf);
		}
		return secondHalfImages;
	}

	@Override
	public List<Image> decideSecondHalf(User attacker, List<Image> resultImages, int score) {
		List<Image> secondHalfList = makeSecondHalfList(attacker, score);
		for (Image secondHalf : secondHalfList) {
			resultImages.add(secondHalf);
		}
		return resultImages;
	}

}
