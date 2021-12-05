package com.zea.geverytime.info.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class InfoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String memberId;
	private int viewCount;
	private String regCheck;
	private String businessNo;
	private Date regDate;
	private String businessName;
	private String businessAddress;
	private String businessTel;
	private String location;
	private String headContent;
	private String bodyContents;
	private String serviceContent;
	private String site;
	private String startHour;
	private String endHour;
	private String startLaunch;
	private String endLaunch;
	private String startDinner;
	private String endDinner;
	private String holiday;
	private String roadGuide;
	private String deleteCheck;
	
	public InfoEntity() {
		super();
	}

	public InfoEntity(String code, String memberId, int viewCount, String regCheck, String businessNo, Date regDate,
			String businessName, String businessAddress, String businessTel, String location, String headContent,
			String bodyContents, String serviceContent, String site, String startHour, String endHour,
			String startLaunch, String endLaunch, String startDinner, String endDinner, String holiday,
			String roadGuide, String deleteCheck) {
		super();
		this.code = code;
		this.memberId = memberId;
		this.viewCount = viewCount;
		this.regCheck = regCheck;
		this.businessNo = businessNo;
		this.regDate = regDate;
		this.businessName = businessName;
		this.businessAddress = businessAddress;
		this.businessTel = businessTel;
		this.location = location;
		this.headContent = headContent;
		this.bodyContents = bodyContents;
		this.serviceContent = serviceContent;
		this.site = site;
		this.startHour = startHour;
		this.endHour = endHour;
		this.startLaunch = startLaunch;
		this.endLaunch = endLaunch;
		this.startDinner = startDinner;
		this.endDinner = endDinner;
		this.holiday = holiday;
		this.roadGuide = roadGuide;
		this.deleteCheck = deleteCheck;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getRegCheck() {
		return regCheck;
	}

	public void setRegCheck(String regCheck) {
		this.regCheck = regCheck;
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

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getBusinessTel() {
		return businessTel;
	}

	public void setBusinessTel(String businessTel) {
		this.businessTel = businessTel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHeadContent() {
		return headContent;
	}

	public void setHeadContent(String headContent) {
		this.headContent = headContent;
	}

	public String getBodyContents() {
		return bodyContents;
	}

	public void setBodyContents(String bodyContents) {
		this.bodyContents = bodyContents;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getStartLaunch() {
		return startLaunch;
	}

	public void setStartLaunch(String startLaunch) {
		this.startLaunch = startLaunch;
	}

	public String getEndLaunch() {
		return endLaunch;
	}

	public void setEndLaunch(String endLaunch) {
		this.endLaunch = endLaunch;
	}

	public String getStartDinner() {
		return startDinner;
	}

	public void setStartDinner(String startDinner) {
		this.startDinner = startDinner;
	}

	public String getEndDinner() {
		return endDinner;
	}

	public void setEndDinner(String endDinner) {
		this.endDinner = endDinner;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getRoadGuide() {
		return roadGuide;
	}

	public void setRoadGuide(String roadGuide) {
		this.roadGuide = roadGuide;
	}

	public String getDeleteCheck() {
		return deleteCheck;
	}

	public void setDeleteCheck(String deleteCheck) {
		this.deleteCheck = deleteCheck;
	}

	@Override
	public String toString() {
		return "InfoEntity [code=" + code + ", memberId=" + memberId + ", viewCount=" + viewCount + ", regCheck="
				+ regCheck + ", businessNo=" + businessNo + ", regDate=" + regDate + ", businessName=" + businessName
				+ ", businessAddress=" + businessAddress + ", businessTel=" + businessTel + ", location=" + location
				+ ", headContent=" + headContent + ", bodyContents=" + bodyContents + ", serviceContent="
				+ serviceContent + ", site=" + site + ", startHour=" + startHour + ", endHour=" + endHour
				+ ", startLaunch=" + startLaunch + ", endLaunch=" + endLaunch + ", startDinner=" + startDinner
				+ ", endDinner=" + endDinner + ", holiday=" + holiday + ", roadGuide=" + roadGuide + ", deleteCheck="
				+ deleteCheck + "]";
	}
	
	
	
}
