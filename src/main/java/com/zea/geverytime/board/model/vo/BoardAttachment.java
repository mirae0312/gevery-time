package com.zea.geverytime.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BoardAttachment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int no;
	private String orCode;
	private String originalFilename;
	private String renamedFilename;
	private Date regDate;
	public BoardAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardAttachment(int no, String orCode, String originalFilename, String renamedFilename, Date regDate) {
		super();
		this.no = no;
		this.orCode = orCode;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
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
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "BoardAttachment [no=" + no + ", orCode=" + orCode + ", originalFilename=" + originalFilename
				+ ", renamedFilename=" + renamedFilename + ", regDate=" + regDate + "]";
	}
	
	
}
