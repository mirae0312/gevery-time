package com.zea.geverytime.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.board.model.service.BoardService;
import com.zea.geverytime.board.model.vo.Board;
import com.zea.geverytime.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2. 업무처리
		Board board = boardService.selectOneBoard(no);
		List<BoardComment> list = null;
		if(board.getCommentCount()>0) {
			list = boardService.getBoardCommentList(no);
			System.out.println(list);
		}
		// 3. 응답처리
		request.setAttribute("comment",list);
		request.setAttribute("board", board);
		request
			.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp")
			.forward(request,response);
	
	}

}
