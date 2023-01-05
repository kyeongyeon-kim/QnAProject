package mypage;

import login.User;

public class MypageInfo {
	private User user;
	
	public MypageInfo(User user) {
		this.user = user;
		new MypageDialog(user).setVisible(true);
	}
}
