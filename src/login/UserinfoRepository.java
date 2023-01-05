package login;

public interface UserinfoRepository {
	int countById(String id);
	int userInsert(String id, String pw, String name, String mbti, String gender);
	int userInsert(User user);
	String selectPw(String id);
	int userUpdate(User user);
	User loginUser(String id);
}
