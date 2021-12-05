package com.zea.geverytime.market.usedgoods.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.market.usedgoods.model.service.UsedGoodsService;

/**
 * Servlet implementation class UsedGoodBoardRequestAddServlet
 */
@WebServlet("/ugGoods/requestAdd")
public class UsedGoodBoardRequestAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsedGoodsService ugService = new UsedGoodsService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String memberId = request.getParameter("memberId");
		String content = request.getParameter("content");
		
		System.out.println("요청정보 : "+memberId+content);
		
		int result = ugService.addBoardRequest(boardNo, memberId, content);
		
		Map<String, Object> map = new HashMap<>();
		if(result > 0) {
			map.put("msg", "요청을 보냈습니다.");			
		} else {
			map.put("msg", "요청을 보내지 못했습니다.");
		}
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
	}

}
