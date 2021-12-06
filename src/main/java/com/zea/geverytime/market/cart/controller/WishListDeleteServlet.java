package com.zea.geverytime.market.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.cart.model.service.CartService;

/**
 * Servlet implementation class WishListDeleteServlet
 */
@WebServlet("/cart/deleteWishList")
public class WishListDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		int result = cartService.deleteWishList(memberId, boardNo);
		
		String msg = "";
		if(result > 0) {
			msg = "찜목록에서 삭제되었습니다.";
		} else {
			msg = "삭제되지 않았습니다.";
		}
		
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath()+"/wishList/main?memberId="+memberId);
	}

}
