package com.zea.geverytime.myPage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.myPage.model.service.MyPageService;
import com.zea.geverytime.myPage.model.vo.Purchase;

/**
 * Servlet implementation class MyPagePurchaseServlet
 */
@WebServlet("/myPage/PurchaseHistory")
public class MyPagePurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyPageService myPageService = new MyPageService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		System.out.println("[MyPagePurchase@Servlet] memberId = " + memberId);
		
		List<Purchase> list = myPageService.getPurchase(memberId);
		System.out.println("[MyPagePurchase@Servlet] list = " + list);
		
		request.setAttribute("list", list);
		request
			.getRequestDispatcher("/WEB-INF/views/myPage/myPageSalesList.jsp")
			.forward(request, response);
	}

}
