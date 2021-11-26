package com.zea.geverytime.market.productsale.model.vo;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int pdtNo;
	private String pdtName;
	private int pdtPrice;
	private String pdtDiv;
	private String seller_id;
	private String state;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(int pdtNo, String pdtName, int pdtPrice, String pdtDiv, String seller_id, String state) {
		super();
		this.pdtNo = pdtNo;
		this.pdtName = pdtName;
		this.pdtPrice = pdtPrice;
		this.pdtDiv = pdtDiv;
		this.seller_id = seller_id;
		this.state = state;
	}

	public int getPdtNo() {
		return pdtNo;
	}

	public void setPdtNo(int pdtNo) {
		this.pdtNo = pdtNo;
	}

	public String getPdtName() {
		return pdtName;
	}
	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}
	public int getPdtPrice() {
		return pdtPrice;
	}
	public void setPdtPrice(int pdtPrice) {
		this.pdtPrice = pdtPrice;
	}
	public String getPdtDiv() {
		return pdtDiv;
	}
	public void setPdtDiv(String pdtDiv) {
		this.pdtDiv = pdtDiv;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Product [pdtName=" + pdtName + ", pdtPrice=" + pdtPrice + ", pdtDiv=" + pdtDiv + ", seller_id="
				+ seller_id + ", state=" + state + "]";
	}
	
	
	
}
