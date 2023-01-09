package exam;

import java.util.List;

import object.User;


public interface examService {
		public List<String> readQuestion();
		public List<Integer> readEachNum();
		public List<String> readFixOption();
		public List<Integer> readMissionNum(String n);
		public void signUp(List<Integer> e, User user);
		public void editUp(List<Integer> e, User user);
		public List<String> readOptionByExamNo(int examNo);
		public int countOptionByExamNo(int examNo);
		
}
