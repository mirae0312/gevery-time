package com.zea.geverytime.market.usedgoods.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.usedgoods.model.service.UsedGoodsService;

/**
 * Servlet implementation class UsedGoodTradeRequestAcceptServlet
 */
@WebServlet("/ugGoods/tradeReqAccept")
public class UsedGoodTradeRequestAcceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsedGoodsService ugService = new UsedGoodsService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int boardNo = Integer.parseInt(request.getParameter("thisBoardNo"));
		System.out.println("유저아이디 : "+userId);

		// 요청 수락 시 request state 변경
		int result = ugService.tradeRequestAccept(userId, boardNo);
		  
		// 게시물 상태 변경
		String state = "거래중";
		int stateResult = ugService.changeUgBoardState(boardNo, state);
		
		 System.out.println("result : "+result);
		  
		 request.getSession().setAttribute("msg", "요청이 수락되었습니다.");
		 response.sendRedirect(request.getContextPath()+"/ugGoods/boardView?boardNo="+boardNo);
		 }

}
