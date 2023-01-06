package mypage;

import lobby.LobbyService;
import lobby.LobbyServiceImpl;
import lobby.LobbyServiceToolImpl;
import object.User;

public class MypageInfo {
	private User user;
	
	public MypageInfo(User user) {
		this.user = user;
		new MypageDialog(user).setVisible(true);
	}
	
	// 테이블 정보 얻어오기
	public void getRankingTable() {
		LobbyService lobbyService = new LobbyServiceImpl(new LobbyServiceToolImpl());
		lobbyService.makeAttackerList(user.getId()); // List<Attacker>
//		System.out.println(lobbyService.makeAttackerList(user.getId()));
	}
	public static void main(String[] args) {
//		new MypageInfo(new User("은진", "1234", "이은진", "ISTJ", "여")); // 테스트용
		
//		MypageInfo m = new MypageInfo();
		new MypageInfo(new User("은진", "1234", "이은진", "ISTJ", "여")).getRankingTable();
	}
}
