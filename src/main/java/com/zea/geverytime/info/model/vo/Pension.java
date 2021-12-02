package com.zea.geverytime.info.model.vo;

import java.io.Serializable;

public class Pension implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String code;
	private String room;
	private String price1;
	private String price2;
	private String price3;
	private String price4;
	private String price5;
	private String price6;
	
	public Pension() {
		super();
	}

	public Pension(int no, String code, String room, String price1, String price2, String price3, String price4,
			String price5, String price6) {
		super();
		this.no = no;
		this.code = code;
		this.room = room;
		this.price1 = price1;
		this.price2 = price2;
		this.price3 = price3;
		this.price4 = price4;
		this.price5 = price5;
		this.price6 = price6;
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

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public String getPrice3() {
		return price3;
	}

	public void setPrice3(String price3) {
		this.price3 = price3;
	}

	public String getPrice4() {
		return price4;
	}

	public void setPrice4(String price4) {
		this.price4 = price4;
	}

	public String getPrice5() {
		return price5;
	}

	public void setPrice5(String price5) {
		this.price5 = price5;
	}

	public String getPrice6() {
		return price6;
	}

	public void setPrice6(String price6) {
		this.price6 = price6;
	}

	@Override
	public String toString() {
		return "Pension [no=" + no + ", code=" + code + ", room=" + room + ", price1=" + price1 + ", price2=" + price2
				+ ", price3=" + price3 + ", price4=" + price4 + ", price5=" + price5 + ", price6=" + price6 + "]";
	}
	
	

}
