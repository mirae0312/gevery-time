package com.zea.geverytime.market.purchase.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PurchaseCompleteServlet
 */
@WebServlet("/purchase/Complete")
public class PurchaseCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String muid = request.getParameter("muid");
		String amount = request.getParameter("amount");
		
		System.out.println("결제완료페이지 : "+uid+", "+muid+", "+amount);
		
		request.setAttribute("uid", uid);
		request.setAttribute("muid", muid);
		request.setAttribute("amount", amount);
		
		request.getRequestDispatcher("/WEB-INF/views/market/purchase/purchaseComplete.jsp").forward(request, response);
	}

}
