package com.zea.geverytime.market.usedgoods.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.zea.geverytime.common.model.vo.Attachment;

public class UsedGoodsBoard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int no;
	private String title;
	private String content;
	private String orCode;
	private Date regDate;
	private String writer;
	private int price;
	private List<Attachment> attachments;
	private String state;
	
	public UsedGoodsBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsedGoodsBoard(int no, String title, String content, String orCode, Date regDate, String writer,
			int price) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.orCode = orCode;
		this.regDate = regDate;
		this.writer = writer;
		this.price = price;
	}

	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getOrCode() {
		return orCode;
	}

	public void setOrCode(String or_code) {
		this.orCode = or_code;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "UsedGoodsBoard [no=" + no + ", title=" + title + ", content=" + content + ", orCode=" + orCode
				+ ", regDate=" + regDate + ", writer=" + writer + ", price=" + price + ", attachments=" + attachments
				+ ", state=" + state + "]";
	}




	
	
	
}
