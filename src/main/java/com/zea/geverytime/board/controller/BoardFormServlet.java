package com.zea.geverytime.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFormServlet
 */
@WebServlet("/board/boardForm")
public class BoardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		// 3. 응답처리
		request
			.getRequestDispatcher("/WEB-INF/views/board/boardForm.jsp")
			.forward(request,response);
		}catch(Exception e) {
			throw e;
		}
	}

}
