package com.zea.geverytime.member.model.vo;
import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String password;
	private String memberName;
	private String phone;
	private String address;
	private String  email;
	private String memberRole;
	private String memberType;
	private Date  birthday;
	
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String memberId, String password, String memberName, String phone, String address, String email,
			String memberRole, String memberType, Date birthday) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.memberRole = memberRole;
		this.memberType = memberType;
		this.birthday = birthday;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName + ", phone="
				+ phone + ", address=" + address + ", email=" + email + ", memberRole=" + memberRole + ", memberType="
				+ memberType + ", birthday=" + birthday + "]";
	}


}
