package com.zea.geverytime.info.model.vo;

public class Business {
	private String businessId;
	private String businessNo;
	private String bName;
	private String bTel;
	private String bAddress;
	private String location;
	public Business() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Business(String businessId, String businessNo, String bName, String bTel, String bAddress, String location) {
		super();
		this.businessId = businessId;
		this.businessNo = businessNo;
		this.bName = bName;
		this.bTel = bTel;
		this.bAddress = bAddress;
		this.location = location;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getbTel() {
		return bTel;
	}
	public void setbTel(String bTel) {
		this.bTel = bTel;
	}
	public String getbAddress() {
		return bAddress;
	}
	public void setbAddress(String bAddress) {
		this.bAddress = bAddress;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Business [businessId=" + businessId + ", businessNo=" + businessNo + ", bName=" + bName + ", bTel="
				+ bTel + ", bAddress=" + bAddress + ", location=" + location + "]";
	}
	
}
