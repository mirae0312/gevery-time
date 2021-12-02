package com.zea.geverytime.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			String code = request.getParameter("code");
			String codeN = code.substring(2, 3);
			String memberId = request.getParameter("memberId");
			
			int no = infoService.checkInfoLike(code, memberId);
			if("0".equals(no)) {
				int result = infoService.insertInfoLike(codeN, code, memberId);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		
	}

}
