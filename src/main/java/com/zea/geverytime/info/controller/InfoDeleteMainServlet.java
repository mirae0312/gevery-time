package com.zea.geverytime.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.info.model.service.InfoService;

/**
 * Servlet implementation class InfoDeleteMainServlet
 */
@WebServlet("/info/deleteMain")
public class InfoDeleteMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			HttpSession session = request.getSession();
			
			String code = request.getParameter("code");

			int result = infoService.deleteInfoMain(code);
			
			session.setAttribute("msg", "게시물 삭제 성공!");
			String location = request.getContextPath() + "/";
			response.sendRedirect(location);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
