package com.zea.geverytime.market.productsale.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ProductBoard implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private int boardNo;
	private String orCode;
	private String title;
	private String content;
	private Date regDate;
	private String sellerId;
	private int productNo;
	private String productState;
	
	
	public ProductBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductBoard(int boardNo, String orCode, String title, String content, Date regDate, String sellerId,
			int productNo, String productState) {
		super();
		this.boardNo = boardNo;
		this.orCode = orCode;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.sellerId = sellerId;
		this.productNo = productNo;
		this.productState = productState;
	}

	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getOrCode() {
		return orCode;
	}
	public void setOrCode(String orCode) {
		this.orCode = orCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	@Override
	public String toString() {
		return "ProductBoard [boardNo=" + boardNo + ", orCode=" + orCode + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + ", sellerId=" + sellerId + ", productNo=" + productNo + ", productState="
				+ productState + "]";
	}
	
	
	
	
}
