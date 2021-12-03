package com.zea.geverytime.market.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PurchaseMainPage
 */
@WebServlet("/purchase/purchasePage")
public class PurchaseMainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 구매자 id 받아오기
		String memberId = request.getParameter("memberId");
		System.out.println("memberId : "+memberId);
		
		// 선택된 상품 개수 가져오기
		int countNum = Integer.parseInt(request.getParameter("CountNum"));
		System.out.println("countNum : "+countNum);
		
		List<Map<String, Object>> list = new ArrayList<>();
		// 선택된 상품 번호, 가격, 개수 가져오기
		for(int i = 1; i <= countNum; i++) {
			int no = Integer.parseInt(request.getParameter("pdtNo"+i));
			int boardNo = Integer.parseInt(request.getParameter("pdtBoardNo"+i));
			int price = Integer.parseInt(request.getParameter("pdtPrice"+i));
			int count = Integer.parseInt(request.getParameter("pdtCount"+i));
			String title = request.getParameter("pdtTitle"+i);
			
			Map<String, Object> map = new HashMap<>();
			map.put("no", no);
			map.put("boardNo", boardNo);
			map.put("price", price);
			map.put("count", count);
			map.put("title", title);
			
			list.add(map);
		}
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/market/purchase/purchaseProductMainPage.jsp").forward(request, response);
	}

}
