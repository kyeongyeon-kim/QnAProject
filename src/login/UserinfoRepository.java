package login;

import object.User;

public interface UserinfoRepository {
	public int countById(String id);
	public int userInsert(String id, String pw, String name, String mbti, String gender);
	public int userInsert(User user);
	public String selectPw(String id);
	public int userUpdate(User user);
	public User loginUser(String id);
	public void userDelete(User user);
}
