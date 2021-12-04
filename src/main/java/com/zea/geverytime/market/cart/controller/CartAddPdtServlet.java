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
import com.zea.geverytime.customer.model.vo.QnaBoard;
import com.zea.geverytime.market.cart.model.service.CartService;
import com.zea.geverytime.market.cart.model.vo.Cart;

/**
 * Servlet implementation class CartAddPdtServlet
 */
@WebServlet("/cart/addCart")
public class CartAddPdtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		System.out.println(memberId + boardNo);
		
		Cart cart = new Cart(memberId, boardNo);
		
		// cart 담기(중복확인 후 DB 저장)
		int result = cartService.insertIntoCart(cart);
		System.out.println("addCartServ@result : "+result);
		
		String msg = "";
		if(result > 0) {
			msg = "장바구니에 담았습니다. \n 장바구니로 이동하시겠습니까?";
		} else {
			msg = "중복 상품이 존재합니다. \n 장바구니로 이동하시겠습니까?";
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("msg", msg);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());				
	}

}
