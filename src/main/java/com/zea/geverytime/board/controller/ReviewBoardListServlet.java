package com.zea.geverytime.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.board.model.service.BoardService;
import com.zea.geverytime.board.model.vo.Board;

/**
 * Servlet implementation class ReviewBoardListServlet
 */
@WebServlet("/board/reviewBoardList")
public class ReviewBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 2. 업무처리
			int count = 8;
			List<Board> popularList = boardService.getReviewPopularList(count);
			System.out.println("popularList = "+popularList);
			
			request.setAttribute("popularList",popularList);
			request
				.getRequestDispatcher("/WEB-INF/views/board/reviewBoardList.jsp")
				.forward(request, response);
			}catch(Exception e) {
			throw e;
		}
	}	

}
