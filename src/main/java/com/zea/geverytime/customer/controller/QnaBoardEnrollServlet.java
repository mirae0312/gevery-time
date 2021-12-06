package com.zea.geverytime.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.customer.model.service.QnaBoardService;
import com.zea.geverytime.customer.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaBoardEnrollServlet
 */
@WebServlet("/customer/qnaBoardEnroll")
public class QnaBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();

	/**
	 *  
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 try {
		//사용자입력값
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		 String password = request.getParameter("password");
		String category = request.getParameter("category");
		
		QnaBoard qnaBoard = new QnaBoard(0, title, writer, password, content, 0, 0, category, null);
		
		System.out.println("[QnaBoardEnrollServlet] = "+ qnaBoard);
		
	//업무로직
		int result = qnaBoardService.insertQnaBoard(qnaBoard);
		String msg = result >0 ? "질문글이 등록되었습니다." : "질문글 등록 실패입니다";
		
	//redirect
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		session.setAttribute("qnaBoard", qnaBoard);
		//String location = request.getContextPath() + "/customer/qnaBoardView?no=" + qnaBoard.getNo();
		  String location = request.getContextPath()+"/customer/qnaBoardList";
		 
		response.sendRedirect(location);
		
	 }catch(Exception e) {
		 e.printStackTrace();
		 throw e;
	 }
		 
		
	}

}
