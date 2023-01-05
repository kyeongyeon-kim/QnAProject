package mypage;

import login.User;

public class MypageInfo {
	private User user;
	
	public MypageInfo(User user) {
		this.user = user;
		new MypageDialog(user).setVisible(true);
	}
	
	// 테이블 정보 얻어오기

	public static void main(String[] args) {
		new MypageInfo(new User("은진", "1234", "이은진", "ISTJ", "여")); // 테스트용
	}
}
