package com.zea.geverytime.market.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.cart.model.service.CartService;

/**
 * Servlet implementation class CartDeleteServlet
 */
@WebServlet("/cart/deleteCart")
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("delCartLoginMember");
		int countNum = Integer.parseInt(request.getParameter("delCartCountNum"));
		List<Integer> list = new ArrayList<>();
		
		for(int i = 1; i <= countNum; i++) {
			int boardNo = Integer.parseInt(request.getParameter("delCart"+i));
			System.out.println("boardNo");
			list.add(boardNo);
		}
		
		int result = cartService.deleteCart(list, memberId);
		System.out.println("cartDelResult : "+result);
	}

}
