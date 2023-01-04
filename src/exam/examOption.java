package exam;

public class examOption {
	private int examNo;
	private String option;
	
	public examOption(int examNo, String option) {
		super();
		this.examNo = examNo;
		this.option = option;
	}

	@Override
	public String toString() {
		return "examOption [examNo=" + examNo + ", option=" + option + "]";
	}

	public int getExamNo() {
		return examNo;
	}

	public void setExamNo(int examNo) {
		this.examNo = examNo;
	}

	
	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	
}
