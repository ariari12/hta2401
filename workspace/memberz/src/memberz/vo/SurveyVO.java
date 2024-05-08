package memberz.vo;

import java.sql.Date;

public class SurveyVO {
	private static SurveyVO instance; 
	private int sNo;
	private String title;
	private String num1;
	private String num2;
	private Date startDate;
	private Date endDate;
	private int num1Cnt;
	private int num2Cnt;
	
	private SurveyVO() { }
	
	public SurveyVO(String title, String num1, String num2, 
					Date startDate, Date endDate) {
		this.title = title;
		this.num1 = num1;
		this.num2 = num2;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public static SurveyVO getInstance() {
		if (instance != null) {
			return instance;
		}
		
		return new SurveyVO();
	}
	
	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNum1() {
		return num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public String getNum2() {
		return num2;
	}

	public void setNum2(String num2) {
		this.num2 = num2;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getNum1Cnt() {
		return num1Cnt;
	}

	public void setNum1Cnt(int num1Cnt) {
		this.num1Cnt = num1Cnt;
	}

	public int getNum2Cnt() {
		return num2Cnt;
	}

	public void setNum2Cnt(int num2Cnt) {
		this.num2Cnt = num2Cnt;
	}

}
