package ITBookFleaMarket.vo;

import java.sql.Date;

public class TradeLogVO {
	private int trLogNo;
	private int bookNo;
	private int tradePrice;
	private Date tradeDate;
	private String tradeState;
	private int invoiceNo;
	private String buyer;
	private int mlLogNo;

	
	
	public int getTrLogNo() {
		return trLogNo;
	}

	public void setTrLogNo(int trLogNo) {
		this.trLogNo = trLogNo;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public int getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(int tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public int getMlLogNo() {
		return mlLogNo;
	}

	public void setMlLogNo(int mlLogNo) {
		this.mlLogNo = mlLogNo;
	}

	public TradeLogVO(int trLogNo, int bookNo, int tradePrice, Date tradeDate, 
					String tradeState, int invoiceNo, String buyer, int mlLogNo) {
		this.trLogNo = trLogNo;
		this.bookNo = bookNo;
		this.tradePrice = tradePrice;
		this.tradeDate = tradeDate;
		this.tradeState = tradeState;
		this.invoiceNo = invoiceNo;
		this.buyer = buyer;
		this.mlLogNo = mlLogNo;
	}

	public TradeLogVO() {
		
	}
}
