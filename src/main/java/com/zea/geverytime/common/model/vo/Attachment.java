package com.zea.geverytime.common.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Attachment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no_;
	private String code;
	private String originalFilename;
	private String renamedFilename;
	private Date regDate_;

	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Attachment(int no, String code, String originalFilename, String renamedFilename, Date regDate) {
		super();
		this.no_ = no;
		this.code = code;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.regDate_ = regDate;
	}

	public int getNo() {
		return no_;
	}
	public void setNo(int no) {
		this.no_ = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
		return regDate_;
	}
	public void setRegDate(Date regDate) {
		this.regDate_ = regDate;
	}

	@Override
	public String toString() {
		return "Attachment [no=" + no_ + ", code=" + code + ", originalFilename=" + originalFilename
				+ ", renamedFilename=" + renamedFilename + ", regDate=" + regDate_ + "]";
	}
	
	
}
