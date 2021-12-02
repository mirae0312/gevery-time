package com.zea.geverytime.market.cart.model.vo;

import java.io.Serializable;

public class Cart implements Serializable {
	private String memberId;
	private int productNo;
	
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Cart(String memberId, int productNo) {
		super();
		this.memberId = memberId;
		this.productNo = productNo;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public int getProductNo() {
		return productNo;
	}


	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}


	@Override
	public String toString() {
		return "Cart [memberId=" + memberId + ", productNo=" + productNo + "]";
	}
	
	
}
