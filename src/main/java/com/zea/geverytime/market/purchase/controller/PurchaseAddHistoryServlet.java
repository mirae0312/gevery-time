package com.zea.geverytime.market.purchase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.purchase.model.service.PurchaseService;
import com.zea.geverytime.market.purchase.model.vo.PurchaseHistory;

/**
 * Servlet implementation class PurchaseAddHistoryServlet
 */
@WebServlet("/purchase/addHistory")
public class PurchaseAddHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PurchaseService purchaseService = new PurchaseService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		String uid = request.getParameter("uid");
		String muid = request.getParameter("muid");
		String state = request.getParameter("state");
		
		PurchaseHistory ph = new PurchaseHistory(0, null, memberId, totalPrice, uid, muid, state);
		
		// history 등록
		int result = purchaseService.insertPurchaseHistory(ph);
		
		// history 상세 등록
		int historyNo = purchaseService.getHistoryNo(uid);
		
		int countNum = Integer.parseInt(request.getParameter("countNum"));
		
		
		// 상품번호, 상품 개수 구하기
		for(int i = 1; i < countNum; i++) {
			int pdtNo = Integer.parseInt(request.getParameter("pdtNo"+i));
			int pdtCount = Integer.parseInt(request.getParameter("pdtCount"+i));
			//가져온 정보를 통해 상세내역 등록
			int historyDetailResult = purchaseService.insertPurchaseDetailHistory(historyNo, pdtNo, pdtCount);
		}
	
		
	}

}
