package com.zea.geverytime.market.point.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class PointHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private int withdraw;
	private int deposit;
	private Date regDate;
	private String history;
	private String purchaseUid;
	private String div;
	
	public PointHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PointHistory(int no, int withdraw, int deposit, Date regDate, String history,
			String purchaseUid, String div) {
		super();
		this.no = no;
		this.withdraw = withdraw;
		this.deposit = deposit;
		this.regDate = regDate;
		this.history = history;
		this.purchaseUid = purchaseUid;
		this.div = div;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}

	
	public int getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(int withdraw) {
		this.withdraw = withdraw;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getPurchaseUid() {
		return purchaseUid;
	}
	public void setPurchaseUid(String purchaseUid) {
		this.purchaseUid = purchaseUid;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
	}
	
	
}
