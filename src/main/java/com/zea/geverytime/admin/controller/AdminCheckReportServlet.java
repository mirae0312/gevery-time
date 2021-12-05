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
import com.zea.geverytime.customer.model.vo.ReportBoard;
import com.zea.geverytime.myPage.model.vo.Purchase;

/**
 * Servlet implementation class AdminCheckReportServlet
 */
@WebServlet("/admin/reportCheck")
public class AdminCheckReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request
			.getRequestDispatcher("/WEB-INF/views/admin/reportCheck.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[AdminCheckReport@Servlet] doPost");
		String msg = null;
		int result = 0;
		try {
			
			String code = request.getParameter("code");
			String delCode = request.getParameter("delCode");
			String output = request.getParameter("output");
			System.out.println("[AdminCheck@Servlet] reportCode = " + code);
			System.out.println("[AdminCheck@Servlet] delCode = " + delCode);
			System.out.println("[AdminCheck@Servlet] output = " + output);
			
			result = adminService.checkReport(code, delCode, output);
			
			msg = (result > 0) ?
					"신고사항 전송 성공" : "신고사항 전송 실패";
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
