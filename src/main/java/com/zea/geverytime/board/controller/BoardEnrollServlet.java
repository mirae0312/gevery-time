package com.zea.geverytime.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.zea.geverytime.common.MvcFileRenamePolicy;

/**
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		int max = 1024*1024*10; // 10메가바이트
		String encoding = "utf-8";
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		MultipartRequest multipartRequest = new MultipartRequest(request,saveDirectory,max,encoding,policy);
		
		String title = multipartRequest.getParameter("title");
		String writer = multipartRequest.getParameter("writer");
		String content = multipartRequest.getParameter("content");
		
		// 2. 업무처리
		
		// 3. 응답처리
		response.sendRedirect(request.getContextPath()+"/board/freeBoard");
		
	}

}
