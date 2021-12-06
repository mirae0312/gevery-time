package com.zea.geverytime.market.cart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.cart.model.service.CartService;
import com.zea.geverytime.market.cart.model.vo.WishList;

/**
 * Servlet implementation class WishListMainServlet
 */
@WebServlet("/wishList/main")
public class WishListMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		
		List<WishList> list = cartService.getWishList(memberId); 
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/market/cart/wishListMain.jsp").forward(request, response);
	}

}
