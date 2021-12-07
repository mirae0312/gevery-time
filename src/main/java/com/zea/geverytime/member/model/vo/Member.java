package com.zea.geverytime.member.model.vo;
import java.io.Serializable;
import java.sql.Date;

public class Member extends CommonData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	

	private String phone;
	private String address;
	private String memberRole;
	private String memberType;
	private Date  birthday;
	
	
	public Member(String Id, String password,
			String name,
			String phone,
			String address,
			String email, 
			String memberRole,
			String memberType,
			Date birthday) {
		super(Id,password,name,email);
	
		this.phone = phone;
		this.address = address;
		this.memberRole= memberRole;
		this.memberType = memberType;
		this.birthday  = birthday;
	
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getMemberRole() {
		return memberRole;
	}


	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}


	public String getMemberType() {
		return memberType;
	}


	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Member [phone=" + phone + ", address=" + address + ", memberRole=" + memberRole + ", memberType="
				+ memberType + ", birthday=" + birthday + ", Id=" + Id + ", password=" + password + ", name=" + name
				+ ", email=" + email + "]";
	}

	
	
	
}
