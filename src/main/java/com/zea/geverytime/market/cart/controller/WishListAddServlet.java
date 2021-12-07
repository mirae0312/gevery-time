package com.zea.geverytime.market.cart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.market.cart.model.service.CartService;
import com.zea.geverytime.market.usedgoods.model.service.UsedGoodsService;
import com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard;

/**
 * Servlet implementation class WishListAddServlet
 */
@WebServlet("/wishList/add")
public class WishListAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
	private UsedGoodsService ugService = new UsedGoodsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String memberId = request.getParameter("memberId");
		
//		UsedGoodsBoard board = ugService.getUgGoodsBoard(boardNo);
//		String orCode = board.getOrCode();
//		
//		List<Attachment> attachments = ugService.getUgBoardAttachment(orCode);
//		
//		String thumbNail = "";
//		for(Attachment attachment : attachments) {
//			thumbNail = attachment.getRenamedFilename();
//			break;
//		}
		
		int result = cartService.addWishList(boardNo, memberId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
	}

}
