package com.zea.geverytime.market.usedgoods.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.market.usedgoods.model.service.UsedGoodsService;
import com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard;

/**
 * Servlet implementation class UsedGoodBoardUpdateFormServlet
 */
@WebServlet("/ugGoods/boardUpdateForm")
public class UsedGoodBoardUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsedGoodsService ugService = new UsedGoodsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		UsedGoodsBoard board = ugService.getUgGoodsBoard(boardNo);
		
		String orCode = board.getOrCode();
		System.out.println("orCode? "+orCode);
		List<Attachment> attachments = ugService.getUgBoardAttachment(orCode);
		
		for(Attachment attach : attachments) {
			String fileName = attach.getRenamedFilename();
			File delFile = new File(getServletContext().getRealPath("/upload/market/UgSale"), fileName);
			boolean removed = delFile.delete();
			System.out.println("삭제여부? : "+removed);
		}
		
		int result = ugService.deleteAttachments(orCode);
		
		request.setAttribute("board", board);		
		request.getRequestDispatcher("/WEB-INF/views/market/ugGoods/ugGoodsBoardUpdateForm.jsp").forward(request, response);
	}

}
