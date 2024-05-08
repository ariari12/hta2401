package memberz.vo;

import java.sql.Date;

public class SurveyAttendVO {
	public static SurveyAttendVO instance;
	private int aNo;
	private int sNo;
	private String id;
	private int num;
	private Date attendDate;
	
	private SurveyAttendVO() { }
	
	public SurveyAttendVO(int sNo, String id, int num) {
		this.sNo = sNo;
		this.id = id;
		this.num = num;
	}
	
	public static SurveyAttendVO getInstance() {
		if (instance != null) {
			return instance;
		}
		
		return new SurveyAttendVO();
	}
	
	public int getaNo() {
		return aNo;
	}
	public void setaNo(int aNo) {
		this.aNo = aNo;
	}
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getAttendDate() {
		return attendDate;
	}
	public void setAttendDate(Date attendDate) {
		this.attendDate = attendDate;
	}
	
	
}
