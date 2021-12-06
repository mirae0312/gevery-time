package com.zea.geverytime.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.common.model.service.CommonService;

/**
 * Servlet implementation class CommonReportCheckServlet
 */
@WebServlet("/common/reportCheck")
public class CommonReportCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommonService cmService = new CommonService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String reportCode = request.getParameter("reportCode");
		
		int result = cmService.checkReported(user, reportCode);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
	}

}
