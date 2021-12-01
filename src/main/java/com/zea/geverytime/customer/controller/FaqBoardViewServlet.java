package com.zea.geverytime.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.customer.model.exception.CustomerBoardException;
import com.zea.geverytime.customer.model.service.QnaBoardService;
import com.zea.geverytime.customer.model.vo.FaqBoard;

/**
 * Servlet implementation class FaqBoardViewServlet
 */
@WebServlet("/customer/faqBoardView")
public class FaqBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService(); 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//사용자 입력값 처리
			int no = Integer.parseInt(request.getParameter("no"));
			 
			//업무로직 
			 FaqBoard faqBoard = qnaBoardService.selectOneFaqBoard(no);
			 System.out.println("[FaqBoardViewServlet] faqboard = " + faqBoard);
			 
			//3. jsp forwarding
			request.setAttribute("faqBoard", faqBoard);
			request.getRequestDispatcher("/WEB-INF/views/customer/faqBoardView.jsp")
				   .forward(request, response);
		 
		} catch(NumberFormatException e) {
			throw new CustomerBoardException("유효한 게시글 번호가 아닙니다.", e);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	}