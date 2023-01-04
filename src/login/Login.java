package login;

public class Login {
	private LoginFrame loginFrame;
	public final int loginComplete = 0;
	public final int loginFailByPw = 1;
	public final int loginFailById = 2;
	
	public Login() {
		loginFrame = new LoginFrame(this);
		loginFrame.setVisible(true);
	}

	// 로그인 정보 -> db 연결
	public int checkLogin(String id, String pw) {
		UserinfoRepositoryImpl repo = new UserinfoRepositoryImpl();
		if (repo.countById(id) != 0) {
			if (repo.selectPw(id).equals(pw)) {
				System.out.println("로그인 완료");
				return loginComplete;
			} else {
				System.out.println("로그인 실패: 비밀번호 틀림");
				return loginFailByPw;
			}
		} else {
			System.out.println("로그인 실패: 존재하지 않는 아이디");
			return loginFailById;
		}
	}
	public static void main(String[] args) {
		Login login = new Login();
	}
}
