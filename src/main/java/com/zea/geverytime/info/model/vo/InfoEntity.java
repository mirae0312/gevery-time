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
	private String mon;
	private String tue;
	private String wed;
	private String thu;
	private String fri;
	private String sat;
	private String sun;
	private String launch;
	private String dinner;
	private String holiday;
	
	public InfoEntity() {
		super();
	}

	public InfoEntity(String code, String memberId, int viewCount, String regCheck, String businessNo, Date regDate,
			String businessName, String businessAddress, String businessTel, String location, String headContent,
			String bodyContents, String serviceContent, String site, String mon, String tue, String wed, String thu,
			String fri, String sat, String sun, String launch, String dinner, String holiday) {
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
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
		this.launch = launch;
		this.dinner = dinner;
		this.holiday = holiday;
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

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public String getTue() {
		return tue;
	}

	public void setTue(String tue) {
		this.tue = tue;
	}

	public String getWed() {
		return wed;
	}

	public void setWed(String wed) {
		this.wed = wed;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getFri() {
		return fri;
	}

	public void setFri(String fri) {
		this.fri = fri;
	}

	public String getSat() {
		return sat;
	}

	public void setSat(String sat) {
		this.sat = sat;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	public String getLaunch() {
		return launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}

	public String getDinner() {
		return dinner;
	}

	public void setDinner(String dinner) {
		this.dinner = dinner;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	@Override
	public String toString() {
		return "InfoEntity [code=" + code + ", memberId=" + memberId + ", viewCount=" + viewCount + ", regCheck="
				+ regCheck + ", businessNo=" + businessNo + ", regDate=" + regDate + ", businessName=" + businessName
				+ ", businessAddress=" + businessAddress + ", businessTel=" + businessTel + ", location=" + location
				+ ", headContent=" + headContent + ", bodyContents=" + bodyContents + ", serviceContent="
				+ serviceContent + ", site=" + site + ", mon=" + mon + ", tue=" + tue + ", wed=" + wed + ", thu=" + thu
				+ ", fri=" + fri + ", sat=" + sat + ", sun=" + sun + ", launch=" + launch + ", dinner=" + dinner
				+ ", holiday=" + holiday + "]";
	}
	
	
	
	

}
