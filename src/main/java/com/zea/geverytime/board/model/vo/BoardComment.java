package com.zea.geverytime.board.model.vo;

import java.sql.Date;

public class BoardComment {
	
	/**
	 * no number,
    or_code varchar2(100),
    comment_level number default 1, -- 댓글 1, 대댓글 2
    writer varchar2(15),
    content varchar2(2000),
    board_no number,
    comment_ref number, -- 대댓글인 경우, 참조하는 댓글번호, 댓글인 경우 NULL
    like_count number default 0,
    reg_date date default sysdate,
	 * 
	 */
	
	private int no;
	private String orCode;
	private int commentLevel;
	private String writer;
	private String content;
	private int boardNo;
	private int commentRef;
	private int likeCount;
	private Date regDate;
	
	
}
