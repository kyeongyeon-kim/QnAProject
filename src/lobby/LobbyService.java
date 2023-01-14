package lobby;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import mypage.MypageDialog;
import object.Attacker;
import object.User;
import ranking.UserRankDialog;

public interface LobbyService {
	public List<String[]> readUserinfo(User user);
	public List<Attacker> makeAttackerList(User user);
	public List<Attacker> makeMyAttackList(User user);
	public List<String[]> readedUserRanking(List<Attacker> attackerList);
	public void setDefendRanking(MypageDialog mypageDialog, List<Attacker> list);
	public void setAttackRanking(MypageDialog mypageDialog, List<Attacker> list);
	public List<Integer> makeMissonList(User user);
	
}
