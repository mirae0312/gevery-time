package com.zea.geverytime.board.controller;

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
import com.zea.geverytime.common.MvcUtils;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			int cPage = Integer.parseInt(request.getParameter("cPage"));
			String boardSelect = request.getParameter("boardSelect");
			String sort = request.getParameter("sort");
			System.out.println(cPage);
			System.out.println(sort);
			System.out.println(boardSelect);
			// 2. 업무처리
			// 1) 컨텐츠 부분 - 현재페이지, 페이지당 게시물 수 -> startnum, endnum구하기
			int numPerPage = 15;
			int startNum = (cPage-1)*numPerPage+1;
			int endNum = cPage*numPerPage;
			
			Map<String,Object> map = new HashMap<>();
			map.put("sort",sort);
			map.put("boardSelect", boardSelect);
			map.put("startNum", startNum);
			map.put("endNum", endNum);
			List<Board> list = boardService.selectAllContentList(map);
			
			// 2) 페이지 바 부분 - 총 게시물 개수, 현재 페이지, 페이지당 게시물 개수, 페이지바 크기, uri
			final int pageBarSize = 10;
			int totalContentCount = boardService.getTotalContentCount(map);
			String url = request.getRequestURI();
			
			String pagebar = MvcUtils.getPagebar(cPage,numPerPage,pageBarSize,totalContentCount,url);
			
			Map<String,Object> jsonMap = new HashMap<>();
			jsonMap.put("pagebar",pagebar);
			System.out.println(pagebar);
			jsonMap.put("list", list);
			System.out.println(list);
			// 3. 응답처리
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(jsonMap,response.getWriter());
			
		}catch(Exception e) {
			throw e;
		}
	}
}
