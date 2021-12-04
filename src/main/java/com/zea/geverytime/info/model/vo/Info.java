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
	private List<Hospital> hospitals;
	private List<CafeRestaurant> cafeRestaurants;
	private List<Pension> pensions;
	private List<Salon> salons;
	private int commentCount;
	
	public Info() {
		super();
	}
	
	
	
	public Info(String code, String memberId, int viewCount, String regCheck, String businessNo, Date regDate,
			String businessName, String businessAddress, String businessTel, String location, String headContent,
			String bodyContents, String serviceContent, String site, String startHour, String endHour,
			String startLaunch, String endLaunch, String startDinner, String endDinner, String holiday,
			String roadGuide, String deleteCheck, int recommend, int attachCount, List<Attachment> attachments,
			List<Hospital> hospitals, List<CafeRestaurant> cafeRestaurants, List<Pension> pensions, List<Salon> salons,
			int commentCount) {
		super(code, memberId, viewCount, regCheck, businessNo, regDate, businessName, businessAddress, businessTel,
				location, headContent, bodyContents, serviceContent, site, startHour, endHour, startLaunch, endLaunch,
				startDinner, endDinner, holiday, roadGuide, deleteCheck);
		this.recommend = recommend;
		this.attachCount = attachCount;
		this.attachments = attachments;
		this.hospitals = hospitals;
		this.cafeRestaurants = cafeRestaurants;
		this.pensions = pensions;
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



	public List<Hospital> getHospitals() {
		return hospitals;
	}



	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}



	public List<CafeRestaurant> getCafeRestaurants() {
		return cafeRestaurants;
	}



	public void setCafeRestaurants(List<CafeRestaurant> cafeRestaurants) {
		this.cafeRestaurants = cafeRestaurants;
	}



	public List<Pension> getPensions() {
		return pensions;
	}



	public void setPensions(List<Pension> pensions) {
		this.pensions = pensions;
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
				+ ", hospitals=" + hospitals
				+ ", cafeRestaurants=" + cafeRestaurants
				+ ", pensions=" + pensions
				+ ", salons=" + salons
				+ ", commentCount=" + commentCount	+ "]";
	}
	
	
	

}
