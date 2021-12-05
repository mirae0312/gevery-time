package com.zea.geverytime.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.common.model.service.CommonService;
import com.zea.geverytime.customer.model.vo.ReportBoard;

/**
 * Servlet implementation class CommonReportFormServlet
 */
@WebServlet("/common/report")
public class CommonReportFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommonService commonService = new CommonService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String code = request.getParameter("code");
		System.out.println("code : " + code);
		request.setAttribute("code", code);
		request
			.getRequestDispatcher("/WEB-INF/views/common/reportForm.jsp")
			.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String code = request.getParameter("code");
			String head = request.getParameter("head");
			String body = request.getParameter("body");
			String memberId = request.getParameter("writer");
			ReportBoard report = new ReportBoard(0, head, body, code, null, memberId, null);
			System.out.println("[CommonReportFormServlet] report : " + report);
			
			int result = commonService.insertReport(report);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}

}
