package com.zea.geverytime.common.controller;

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
import com.zea.geverytime.board.model.service.BoardService;
import com.zea.geverytime.board.model.vo.Board;

/**
 * Servlet implementation class MainPageBoardPopularListServlet
 */
@WebServlet("/common/mainBoardPopularList")
public class MainPageBoardPopularListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = 15;
		List<Board> freeList = boardService.getFreePopularList(count);
		List<Board> reviewList = boardService.getReviewPopularList(count);
		Map<String, Object> map = new HashMap<>();
		map.put("freeList", freeList);
		map.put("reviewList", reviewList);
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map,response.getWriter());
	}


}
