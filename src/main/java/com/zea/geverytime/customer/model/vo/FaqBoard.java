package com.zea.geverytime.customer.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class FaqBoard implements Serializable {

	 
	private static final long serialVersionUID = 1L;

	private int no;
	private String title;
	private String writer;
	private String content;
	private String category;
	private Date regDate;
	public FaqBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaqBoard(int no, String title, String writer, String content, String category, Date regDate) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.category = category;
		this.regDate = regDate;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "FaqBoard [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content + ", category="
				+ category + ", regDate=" + regDate + "]";
	}
	
	
	
	
}
