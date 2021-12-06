package com.zea.geverytime.market.cart.model.vo;

import java.io.Serializable;

import com.zea.geverytime.common.model.vo.Attachment;

public class WishList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String memberId;
	private int boardNo;
	private Attachment attachment;
	
	public WishList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WishList(String memberId, int boardNo) {
		super();
		this.memberId = memberId;
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	
	
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	@Override
	public String toString() {
		return "WishList [memberId=" + memberId + ", boardNo=" + boardNo + "]";
	}
	
	
}
