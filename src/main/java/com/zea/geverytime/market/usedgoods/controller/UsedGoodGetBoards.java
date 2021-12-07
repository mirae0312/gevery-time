package com.zea.geverytime.market.usedgoods.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.market.usedgoods.model.service.UsedGoodsService;
import com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard;

/**
 * Servlet implementation class UsedGoodGetBoards
 */
@WebServlet("/ugGoods/getBoards")
public class UsedGoodGetBoards extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsedGoodsService ugService = new UsedGoodsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int countNum = Integer.parseInt(request.getParameter("countNum"));
		
		List<UsedGoodsBoard> ugList = new ArrayList<>();
		for(int i = 1; i < countNum; i++) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"+i));
			UsedGoodsBoard board = ugService.getUgGoodsBoard(boardNo);
			String orCode = board.getOrCode();
			String state = ugService.getUgGoodsBoardState(boardNo);
			board.setState(state);
			List<Attachment> attachments = ugService.getUgBoardAttachment(orCode); 
			board.setAttachments(attachments);
			ugList.add(board);
		}
		System.out.println("UGLIST :" +ugList);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(ugList, response.getWriter());
		
	}

}
