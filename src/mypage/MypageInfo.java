package mypage;


import javax.swing.JTable;

import object.User;

public class MypageInfo {
	private User user;
	private MypageDialog mypageDialog;
	
	public MypageInfo(User user) {
		this.user = user;
		mypageDialog = new MypageDialog(user);
		mypageDialog.setVisible(true);
	}
	
	// 테이블 정보 얻어오기
	public void getRankingTable(JTable tableAttack) {
	}
	
	// 로비창 끄기
	public boolean mypageDispose() {
		return mypageDialog.isMypageDispose();
	}
	
	
//	public static void main(String[] args) {
////		MypageInfo m = new MypageInfo(new User("은진", "1234", "이은진", "ISTJ", "여"));
//		MypageInfo m = new MypageInfo(new User("경연", "1234", "김경연", "ISFP", "남"));
////		MypageInfo m = new MypageInfo(new User("경태", "1234", "김경태", "INTJ", "남"));
//	}
}
