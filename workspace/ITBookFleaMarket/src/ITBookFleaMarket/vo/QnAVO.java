package ITBookFleaMarket.vo;

import java.sql.Date;

public class QnAVO {
	private static QnAVO instance;
	private int qNo;
	private String qId;
	private String qTitle;
	private String qContent;
	private Date qDate;
	private String aState;
	private String aContent;
	private Date aDate;
	
	private QnAVO() { }
	
	public QnAVO(String qId, String qTitle, String qContent) {
		this.qId = qId;
		this.qTitle = qTitle;
		this.qContent = qContent;
	}
	
	public static QnAVO getInstance() {
		if (instance == null) {
			instance = new QnAVO();
		}
		return instance;
	}
	
	public static void setInstance(QnAVO instance) {
		QnAVO.instance = instance;
	}
	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public Date getqDate() {
		return qDate;
	}
	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}
	public String getaState() {
		return aState;
	}
	public void setaState(String aState) {
		this.aState = aState;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}
	public Date getaDate() {
		return aDate;
	}
	public void setaDate(Date aDate) {
		this.aDate = aDate;
	}
	
	
}
