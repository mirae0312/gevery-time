package com.zea.geverytime.market.cart.model.vo;

import java.io.Serializable;

import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	private String memberId;
	private int productboardNo;
	private ProductBoard pdtBoard;
	
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Cart(String memberId, int productboardNo) {
		super();
		this.memberId = memberId;
		this.productboardNo = productboardNo;
	}

	

	public Cart(String memberId, int productboardNo, ProductBoard pdtBoard) {
		super();
		this.memberId = memberId;
		this.productboardNo = productboardNo;
		this.pdtBoard = pdtBoard;
	}

	

	public ProductBoard getPdtBoard() {
		return pdtBoard;
	}


	public void setPdtBoard(ProductBoard pdtBoard) {
		this.pdtBoard = pdtBoard;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public int getProductboardNo() {
		return productboardNo;
	}


	public void setProductboardNo(int productboardNo) {
		this.productboardNo = productboardNo;
	}


	@Override
	public String toString() {
		return "Cart [memberId=" + memberId + ", productNo=" + productboardNo + "]";
	}
	
	
}
