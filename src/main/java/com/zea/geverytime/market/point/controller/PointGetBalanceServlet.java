package com.zea.geverytime.market.point.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.market.point.model.service.PointService;

/**
 * Servlet implementation class PointGetBalanceServlet
 */
@WebServlet("/point/getBalance")
public class PointGetBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PointService pointService = new PointService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		
		// 포인트 잔액 받아오기
		int pointBal = pointService.getPointBalance(memberId);
		System.out.println("pointbal : "+pointBal);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pointBal", pointBal);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
	}

}
