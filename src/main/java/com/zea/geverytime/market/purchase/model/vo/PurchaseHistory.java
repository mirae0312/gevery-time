package com.zea.geverytime.market.purchase.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class PurchaseHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private Date regDate;
	private String memberId;
	private int price;
	private String uid;
	private String muid;
	private String state;
	
	public PurchaseHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PurchaseHistory(int no, Date regDate, String memberId, int price, String uid, String muid, String state) {
		super();
		this.no = no;
		this.regDate = regDate;
		this.memberId = memberId;
		this.price = price;
		this.uid = uid;
		this.muid = muid;
		this.state = state;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMuid() {
		return muid;
	}

	public void setMuid(String muid) {
		this.muid = muid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
