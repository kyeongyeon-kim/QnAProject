package login;

public class UserInputEvent {
	private String name;
	private String id;
	private String mbti;
	private String pw;
	private String gender;
	public UserInputEvent(String name, String id, String mbti, String pw, String gender) {
		super();
		this.name = name;
		this.id = id;
		this.mbti = mbti;
		this.pw = pw;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMbti() {
		return mbti;
	}
	public void setMbti(String mbti) {
		this.mbti = mbti;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
