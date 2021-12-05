package com.zea.geverytime.myPage.model.vo;

import java.io.Serializable;
import java.sql.Date;


public class Purchase implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String uid;
	private String muid;
	private String name;
	private int price;
	private int productCount;
	private Date regDate;
	
	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Purchase(String uid, String muid, String name, int price, int productCount, Date regDate) {
		super();
		this.uid = uid;
		this.muid = muid;
		this.name = name;
		this.price = price;
		this.productCount = productCount;
		this.regDate = regDate;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "PurchaseDetail [uid=" + uid + ", muid=" + muid + ", name=" + name + ", price=" + price
				+ ", productCount=" + productCount + ", regDate=" + regDate + "]";
	}

	
}
