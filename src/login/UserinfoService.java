package login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserinfoService {
	private UserinfoRepository repo;

	public UserinfoService(UserinfoRepository repo) {
		super();
		this.repo = repo;
	}

	public boolean verifyId(String id) {
		Pattern p = Pattern.compile("[a-zA-Zㄱ-힣0-9]{1,20}");
		Matcher m = p.matcher(id);
		if (m.matches()) {
			return true;
		}
		System.out.println("id 입력오류");
		return false;
	}

	public boolean verifyPw(String pw) {
		Pattern p = Pattern.compile("[a-zA-Zㄱ-힣0-9]{4,20}");
		Matcher m = p.matcher(pw);
		if (m.matches()) {
			return true;
		}
		System.out.println("pw 입력오류");
		return false;
	}

	public boolean verifyName(String name) {
		Pattern p = Pattern.compile("[a-zA-Zㄱ-힣0-9]{1,15}");
		Matcher m = p.matcher(name);
		if (m.matches()) {
			return true;
		}
		System.out.println("이름 입력오류");
		return false;
	}

	public boolean verifyMbti(String str) {
		String mbti = str.toUpperCase();
		Pattern p = Pattern.compile("[I|E]{1}[S|N]{1}[T|F]{1}[J|P]{1}");
		Matcher m = p.matcher(mbti);
		if (m.matches()) {
			return true;
		}
		System.out.println("mbti 입력오류");
		return false;
	}

	public boolean duplicateId(String id) {
		// 중복확인
		if (repo.countById(id) == 0) {
			return true;
		}
		System.out.println("아이디중복");
		return false;
	}

	public void userInsert(User user) {
		repo.userInsert(user);

	}
	
	public void userUpdate(User user) {
		repo.userUpdate(user);
	}
}
