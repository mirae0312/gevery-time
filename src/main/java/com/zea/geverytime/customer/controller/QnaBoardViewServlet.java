package com.zea.geverytime.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.customer.model.exception.CustomerBoardException;
import com.zea.geverytime.customer.model.service.QnaBoardService;
import com.zea.geverytime.customer.model.vo.QnaBoard;

/**
 * Servlet implementation class qnaBoardViewServlet
 */
@WebServlet("/customer/qnaBoardView")
public class QnaBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService(); 

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		//사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		//업무로직 
		 QnaBoard qnaBoard = qnaBoardService.selectOneQnaBoard(no);
		 System.out.println("[QnaBoardViewServlet] qnaboard = " + qnaBoard);
		 
		 
//		String content = qnaBoard.getContent().replaceAll("<", "&lt;").replaceAll("<", "&gt;");
//		content = content.replaceAll("\n","<br/>");
//		qnaBoard.setContent(content);
//	 
		 
		//3. jsp forwarding
		request.setAttribute("qnaBoard", qnaBoard);
		request.getRequestDispatcher("/WEB-INF/views/customer/qnaBoardView.jsp")
			   .forward(request, response);
	 
	} catch(NumberFormatException e) {
		throw new CustomerBoardException("유효한 게시글 번호가 아닙니다.", e);
	} catch(Exception e) {
		e.printStackTrace();
		throw e;
	}
}

}