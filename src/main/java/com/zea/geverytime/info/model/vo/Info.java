package com.zea.geverytime.info.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.zea.geverytime.common.model.vo.Attachment;

public class Info extends InfoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int recommend;
	private int attachCount;
	private List<Attachment> attachments;
	private List<Salon> salons;
	private int commentCount;
	
	public Info() {
		super();
	}
	
	

	public Info(String code, String memberId, int viewCount, String regCheck, String businessNo, Date regDate,
			String businessName, String businessAddress, String businessTel, String location, String headContent,
			String bodyContents, String serviceContent, String site, String startHour, String endHour,
			String startLaunch, String endLaunch, String startDinner, String endDinner, String holiday,
			String roadGuide, int recommend, int attachCount, List<Attachment> attachments, List<Salon> salons,
			int commentCount) {
		super(code, memberId, viewCount, regCheck, businessNo, regDate, businessName, businessAddress, businessTel,
				location, headContent, bodyContents, serviceContent, site, startHour, endHour, startLaunch, endLaunch,
				startDinner, endDinner, holiday, roadGuide);
		this.recommend = recommend;
		this.attachCount = attachCount;
		this.attachments = attachments;
		this.salons = salons;
		this.commentCount = commentCount;
	}

	

	public int getRecommend() {
		return recommend;
	}



	public void setRecommend(int recommend) {
		this.recommend = recommend;
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



	public List<Salon> getSalons() {
		return salons;
	}



	public void setSalons(List<Salon> salons) {
		this.salons = salons;
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
				+ "recommend=" + recommend
				+ "attachCount=" + attachCount 
				+ ", attachments=" + attachments 
				+ ", salons=" + salons
				+ ", commentCount=" + commentCount	+ "]";
	}
	
	
	

}
