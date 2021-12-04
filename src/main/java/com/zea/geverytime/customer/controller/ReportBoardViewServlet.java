package com.zea.geverytime.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.customer.model.exception.CustomerBoardException;
import com.zea.geverytime.customer.model.service.QnaBoardService;
import com.zea.geverytime.customer.model.vo.ReportBoard;

/**
 * Servlet implementation class ReportBoardViewServlet
 */
@WebServlet("/customer/reportBoardView")
public class ReportBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService(); 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//사용자 입력값 처리
			int no = Integer.parseInt(request.getParameter("no"));
			 
			//업무로직 
			 ReportBoard reportBoard = qnaBoardService.selectOneReportBoard(no);
			 System.out.println("[ReportBoardViewServlet] reportBoard= " + reportBoard);
			 
			//3. jsp forwarding
			request.setAttribute("reportBoard", reportBoard);
			request.getRequestDispatcher("/WEB-INF/views/customer/reportBoardView.jsp")
				   .forward(request, response);
		 
		} catch(NumberFormatException e) {
			throw new CustomerBoardException("유효한 게시글 번호가 아닙니다.", e);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	}