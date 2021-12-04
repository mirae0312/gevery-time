package com.zea.geverytime.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.customer.model.service.QnaBoardService;
import com.zea.geverytime.customer.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaBoardGetAnswer
 */
@WebServlet("/customer/getAnswer")
public class QnaBoardGetAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		int replyRef = Integer.parseInt(request.getParameter("replyRef"));
//		String writer = request.getParameter("writer");
//		String content = request.getParameter("content");
//		String title = request.getParameter("title");
		//List<QnaBoard> list = qnaBoardService.selectReplyList(no);
		
//		Map<String, Object> map = new HashMap<>();
//        map.put("replyRef", replyRef);
//        map.put("writer", writer);
//        map.put("content", content);
//        map.put("title", title);
//           
		
		
		// request.setAttribute("list", list);
//	
		QnaBoard qnaBoard2 = null;
		try {
		int no = Integer.parseInt(request.getParameter("no"));
		
		  qnaBoard2 = qnaBoardService.selectQnaBoardReply(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
        response.setContentType("application/json; charset=utf-8");
        new Gson().toJson(qnaBoard2, response.getWriter());
	}

}
