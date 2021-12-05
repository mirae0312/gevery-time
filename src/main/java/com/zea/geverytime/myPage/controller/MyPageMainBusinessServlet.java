package com.zea.geverytime.myPage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class MyPageMainBusinessServlet
 */
@WebServlet("/myPage/business")
public class MyPageMainBusinessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memberId = request.getParameter("id");
		request.setAttribute("memberId", memberId);
		
		request
			.getRequestDispatcher("/WEB-INF/views/myPage/businessInfo.jsp")
			.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String memberId = request.getParameter("id");
			System.out.println("[MyPageMainBusinessServlet] id : " + memberId);
			
			Info info = infoService.selectMyBusiness(memberId);
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(info, response.getWriter());	
			
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
