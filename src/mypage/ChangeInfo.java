package mypage;

import login.UserinfoRepositoryImpl;
import login.UserinfoService;
import object.User;

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
		new ChangeInfoDialog(user, ChangeInfo.this).setVisible(true);
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
}
