package com.zea.geverytime.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.InfoReview;

/**
 * Servlet implementation class InfoBoardLikeCountServlet
 */
@WebServlet("/info/likeCount")
public class InfoBoardLikeCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String state = request.getParameter("state");
			String code = (String) request.getParameter("code");
			String codeN = code.substring(2, 3);
			String memberId = (String) request.getParameter("memberId");
			
			int no = infoService.checkInfoLike(code, memberId);
			System.out.println("[infoBoardLikeCountServlet] no : " + no);
			if(no == 0) {
				int result = infoService.insertInfoLike(codeN, code, memberId);
//				System.out.println("[infoBoardLikeCountServlet] insertresult : " + result);
				response.setContentType("application/json; charset=utf-8");
				new Gson().toJson(result, response.getWriter());
			}else {
				int result = infoService.updateInfoLike(state, code, memberId);
//				System.out.println("[infoBoardLikeCountServlet] updateresult : " + result);
				response.setContentType("application/json; charset=utf-8");
				new Gson().toJson(result, response.getWriter());
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		
	}

}
