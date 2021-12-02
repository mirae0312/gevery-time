package com.zea.geverytime.myPage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.member.model.vo.Business;

/**
 * Servlet implementation class MyPageMainServlet
 */
@WebServlet("/myPage/myPageMain")
public class MyPageMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Business loginBusiness = (Business) session.getAttribute("loginBusiness");
		
		request
			.getRequestDispatcher("/WEB-INF/views/myPage/myPageMain.jsp")
			.forward(request, response);
	}

}
