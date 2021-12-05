package com.zea.geverytime.myPage.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class PruchaserDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int no;
	private int productNo;
	private int product_count;
	private Date regDate;
	
	public PruchaserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PruchaserDetail(int no, int productNo, int product_count, Date regDate) {
		super();
		this.no = no;
		this.productNo = productNo;
		this.product_count = product_count;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getProduct_count() {
		return product_count;
	}

	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "PruchaserDetail [no=" + no + ", productNo=" + productNo + ", product_count=" + product_count
				+ ", regDate=" + regDate + "]";
	}
	
	
}
