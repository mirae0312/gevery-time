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
 * Servlet implementation class QnaBoardUpdateServlet
 */
@WebServlet("/customer/qnaBoardUpdate")
public class QnaBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();
 
	//업데이트 폼 제공
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //사용자 입력값
		int no = Integer.parseInt(request.getParameter("no"));
		//업무로직
		QnaBoard qnaBoard = qnaBoardService.selectOneQnaBoard(no);
		System.out.println("[QnaBoardUpdateServlet] qnaBoard = " + qnaBoard);
		//view단 처리 
		request.setAttribute("qnaBoard", qnaBoard);
		request
		       .getRequestDispatcher("/WEB-INF/views/customer/QnaBoardUpdate.jsp")
		       .forward(request, response);
		
	}

 
	//디비 수정 작업
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		//사용자입력값 QnaBoard객체에 넣기
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		//String password = request.getParameter("password");
		String category = request.getParameter("category");
		
		QnaBoard qnaBoard = new QnaBoard(no, title, writer,null, content, 0, 0, category, null);
		
		int result = qnaBoardService.updateQnaBoard(qnaBoard);
		System.out.println("[QnaBoardUpdateServlet] result = " + result);
		String msg = result> 0? "게시글이 수정되었습니다." : "게시글 수정에 실패했습니다.";
		
		//리다이렉트
		request.getSession().setAttribute("msg", msg);
		//String location = request.getContextPath() + "/customer/qnaBoardList";
		String location = request.getContextPath() + "/customer/qnaBoardView?no=" + qnaBoard.getNo();
		response.sendRedirect(location);
		
		
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}
		
		 
		
		
	}

}
