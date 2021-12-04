package com.zea.geverytime.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class AdminCheckInfoServlet
 */
@WebServlet("/admin/check")
public class AdminCheckInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String code = request.getParameter("code");
			String codeN = code.substring(2,3);		
			
			// 게시물 가져오기
			Info info = infoService.selectOneView(code, codeN);
//		System.out.println("[infoBoardViewServlet] info : " + info);
			
			// 사이트 null 방지
			String site1 = "";
			String site2 = "";
			String site = info.getSite();
			if(site != null && !site.isEmpty()) {
				String[] sites = site.split(",");
				if(sites.length == 2) {
					site1 = sites[0];
					site2 = sites[1];
					request.setAttribute("site1", site1);
					request.setAttribute("site2", site2);
				} else {
					site1 = site;
					request.setAttribute("site1", site1);
				}
			}
			// view단 처리
			System.out.println("[AdminCheckInfo@Servlet] doGet" + info);
			request.setAttribute("codeN", codeN);
			request.setAttribute("info", info);
			request
				.getRequestDispatcher("/WEB-INF/views/admin/infoCheck.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[AdminCheckInfo@Servlet] doPost");
		String msg = null;
		int result = 0;
		try {
			String code = request.getParameter("code");
			System.out.println("[AdminCheckInfo@Servlet] code = " + code);
			String in = "I";
			result = infoService.checkInfoTrue(code, in);
			System.out.println("[AdminCheckInfo@Servlet] in = " + in);
			
			msg = (result > 0) ?
					"정보승인성공" : "정보승인실패";
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
