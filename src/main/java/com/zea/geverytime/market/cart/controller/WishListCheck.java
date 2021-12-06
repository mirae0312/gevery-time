package com.zea.geverytime.market.cart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.market.cart.model.service.CartService;

/**
 * Servlet implementation class WishListCheck
 */
@WebServlet("/wishList/check")
public class WishListCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CartService cartService = new CartService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		int result = cartService.wishListCheck(memberId, boardNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
	}

}
