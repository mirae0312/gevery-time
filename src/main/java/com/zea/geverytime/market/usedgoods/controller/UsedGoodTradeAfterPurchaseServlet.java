package com.zea.geverytime.market.usedgoods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.usedgoods.model.service.UsedGoodsService;

/**
 * Servlet implementation class UsedGoodTradeAfterPurchaseServlet
 */
@WebServlet("/ugGoods/boardStateChange")
public class UsedGoodTradeAfterPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsedGoodsService ugService = new UsedGoodsService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String state = request.getParameter("state");
		// 게시물 상태 변경
		int stateResult = ugService.changeUgBoardState(boardNo, state);
		System.out.println("결제 완료 시 상태 변경 성공 : "+stateResult);
	}

}
