package com.zea.geverytime.customer.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class QnaBoard implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String title;
	private String writer;
	private String password;
	private String content;
	private int replyLevel;
	private int replyRef;
	private String category;
	private Date regDate;
	public QnaBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaBoard(int no, String title, String writer, String password, String content, int replyLevel, int replyRef,
			String category, Date regDate) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.password = password;
		this.content = content;
		this.replyLevel = replyLevel;
		this.replyRef = replyRef;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReplyLevel() {
		return replyLevel;
	}
	public void setReplyLevel(int replyLevel) {
		this.replyLevel = replyLevel;
	}
	public int getReplyRef() {
		return replyRef;
	}
	public void setReplyRef(int replyRef) {
		this.replyRef = replyRef;
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
		return "QnaBoard [no=" + no + ", title=" + title + ", writer=" + writer + ", password=" + password
				+ ", content=" + content + ", replyLevel=" + replyLevel + ", replyRef=" + replyRef + ", category="
				+ category + ", regDate=" + regDate + "]";
	}
	
	
	
	
}