package com.zea.geverytime.info.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.zea.geverytime.common.model.vo.Attachment;

public class InfoReview implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String rCode;
	private String code;
	private String memberId;
	private String recommend;
	private String headContent;
	private String content;
	private Date regDate;
	private List<Attachment> attachments;
	
	public InfoReview() {
		super();
	}

	public InfoReview(int no, String rCode, String code, String memberId, String recommend, String headContent,
			String content, Date regDate, List<Attachment> attachments) {
		super();
		this.no = no;
		this.rCode = rCode;
		this.code = code;
		this.memberId = memberId;
		this.recommend = recommend;
		this.headContent = headContent;
		this.content = content;
		this.regDate = regDate;
		this.attachments = attachments;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getrCode() {
		return rCode;
	}

	public void setrCode(String rCode) {
		this.rCode = rCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "InfoReview [no=" + no + ", rCode=" + rCode + ", code=" + code + ", memberId=" + memberId
				+ ", recommend=" + recommend + ", headContent=" + headContent + ", content=" + content + ", regDate="
				+ regDate + ", attachments=" + attachments + "]";
	}

	
}
