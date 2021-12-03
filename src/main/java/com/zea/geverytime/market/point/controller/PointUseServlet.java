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
 * Servlet implementation class PointUseServlet
 */
@WebServlet("/point/usePoint")
public class PointUseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PointService pointService = new PointService();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int usePoint = Integer.parseInt(request.getParameter("point"));
		
		int result = pointService.withdrawPoint(memberId, usePoint);
		System.out.println("usePointResult : "+result);
		
		int pointNo = pointService.getPointNo(memberId);
		System.out.println("pointUse : "+pointNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pointNo", pointNo);
		map.put("usePoint", usePoint);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
	}

}
