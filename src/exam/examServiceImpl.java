package exam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

import dbutil.ConnectionProvider;

public class examServiceImpl implements examService {
	private static JLabel lblTest[] = new JLabel[10];
	private static JRadioButton radioTest[] = new JRadioButton[41];
	static List<String> QuestionList = new ArrayList<>();
	static List<String> optionList = new ArrayList<>();
	static List<Integer> examEachNum = new ArrayList<>();
	static Map<Integer,String> map = new LinkedHashMap<>();
	
	@Override
	public List<String> readQuestion() {
		try (Connection conn = ConnectionProvider.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM exam")) {
			
			for (int j = 0; j < 10 ; j++) {
				lblTest[j] = new JLabel();
			}
			
			int k = 0;
			while (rs.next()) {
				String question = rs.getString("question");
				lblTest[k].setText(question);
				QuestionList.add(question);
				k++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return QuestionList;
	}
	

	@Override
	public List<String> readOption() {
			try (Connection conn = ConnectionProvider.makeConnection();
					Statement stmt1 = conn.createStatement();
					Statement stmt2 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery("SELECT * FROM exam_option ORDER BY choice;");
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM exam;")) {

				for (int j = 0; j < 41; j++) {
					radioTest[j] = new JRadioButton();
				}
				
//				while(rs1.next()) {
//					int fixChoiceNum = rs1.getInt("choice");
//					String fixOption = rs1.getString("option");
//					map.put(fixChoiceNum,fixOption);
//				}
//				for (Map.Entry<Integer,String> entry : map.entrySet())
//				{
//				    System.out.println("key : " + entry.getKey() + " / " + "value : " + entry.getValue());
//				}

		
//				while(rs2.next()) {
//					int qeustionNum = rs2.getInt("examNo");
//					
//				}
				int k =0;
				while(rs1.next()) {
					String option = rs1.getString("option");
			//		radioTest[k].setText(option);
					optionList.add(option);
					k++;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return optionList;
		
	}


	@Override
	public List<Integer> readEachNum() {
		try (Connection conn = ConnectionProvider.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT count(*) FROM exam_option group by examNo;");
				){

			for (int j = 0; j < 41; j++) {
				radioTest[j] = new JRadioButton();
			}
			
			while (rs.next()) {
				int examNo = rs.getInt("count(*)");
				examEachNum.add(examNo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return examEachNum;
	}

}
