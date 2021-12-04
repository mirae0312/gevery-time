package com.zea.geverytime.market.usedgoods.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.usedgoods.model.service.UsedGoodsService;
import com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard;

/**
 * Servlet implementation class UsedGoodBoardViewServlet
 */
@WebServlet("/ugGoods/boardView")
public class UsedGoodBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsedGoodsService ugService = new UsedGoodsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		UsedGoodsBoard board = ugService.getUgGoodsBoard(boardNo);
		
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/views/market/ugGoods/ugGoodsBoardView.jsp").forward(request, response);
	}
}
