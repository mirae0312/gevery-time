package com.zea.geverytime.customer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.customer.model.service.QnaBoardService;

/**
 * Servlet implementation class FaqBoardDeleteServlet
 */
@WebServlet("/faqBoard/faqBoardDelete")
public class FaqBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.사용자입력처리
			int no = Integer.parseInt(request.getParameter("no"));
		
		
			int result = qnaBoardService.deleteFaqBoard(no);
			String msg = result > 0 ? "게시물이 삭제되었습니다." : "게시물 삭제에 실패했습니다.";

			// 3. redirect  
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/customer/faqBoardList");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	}
}

 
