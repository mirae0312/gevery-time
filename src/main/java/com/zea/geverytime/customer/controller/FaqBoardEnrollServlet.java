package com.zea.geverytime.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.customer.model.service.QnaBoardService;
import com.zea.geverytime.customer.model.vo.FaqBoard;
import com.zea.geverytime.customer.model.vo.QnaBoard;

/**
 * Servlet implementation class FaqBoardEnrollServlet
 */
@WebServlet("/customer/faqBoardEnroll")
public class FaqBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
				//사용자입력값
				 request.setCharacterEncoding("utf-8");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				String category = request.getParameter("category");
				
				FaqBoard faqBoard = new FaqBoard(0, title, null, content, category, null);
				
				System.out.println("[FaqBoardEnrollServlet] = "+ faqBoard);
				
			//업무로직
				int result = qnaBoardService.insertFaqBoard(faqBoard);
				String msg = result >0 ? "게시물이 등록되었습니다." : "게시물 등록 실패입니다";
				
			//redirect
				HttpSession session = request.getSession();
				session.setAttribute("msg", msg);
				//session.setAttribute("qnaBoard", qnaBoard);
				//String location = request.getContextPath()+"/customer/qnaBoardView?no=" + qnaBoard.getNo();
				String location = request.getContextPath()+"/customer/faqBoardList";
				response.sendRedirect(location);
				
			 }catch(Exception e) {
				 e.printStackTrace();
				 throw e;
			 }
				 
				
			}

		}
