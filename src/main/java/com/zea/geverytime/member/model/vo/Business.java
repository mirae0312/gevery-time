package com.zea.geverytime.member.model.vo;

import java.io.Serializable;

public class Business implements Serializable{
	private static final long serialVersionUID = 1L;
	private String businessId;
	private String password;
	private String Name;
	private String email;
	private String businessNo;
	private String bName;
	private String bAddress;
	private String bTel;
	private String location;
	private String businessType;
	public Business() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Business(String businessId, String password, String name, String email, String businessNo, String bName,
			String bAddress, String bTel, String location, String businessType) {
		super();
		this.businessId = businessId;
		this.password = password;
		this.Name = name;
		this.email = email;
		this.businessNo = businessNo;
		this.bName = bName;
		this.bAddress = bAddress;
		this.bTel = bTel;
		this.location = location;
		this.businessType = businessType;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getbAddress() {
		return bAddress;
	}
	public void setbAddress(String bAddress) {
		this.bAddress = bAddress;
	}
	public String getbTel() {
		return bTel;
	}
	public void setbTel(String bTel) {
		this.bTel = bTel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	@Override
	public String toString() {
		return "Business [businessId=" + businessId + ", password=" + password + ", Name=" + Name + ", email=" + email
				+ ", businessNo=" + businessNo + ", bName=" + bName + ", bAddress=" + bAddress + ", bTel=" + bTel
				+ ", location=" + location + ", businessType=" + businessType + "]";
	}
	
};