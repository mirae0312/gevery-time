package com.zea.geverytime.info.model.vo;

import java.sql.Date;

public class Member {
	private String memberId;
	private String password;
	private String memberName;
	private Date  birthday;
	private String  email;
	private String phone;
	private String address;
	private String memberType;
	private String memberRole;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String memberId, String password, String memberName, Date birthday, String email, String phone,
			String address, String memberType, String memberRole) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.memberType = memberType;
		this.memberRole = memberRole;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName + ", birthday="
				+ birthday + ", email=" + email + ", phone=" + phone + ", address=" + address + ", memberType="
				+ memberType + ", memberRole=" + memberRole + "]";
	}
	
}