package com.zea.geverytime.market.usedgoods.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.usedgoods.model.service.UsedGoodsService;

/**
 * Servlet implementation class UsedGoodAddSellerPointServlet
 */
@WebServlet("/ugGoods/addSellerPoint")
public class UsedGoodAddSellerPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsedGoodsService ugService = new UsedGoodsService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sellerId = request.getParameter("sellerId");
		int pointVal = Integer.parseInt(request.getParameter("pointVal"));
		
		//int result = ugService.addSellerPoint(sellerId, pointVal);
	}

}
