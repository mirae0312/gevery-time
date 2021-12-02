package com.zea.geverytime.info.model.vo;

import java.io.Serializable;

public class Salon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String code;
	private String smallBath;
	private String middleBath;
	private String specialBath;
	private String smallBathAnd;
	private String middleBathAnd;
	private String specialBathAnd;
	private String smallMachine;
	private String middleMachine;
	private String specialMachine;
	private String smallSpotting;
	private String middleSpotting;
	private String specialSpotting;
	private String smallScissors;
	private String middleScissors;
	private String specialScissors;
	
	public Salon() {
		super();
	}

	public Salon(int no, String code, String smallBath, String middleBath, String specialBath, String smallBathAnd,
			String middleBathAnd, String specialBathAnd, String smallMachine, String middleMachine,
			String specialMachine, String smallSpotting, String middleSpotting, String specialSpotting,
			String smallScissors, String middleScissors, String specialScissors) {
		super();
		this.no = no;
		this.code = code;
		this.smallBath = smallBath;
		this.middleBath = middleBath;
		this.specialBath = specialBath;
		this.smallBathAnd = smallBathAnd;
		this.middleBathAnd = middleBathAnd;
		this.specialBathAnd = specialBathAnd;
		this.smallMachine = smallMachine;
		this.middleMachine = middleMachine;
		this.specialMachine = specialMachine;
		this.smallSpotting = smallSpotting;
		this.middleSpotting = middleSpotting;
		this.specialSpotting = specialSpotting;
		this.smallScissors = smallScissors;
		this.middleScissors = middleScissors;
		this.specialScissors = specialScissors;
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

	public String getSmallBath() {
		return smallBath;
	}

	public void setSmallBath(String smallBath) {
		this.smallBath = smallBath;
	}

	public String getMiddleBath() {
		return middleBath;
	}

	public void setMiddleBath(String middleBath) {
		this.middleBath = middleBath;
	}

	public String getSpecialBath() {
		return specialBath;
	}

	public void setSpecialBath(String specialBath) {
		this.specialBath = specialBath;
	}

	public String getSmallBathAnd() {
		return smallBathAnd;
	}

	public void setSmallBathAnd(String smallBathAnd) {
		this.smallBathAnd = smallBathAnd;
	}

	public String getMiddleBathAnd() {
		return middleBathAnd;
	}

	public void setMiddleBathAnd(String middleBathAnd) {
		this.middleBathAnd = middleBathAnd;
	}

	public String getSpecialBathAnd() {
		return specialBathAnd;
	}

	public void setSpecialBathAnd(String specialBathAnd) {
		this.specialBathAnd = specialBathAnd;
	}

	public String getSmallMachine() {
		return smallMachine;
	}

	public void setSmallMachine(String smallMachine) {
		this.smallMachine = smallMachine;
	}

	public String getMiddleMachine() {
		return middleMachine;
	}

	public void setMiddleMachine(String middleMachine) {
		this.middleMachine = middleMachine;
	}

	public String getSpecialMachine() {
		return specialMachine;
	}

	public void setSpecialMachine(String specialMachine) {
		this.specialMachine = specialMachine;
	}

	public String getSmallSpotting() {
		return smallSpotting;
	}

	public void setSmallSpotting(String smallSpotting) {
		this.smallSpotting = smallSpotting;
	}

	public String getMiddleSpotting() {
		return middleSpotting;
	}

	public void setMiddleSpotting(String middleSpotting) {
		this.middleSpotting = middleSpotting;
	}

	public String getSpecialSpotting() {
		return specialSpotting;
	}

	public void setSpecialSpotting(String specialSpotting) {
		this.specialSpotting = specialSpotting;
	}

	public String getSmallScissors() {
		return smallScissors;
	}

	public void setSmallScissors(String smallScissors) {
		this.smallScissors = smallScissors;
	}

	public String getMiddleScissors() {
		return middleScissors;
	}

	public void setMiddleScissors(String middleScissors) {
		this.middleScissors = middleScissors;
	}

	public String getSpecialScissors() {
		return specialScissors;
	}

	public void setSpecialScissors(String specialScissors) {
		this.specialScissors = specialScissors;
	}

	@Override
	public String toString() {
		return "Salon [no=" + no + ", code=" + code + ", smallBath=" + smallBath + ", middleBath=" + middleBath
				+ ", specialBath=" + specialBath + ", smallBathAnd=" + smallBathAnd + ", middleBathAnd=" + middleBathAnd
				+ ", specialBathAnd=" + specialBathAnd + ", smallMachine=" + smallMachine + ", middleMachine="
				+ middleMachine + ", specialMachine=" + specialMachine + ", smallSpotting=" + smallSpotting
				+ ", middleSpotting=" + middleSpotting + ", specialSpotting=" + specialSpotting + ", smallScissors="
				+ smallScissors + ", middleScissors=" + middleScissors + ", specialScissors=" + specialScissors + "]";
	}

	
	
	
}
