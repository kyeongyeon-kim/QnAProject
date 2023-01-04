package login;

public class Userinfo {
	private String id;
	private String pw;
	private String name;
	private String mbti;
	private String gender;
	public Userinfo(String id, String pw, String name, String mbti, String gender) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.mbti = mbti;
		this.gender = gender;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMbti() {
		return mbti;
	}
	public void setMbti(String mbti) {
		this.mbti = mbti;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", mbti=" + mbti + ", gender=" + gender + "]";
	}
}
