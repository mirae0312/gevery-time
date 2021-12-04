package com.zea.geverytime.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.admin.model.service.AdminService;
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class AdminListServlet
 */
@WebServlet("/admin/adminList")
public class AdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			request
				.getRequestDispatcher("/WEB-INF/views/admin/adminList.jsp")
				.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			List<Info> list = adminService.selectInfoBoard();
			System.out.println("[AdminListServlet] selectInfoBoard : " + list);
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(list, response.getWriter());	
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}

}
