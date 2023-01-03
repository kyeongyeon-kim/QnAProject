package login;

public class Login {
	private LoginFrame loginFrame;
	
	public void start() {
		loginFrame.setVisible(true);
	}
	
	public Login() {
		loginFrame = new LoginFrame();
	}
}
