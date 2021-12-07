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

import com.zea.geverytime.board.model.service.BoardService;
import com.zea.geverytime.board.model.vo.Board;
import com.zea.geverytime.common.MvcUtils;

/**
 * Servlet implementation class FreeboardMainlistServlet
 */
@WebServlet("/board/freeBoardList")
public class FreeboardMainlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 2. 업무처리
			int count =8;
			List<Board> popularList = boardService.getFreePopularList(count);
			
			
			request.setAttribute("popularList",popularList);
			request
				.getRequestDispatcher("/WEB-INF/views/board/freeBoardList.jsp")
				.forward(request, response);
			}catch(Exception e) {
			throw e;
		}
	}
}
