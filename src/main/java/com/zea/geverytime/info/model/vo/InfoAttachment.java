package com.zea.geverytime.info.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class InfoAttachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String code;
	private String originalFilename;
	private String renamedFilename;
	private Date regDate;
	
	public InfoAttachment() {
		super();
	}

	public InfoAttachment(int no, String code, String originalFilename, String renamedFilename, Date regDate) {
		super();
		this.no = no;
		this.code = code;
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
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "InfoAttachment [no=" + no + ", code=" + code + ", originalFilename=" + originalFilename
				+ ", renamedFilename=" + renamedFilename + ", regDate=" + regDate + "]";
	}
	
	

}
