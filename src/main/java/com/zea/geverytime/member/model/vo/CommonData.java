package com.zea.geverytime.member.model.vo;



public class CommonData  {

	 protected String Id;
	 protected String password;
	 protected String name;
	 protected String email;
	
	 public CommonData() {
		super();
	}
	public CommonData(String id, String password, String name, String email) {
		super();
		Id = id;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	public String getMemberId() {
		return Id;
	}
	public void setMemberId(String id) {
		Id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return name;
	}
	public void setMemberName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "CommonData [Id=" + Id + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	 





}
