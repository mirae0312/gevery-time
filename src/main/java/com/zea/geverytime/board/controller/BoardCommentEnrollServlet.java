package com.zea.geverytime.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.board.model.service.BoardService;
import com.zea.geverytime.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentEnrollServlet
 */
@WebServlet("/board/boardCommentEnroll")
public class BoardCommentEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력처리
		String orCode = request.getParameter("orCode");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
		int commentRef = Integer.parseInt(request.getParameter("commentRef"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		// 2. 업무처리
		
		BoardComment bc = new BoardComment();
		bc.setOrCode(orCode);
		bc.setBoardNo(boardNo);
		bc.setCommentLevel(commentLevel);
		bc.setCommentRef(commentRef);
		bc.setWriter(writer);
		bc.setContent(content);
		System.out.println(bc.getOrCode());
//		int result = boardService.enrollBoardComment(bc);
		
		// 3. 응답처리
	}

}
