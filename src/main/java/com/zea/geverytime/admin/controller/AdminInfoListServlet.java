package com.zea.geverytime.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.zea.geverytime.admin.model.service.AdminService;
import com.zea.geverytime.common.MvcUtils;
import com.zea.geverytime.info.model.vo.Info;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.admin.model.service.AdminService;

/**
 * Servlet implementation class AdminInfoList
 */
@WebServlet("/admin/adminInfoList")
public class AdminInfoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage = 1;
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

			List<Info> list = adminService.selectInfoBoard(param);
			
			// 페이지바 호출 MvcUtils.getPagebar(cPage, numPerPage, pageBarSize, totalContentCount, url);
			final int pageBarSize = 10;
			int totalContentCount = adminService.adminTotalCount(param);
			String url = request.getRequestURI();
			
			String pagebar = MvcUtils.getPagebar(cPage, numPerPage, pageBarSize, totalContentCount, url);
			
			// ajax로 요청한 infolist
			
			Map<String, Object> jMap = new HashMap<>();
			jMap.put("list", list);
			jMap.put("pagebar", pagebar);
			
			System.out.println("[AdminList@Servlet] list : " + list);			
			System.out.println("[AdminList@Servlet] pagebar = " + pagebar);
						
			// 응답처리
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(jMap, response.getWriter());
	}

}
