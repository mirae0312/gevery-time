package com.zea.geverytime.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.customer.model.service.QnaBoardService;

/**
 * Servlet implementation class QnaBoardDeleteServlet
 */
@WebServlet("/qnaBoard/qnaBoardDelete")
public class QnaBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 1.사용자입력처리
			int no = Integer.parseInt(request.getParameter("no"));
		
		
			int result = qnaBoardService.deleteQnaBoard(no);
			String msg = result > 0 ? "게시물 삭제 성공!" : "게시물 삭제 실패!";

			// 3. redirect : /mvc/board/boardList로 이동
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/customer/qnaBoardList");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	}
}

 
