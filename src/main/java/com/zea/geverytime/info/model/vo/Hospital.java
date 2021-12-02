package com.zea.geverytime.info.model.vo;

import java.io.Serializable;

public class Hospital implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String code;
	private String service;
	
	public Hospital() {
		super();
	}

	public Hospital(int no, String code, String service) {
		super();
		this.no = no;
		this.code = code;
		this.service = service;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "Hospital [no=" + no + ", code=" + code + ", service=" + service + "]";
	}
	
	

}
