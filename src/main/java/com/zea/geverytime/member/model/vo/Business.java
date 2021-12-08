package com.zea.geverytime.member.model.vo;

import java.io.Serializable;

public class Business extends CommonData implements Serializable{
	private static final long serialVersionUID = 1L;
		
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

	public Business(String id, String password, String name, String email) {
		super(id, password, name, email);
		// TODO Auto-generated constructor stub
	}

	public Business(String Id,
			String password,
			String name,
			String email, 
			String bName, 
			String bAddress,
			String businessNo,
			String bTel,
			String location,
			String businessType  ) {
		
			super(Id, password, name, email);
			
			this.businessNo = businessNo;
			this.bName = bName;
			this.bAddress = bAddress;
			this.bTel  = bTel;
			this.location = location;
			this.businessType = businessType;
	}

	public Business(String string, String string2, String string3, String string4, Object setBusinessNo, String string5,
			Object setbAddress, String string6, String string7, String string8) {
		// TODO Auto-generated constructor stub
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Business [businessNo=" + businessNo + ", bName=" + bName + ", bAddress=" + bAddress + ", bTel=" + bTel
				+ ", location=" + location + ", businessType=" + businessType + ", Id=" + Id + ", password=" + password
				+ ", name=" + name + ", email=" + email + "]";
	}
	

};