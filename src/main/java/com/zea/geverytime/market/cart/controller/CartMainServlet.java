package com.zea.geverytime.market.cart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.market.cart.model.service.CartService;
import com.zea.geverytime.market.cart.model.vo.Cart;
import com.zea.geverytime.market.productsale.model.service.ProductSaleService;

/**
 * Servlet implementation class CartMainServlet
 */
@WebServlet("/cart/main")
public class CartMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
	private ProductSaleService pdtService = new ProductSaleService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);
		
		List<Cart> cartlist = cartService.getCartList(memberId);
		
		for(Cart cart : cartlist) {
			String orCode = cart.getPdtBoard().getOrCode();
			List<Attachment> list = pdtService.getproductSaleBoardAttachment(orCode);
			cart.getPdtBoard().setAttachments(list);
		}
		
		System.out.println("cartList : "+cartlist);
		
		request.setAttribute("cartlist", cartlist);
		request.getRequestDispatcher("/WEB-INF/views/market/cart/cartMain.jsp").forward(request, response);
	}

}
