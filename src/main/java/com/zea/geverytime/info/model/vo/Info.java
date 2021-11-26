package com.zea.geverytime.info.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Info extends InfoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int attachCount;
	private List<InfoAttachment> attachments;
	private int commentCount;
	
	public Info() {
		super();
	}
	public Info(String code, String memberId, int viewCount, String regCheck, String businessNo, Date regDate,
			String businessName, String businessAddress, String businessTel, String location, String headContent,
			String bodyContents, String serviceContent, String site, String mon, String tue, String wed, String thu,
			String fri, String sat, String sun, String launch, String dinner, String holiday) {
		super(code, memberId, viewCount, regCheck, businessNo, regDate, businessName, businessAddress, businessTel, location,
				headContent, bodyContents, serviceContent, site, mon, tue, wed, thu, fri, sat, sun, launch, dinner, holiday);
	}
	
	
	
	public Info(String code, String memberId, int viewCount, String regCheck, String businessNo, Date regDate,
			String businessName, String businessAddress, String businessTel, String location, String headContent,
			String bodyContents, String serviceContent, String site, String mon, String tue, String wed, String thu,
			String fri, String sat, String sun, String launch, String dinner, String holiday, int attachCount,
			List<InfoAttachment> attachments, int commentCount) {
		super(code, memberId, viewCount, regCheck, businessNo, regDate, businessName, businessAddress, businessTel,
				location, headContent, bodyContents, serviceContent, site, mon, tue, wed, thu, fri, sat, sun, launch,
				dinner, holiday);
		this.attachCount = attachCount;
		this.attachments = attachments;
		this.commentCount = commentCount;
	}
	public int getAttachCount() {
		return attachCount;
	}
	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
	public List<InfoAttachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<InfoAttachment> attachments) {
		this.attachments = attachments;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Override
	public String toString() {
		return "Info [ " + super.toString()
				+ "attachCount=" + attachCount 
				+ ", attachments=" + attachments 
				+ ", commentCount=" + commentCount	+ "]";
	}
	
	
	

}
