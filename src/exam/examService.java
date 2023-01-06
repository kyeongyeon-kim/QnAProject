package exam;

import java.util.List;
import java.util.Map;

public interface examService {
		public List<String> readQuestion();
		public List<Integer> readEachNum();
		public List<String> readFixOption();
		public void signUp(List<Integer> e);
		public List<Integer> readMissionNum(String n);
		
}
