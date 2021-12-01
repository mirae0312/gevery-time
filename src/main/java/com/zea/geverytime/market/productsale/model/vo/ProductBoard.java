package com.zea.geverytime.market.productsale.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.zea.geverytime.common.model.vo.Attachment;

public class ProductBoard implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private int boardNo;
	private String orCode;
	private String title;
	private String content;
	private Date regDate;
	private String sellerId;
	private int productNo;
	private Product product;	
	private List<Attachment> attachments;
	
	
	public ProductBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public ProductBoard(int boardNo, String orCode, String title, String content, Date regDate, String sellerId,
			int productNo, Product product, List<Attachment> attachments) {
		super();
		this.boardNo = boardNo;
		this.orCode = orCode;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.sellerId = sellerId;
		this.productNo = productNo;
		this.product = product;
		this.attachments = attachments;
	}

	public ProductBoard(int boardNo, String orCode, String title, String content, Date regDate, String sellerId,
			int productNo) {
		super();
		this.boardNo = boardNo;
		this.orCode = orCode;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.sellerId = sellerId;
		this.productNo = productNo;
	}

	public ProductBoard(int boardNo, String orCode, String title, String content, Date regDate, String sellerId,
			int productNo, Product product) {
		super();
		this.boardNo = boardNo;
		this.orCode = orCode;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.sellerId = sellerId;
		this.productNo = productNo;
		this.product = product;
	}




	public List<Attachment> getAttachments() {
		return attachments;
	}



	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
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



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}



	@Override
	public String toString() {
		return "ProductBoard [boardNo=" + boardNo + ", orCode=" + orCode + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + ", sellerId=" + sellerId + ", productNo=" + productNo + ", product="
				+ product + ", attachments=" + attachments + "]";
	}

	

	


	
	
	
	
}
