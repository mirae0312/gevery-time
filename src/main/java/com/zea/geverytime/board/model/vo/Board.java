package com.zea.geverytime.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String orCode;
	private String title;
	private String writer;
	private String content;
	private int readCount;
	private int likeCount;
	private Date regDate;
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int no, String orCode, String title, String writer, String content, int readCount, int likeCount,
			Date regDate) {
		super();
		this.no = no;
		this.orCode = orCode;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.regDate = regDate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getOrCode() {
		return orCode;
	}
	public void setOrCode(String or_code) {
		this.orCode = or_code;
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
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Board [no=" + no + ", orCode=" + orCode + ", title=" + title + ", writer=" + writer + ", content="
				+ content + ", readCount=" + readCount + ", likeCount=" + likeCount + ", regDate=" + regDate + "]";
	}
	
	
}
