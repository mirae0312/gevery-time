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
 * Servlet implementation class QnaBoardReplyEnrollServlet
 */
@WebServlet("/customer/qnaBoardReplyEnroll")
public class QnaBoardReplyEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값
			
				int replyLevel = Integer.valueOf(request.getParameter("replyLevel"));
				int replyRef = Integer.valueOf(request.getParameter("replyRef"));
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				//String password = request.getParameter("password");
				String category = request.getParameter("category");
				
				QnaBoard qnaBoard = new QnaBoard(0, title, writer, null, content, replyLevel, replyRef, category, null);
				System.out.println("[BoardReplyEnrollServlet] qnaBoard = " + qnaBoard);
				
				// 2.업무로직
				int result = qnaBoardService.insertQnaBoardReply(qnaBoard);
				String msg = result > 0 ? " 등록 성공!" : " 등록 실패!";
				request.getSession().setAttribute("msg", msg);
				
				// 답변완료 시 원글 [답변완료] 추가
				int addState = qnaBoardService.addQnaBoardState(replyRef);
				
				System.out.println("QnaBoardReplyServ@ addState Result : " + addState);
				
				
				 
				// 3.redirect: 게시글상세페이지  
				 String location = request.getContextPath() + "/customer/qnaBoardList";
				//String location = request.getContextPath() + "/customer/qnaBoardView?no=" + qnaBoard.getNo();
				response.sendRedirect(location);
				
				
				
				
				
				
				
				
				
				
				
	}

}