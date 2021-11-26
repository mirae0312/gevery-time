package com.zea.geverytime.info.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class InfoReview implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String pCode;
	private String writer;
	private String recommend;
	private String content;
	private Date visitDate;
	private Date regDate;
	
	public InfoReview() {
		super();
	}

	public InfoReview(String code, String pCode, String writer, String recommend, String content, Date visitDate,
			Date regDate) {
		super();
		this.code = code;
		this.pCode = pCode;
		this.writer = writer;
		this.recommend = recommend;
		this.content = content;
		this.visitDate = visitDate;
		this.regDate = regDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "InfoReview [code=" + code + ", pCode=" + pCode + ", writer=" + writer + ", recommend=" + recommend
				+ ", content=" + content + ", visitDate=" + visitDate + ", regDate=" + regDate + "]";
	}
	
	
	
}
