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
import com.zea.geverytime.market.point.model.vo.PointHistory;

/**
 * Servlet implementation class PointAddHistory
 */
@WebServlet("/point/addPointHistory")
public class PointAddHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PointService pointService = new PointService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String div = request.getParameter("div");
		int pointVal = Integer.parseInt(request.getParameter("pointVal"));
		String history = request.getParameter("history");
		String uid = request.getParameter("purchaseUid");
		System.out.println("pointHistory : "+div+pointVal+history+uid);
		
		PointHistory ht = new PointHistory();
		if(div.equals("O")) {
			ht = new PointHistory(0, pointVal, 0, null, history, uid, div);			
		} else {
			ht = new PointHistory(0, 0, pointVal, null, history, uid, div);
		}
		
		int result = pointService.insertPointHistory(ht, memberId);
		System.out.println("pointHistory result : "+result);
		
		String msg = "";
		if(div.equals("O")) {
			if(result > 0) {
				msg = "포인트가 사용 되었습니다.";
			} else {
				msg = "포인트 사용에 실패했습니다.";
			}
		} else {
			if(result > 0) {
				msg = "포인트가 적립 되었습니다.";
			} else {
				msg = "포인트 적립에 실패했습니다.";
			}
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("msg", msg);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
	}

}
