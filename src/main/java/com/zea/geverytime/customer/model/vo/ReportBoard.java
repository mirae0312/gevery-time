package com.zea.geverytime.customer.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ReportBoard implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	
	private int reportNo;
	private String title;
	private String content;
	private int reportCode;
	private String reportCheck;
	private String memberId;
	private String businessNo;
	private Date regDate;
	
	
	public ReportBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ReportBoard(int reportNo, String title, String content, int reportCode, String reportCheck, String memberId,
			String businessNo, Date regDate) {
		super();
		this.reportNo = reportNo;
		this.title = title;
		this.content = content;
		this.reportCode = reportCode;
		this.reportCheck = reportCheck;
		this.memberId = memberId;
		this.businessNo = businessNo;
		this.regDate = regDate;
	}


	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
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
	public int getReportCode() {
		return reportCode;
	}
	public void setReportCode(int reportCode) {
		this.reportCode = reportCode;
	}
	public String getReportCheck() {
		return reportCheck;
	}
	public void setReportCheck(String reportCheck) {
		this.reportCheck = reportCheck;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	@Override
	public String toString() {
		return "ReportBoard [reportNo=" + reportNo + ", title=" + title + ", content=" + content + ", reportCode="
				+ reportCode + ", reportCheck=" + reportCheck + ", memberId=" + memberId + ", businessNo=" + businessNo
				+ ", regDate=" + regDate + "]";
	}
	

 

	 
	

}
