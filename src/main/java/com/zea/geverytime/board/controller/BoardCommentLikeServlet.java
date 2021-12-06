package com.zea.geverytime.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentLikeServlet
 */
@WebServlet("/board/commentLike")
public class BoardCommentLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 입력값 처리
		String id = request.getParameter("id");
		int no = Integer.parseInt(request.getParameter("no"));
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("no", no);
		
		// 2. 업무처리
		int count = boardService.upCommentLike(map);
		System.out.println("count = " + count);
		// 3. 응답처리
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(count,response.getWriter());
		
		
		// 3. 응답처리
		
		
	
	
	}

}
