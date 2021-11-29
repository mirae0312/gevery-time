package com.zea.geverytime.info.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InfoSalonServlet
 */
@WebServlet("/info/salon")
public class InfoSalonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int start = 1;
			int end = 5;
			
			// 인기 게시물
//			List<Info> popList = infoService.selectSalonPopList();
//			System.out.println("[InfoSalonServlet] + popList : " + popList);
			
			// 전체 게시물
//			List<Info> list = infoService.selectAllSalonList(start, end);
//			System.out.println("[InfoSalonServlet] + list : " + list);
			
//			if(popList != null)
//				request.setAttribute("popList", popList);
//			if(list != null)
//				request.setAttribute("list", list);
			request
				.getRequestDispatcher("/WEB-INF/views/info/salon.jsp")
				.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
