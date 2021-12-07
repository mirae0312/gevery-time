package com.zea.geverytime.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.admin.model.service.AdminService;
import com.zea.geverytime.common.MvcUtils;
import com.zea.geverytime.customer.model.vo.ReportBoard;
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class AdminReportCheckServlet
 */
@WebServlet("/admin/reportCheckList")
public class AdminReportCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage = 1;
		try {
			try {
			// 사용자입력값
			cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {}
		
			System.out.println("[AdminList@Servlet] cPage = " + cPage);
			int numPerPage = 5;
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("end", end);

			List<ReportBoard> list = adminService.selectReportList(param);
			
			// 페이지바 호출 MvcUtils.getPagebar(cPage, numPerPage, pageBarSize, totalContentCount, url);
			final int pageBarSize = 10;
			int totalContentCount = adminService.reportTotalCount(param);
			String url = request.getRequestURI();
			
			String pagebar = MvcUtils.getPagebar(cPage, numPerPage, pageBarSize, totalContentCount, url);
			
			// ajax로 요청한 infolist
			
			Map<String, Object> jMap = new HashMap<>();
			jMap.put("list", list);
			jMap.put("pagebar", pagebar);
			
			System.out.println("[AdminReport@Servlet] list : " + list);			
			System.out.println("[AdminReport@Servlet] pagebar = " + pagebar);
						
			// 응답처리
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(jMap, response.getWriter());			
					
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	

}
