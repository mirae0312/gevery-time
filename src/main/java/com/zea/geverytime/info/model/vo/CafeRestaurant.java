package com.zea.geverytime.info.model.vo;

import java.io.Serializable;

public class CafeRestaurant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String code;
	private String service;
	private String price;
	
	public CafeRestaurant() {
		super();
	}

	public CafeRestaurant(int no, String code, String service, String price) {
		super();
		this.no = no;
		this.code = code;
		this.service = service;
		this.price = price;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CafeRestaurant [no=" + no + ", code=" + code + ", service=" + service + ", price=" + price + "]";
	}
	
	

}
