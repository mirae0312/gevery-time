package com.zea.geverytime.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.customer.model.service.QnaBoardService;
import com.zea.geverytime.customer.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaBoardReplyServlet
 */
@WebServlet("/customer/qnaBoardReplyForm")
public class QnaBoardReplyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값
				int no = Integer.parseInt(request.getParameter("no"));
			 
				request.setAttribute("no", no);
		request
		.getRequestDispatcher("/WEB-INF/views/customer/qnaBoardReplyForm.jsp")
		.forward(request, response);
	}
 

}
