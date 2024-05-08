package ITBookFleaMarket.vo;

import java.sql.Date;

public class BankTransactionVO {
	private int transactionNo;
	private String transactionType;
	private String accountHolder;
	private int trLogNo;
	private int amount;
	private Date transactionDate;
	private String transactionId;
	
	public int getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public int getTrLogNo() {
		return trLogNo;
	}
	public void setTrLogNo(int trLogNo) {
		this.trLogNo = trLogNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	
}
