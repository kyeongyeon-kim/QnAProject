package mypage;

import login.User;
import login.UserinfoRepositoryImpl;
import login.UserinfoService;

public class ChangeInfo {
	private User user;
	private UserinfoService userinfoService = new UserinfoService(new UserinfoRepositoryImpl());
	public final int updateComplete = 20;
	public final int updateFailByName = 21;
	public final int updateFailByMbti = 22;
	public final int updateFailByPw = 23;
	public final int updateFail = 24;

	public ChangeInfo(User user) { // 로비에서 user 받기
		this.user = user;
//		new MypageDialog(user).setVisible(true);
	}

	// 입력값 확인
	public int checkInput(String pw, String name, String mbti, String gender) {
		if (!userinfoService.verifyName(name)) {
			return updateFailByName;
		} else if (!userinfoService.verifyMbti(mbti)) {
			return updateFailByMbti;
		} else if (!userinfoService.verifyPw(pw)) {
			return updateFailByPw;
		} else {
			user = new User(user.getId(), pw, name, mbti, gender);
			return updateComplete;
		}
	}

	// 수정하기
	public void updateInfo(User newUser) {
		userinfoService.userUpdate(newUser);
	}

	public static void main(String[] args) {
		new ChangeInfo(new User("은진", "1234", "이은진", "ISTJ", "여")); // 테스트용
	}
}
