package com.zea.geverytime.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class InfoModifyMainServlet
 */
@WebServlet("/info/modifyMain")
public class InfoModifyMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String code = request.getParameter("code");
			String codeN = request.getParameter("codeN");
			String id = request.getParameter("id");
			
			// 게시물 가져오기
			Info info = infoService.selectOneView(code, codeN);
//			System.out.println("[infoBoardViewServlet] info : " + info);
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
				}else {
					site1 = site;
					request.setAttribute("site1", site1);
				}
				
			}
			
			// view단 처리
			request.setAttribute("codeN", codeN);
			request.setAttribute("info", info);
			request
				.getRequestDispatcher("/WEB-INF/views/info/modifyMain.jsp")
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
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
