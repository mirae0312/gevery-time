package com.zea.geverytime.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FreeboardMainlistServlet
 */
@WebServlet("/board/freeMain")
public class FreeboardMainlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 2. 업무처리
		
		// 3. 응답처리
		request
			.getRequestDispatcher("/WEB-INF/views/board/freeBoard.jsp")
			.forward(request, response);
	}
}
