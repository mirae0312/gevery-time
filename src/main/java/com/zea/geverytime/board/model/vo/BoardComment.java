package com.zea.geverytime.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BoardComment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int no;
	private String orCode;
	private int commentLevel;
	private String writer;
	private String content;
	private int boardNo;
	private int commentRef;
	private int likeCount;
	private Date regDate;
	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardComment(int no, String orCode, int commentLevel, String writer, String content, int boardNo,
			int commentRef, int likeCount, Date regDate) {
		super();
		this.no = no;
		this.orCode = orCode;
		this.commentLevel = commentLevel;
		this.writer = writer;
		this.content = content;
		this.boardNo = boardNo;
		this.commentRef = commentRef;
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
	public void setOrCode(String orCode) {
		this.orCode = orCode;
	}
	public int getCommentLevel() {
		return commentLevel;
	}
	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
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
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getCommentRef() {
		return commentRef;
	}
	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
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
		return "BoardComment [no=" + no + ", orCode=" + orCode + ", commentLevel=" + commentLevel + ", writer=" + writer
				+ ", content=" + content + ", boardNo=" + boardNo + ", commentRef=" + commentRef + ", likeCount="
				+ likeCount + ", regDate=" + regDate + "]";
	}
	
	
}
