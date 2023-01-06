package login;

import object.User;

public class Join {
	private User user;
	private UserinfoService userinfoService = new UserinfoService(new UserinfoRepositoryImpl());
	public final int joinComplete = 10;
	public final int joinFailByName = 11;
	public final int joinFailByDuplicate = 12;
	public final int joinFailById = 13;
	public final int joinFailByMbti = 14;
	public final int joinFailByPw = 15;
	public final int joinFail = 16;

	// 입력값 확인
	public int checkInput(String id, String pw, String name, String mbti, String gender) {
		if (!userinfoService.verifyName(name)) {
			return joinFailByName;
		} else if (!userinfoService.duplicateId(id)) {
			return joinFailByDuplicate; 
		} else if (!userinfoService.verifyId(id)) {
			return joinFailById;
		} else if (!userinfoService.verifyMbti(mbti)) {
			return joinFailByMbti;
		} else if (!userinfoService.verifyPw(pw)) {
			return joinFailByPw;
		} else {
			user = new User(id, pw, name, mbti, gender);
			return joinComplete;
		}
	}
	
	// 입력값 db 저장
	public void insert() {
		userinfoService.userInsert(user);
	}
}
