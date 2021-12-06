package com.zea.geverytime.info.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class InfoScrollServlet
 */
@WebServlet("/info/scrollList")
public class InfoScrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {		
			Info info = new Info();
			
			// 페이지 체크
			String board = request.getParameter("pageCheck");
			String n = request.getParameter("n");
			String sido = request.getParameter("sido");
			System.out.println("[InfoScrollServlet] + n : " + n);	
			System.out.println("[InfoScrollServlet] + sido : " + sido);	
						
			// 페이징
			final int numPerPage = 4;
			int page = Integer.parseInt(request.getParameter("page"));			
			int start = (page - 1) * numPerPage + 1;
			int end = page * numPerPage;
						
			// 전체 게시물
			List<Info> list = null;
//			for(Info info : list)
//				System.out.println("[infoScrollServlet] infoList : " + info);
			
			
			info.setLocation(sido);
			list = infoService.selectAllList(board, start, end, n, info);
			
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(list, response.getWriter());
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
