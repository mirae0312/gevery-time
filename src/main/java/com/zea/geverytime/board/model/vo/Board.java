package com.zea.geverytime.board.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.zea.geverytime.common.model.vo.Attachment;

public class Board extends BoardEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int commentCount;
	private int attachCount; // 첨부파일 수
	private List<Attachment> attachments;
	
	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int no, String orCode, String title, String writer, String content, int readCount, int likeCount,
			Date regDate) {
		super(no, orCode, title, writer, content, readCount, likeCount, regDate);
		// TODO Auto-generated constructor stub
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getAttachCount() {
		return attachCount;
	}
	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	@Override
	public String toString() {
		return "Board ["+super.toString()+", commentCount=" + commentCount + ", attachCount=" + attachCount + ", attachments=" + attachments
				+ "]";
	}
	

	
	
	


	
}
