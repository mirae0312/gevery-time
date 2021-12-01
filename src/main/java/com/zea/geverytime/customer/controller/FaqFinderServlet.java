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

import com.zea.geverytime.customer.model.service.QnaBoardService;
import com.zea.geverytime.customer.model.vo.FaqBoard;

/**
 * Servlet implementation class FaqFinderServlet
 */
@WebServlet("/customer/categoryFinder")
public class FaqFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//1.사용자입력값처리
		String searchKeyword = request.getParameter("searchKeyword");
		//Map에 담기
		Map<String, Object> param = new HashMap<>();
		param.put("searchKeyword", searchKeyword);
		System.out.println("param@servlet=" + param);
		
		//2.업무로직
		List<FaqBoard> list = qnaBoardService.searchFaq(param);
		System.out.println("list@servlet=" + list);
		
		
		//3.view단 처리
		request.setAttribute("list", list);
		request
		.getRequestDispatcher("/WEB-INF/views/customer/faqBoardList.jsp")
		.forward(request, response);
	 
		
	}

}