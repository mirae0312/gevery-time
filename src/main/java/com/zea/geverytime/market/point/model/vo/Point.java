package com.zea.geverytime.market.point.model.vo;

import java.io.Serializable;
import java.util.List;

public class Point implements Serializable {


	private static final long serialVersionUID = 1L;
	private int pointNo;
	private int balance;
	private String memberId;
	private List<PointHistory> history;
	
	
	
	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Point(int pointNo, int balance, String memberId, List<PointHistory> history) {
		super();
		this.pointNo = pointNo;
		this.balance = balance;
		this.memberId = memberId;
		this.history = history;
	}

	public int getPointNo() {
		return pointNo;
	}

	public void setPointNo(int pointNo) {
		this.pointNo = pointNo;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public List<PointHistory> getHistory() {
		return history;
	}

	public void setHistory(List<PointHistory> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "Point [pointNo=" + pointNo + ", balance=" + balance + ", memberId=" + memberId + ", history=" + history
				+ "]";
	}
	
	

}
