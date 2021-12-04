package com.zea.geverytime.customer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import com.zea.geverytime.common.MvcUtilsCustomer;
import com.zea.geverytime.customer.model.service.QnaBoardService;
import com.zea.geverytime.customer.model.vo.ReportBoard;

/**
 * Servlet implementation class ReportBoardListServlet
 */
@WebServlet("/customer/reportBoardList")
public class ReportBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
 
			List<ReportBoard> list = qnaBoardService.selectAllReportBoard();
	  
			//   view단 처리
			request.setAttribute("list", list);
			request
				.getRequestDispatcher("/WEB-INF/views/customer/reportBoardList.jsp")
				.forward(request, response);
 
		} catch (Exception e) {
			e.printStackTrace(); 
			throw e;  
		}
 
	}

}
