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
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class AdminReportServlet
 */
@WebServlet("/admin/reportList")
public class AdminReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("/WEB-INF/views/admin/adminReportList.jsp")
		.forward(request, response);
	}
}
