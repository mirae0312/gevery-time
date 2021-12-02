package com.zea.geverytime.info.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class InfoReview implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String r_code;
	private String code;
	private String member_id;
	private String recommend;
	private String headContent;
	private String content;
	private Date regDate;
	
	public InfoReview() {
		super();
	}

	public InfoReview(int no, String r_code, String code, String member_id, String recommend, String headContent,
			String content, Date regDate) {
		super();
		this.no = no;
		this.r_code = r_code;
		this.code = code;
		this.member_id = member_id;
		this.recommend = recommend;
		this.headContent = headContent;
		this.content = content;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getR_code() {
		return r_code;
	}

	public void setR_code(String r_code) {
		this.r_code = r_code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getHeadContent() {
		return headContent;
	}

	public void setHeadContent(String headContent) {
		this.headContent = headContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "InfoReview [no=" + no + ", r_code=" + r_code + ", code=" + code + ", member_id=" + member_id
				+ ", recommend=" + recommend + ", headContent=" + headContent + ", content=" + content + ", regDate="
				+ regDate + "]";
	}
	
	

}
