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
 * Servlet implementation class UsedGoodBoardDeleteServlet
 */
@WebServlet("/ugGoods/deleteBoard")
public class UsedGoodBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsedGoodsService ugService = new UsedGoodsService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// board의 고유번호 조회
		UsedGoodsBoard board = ugService.getUgGoodsBoard(boardNo);
		String orCode = board.getOrCode();
		System.out.println("del orCode : "+orCode);
		
		
		// 첨부파일 삭제
		List<Attachment> attachs = ugService.getUgBoardAttachment(orCode);
		System.out.println("attachesDel : "+attachs);
		for(Attachment attach : attachs) {
			String rfn = attach.getRenamedFilename();
			File delFile = new File(request.getServletContext().getRealPath("/upload/market/UgSale"), rfn);
			System.out.println("contextPath : "+ request.getContextPath());
			System.out.println("servletContext.realPath : "+request.getServletContext().getRealPath("/upload/market/UgSale"));
			delFile.delete();
		}
		
		// DB에 저장된 board 삭제
		int result = ugService.deleteBoard(boardNo);
		
		String msg = "";
		if(result > 0) {
			msg = "삭제되었습니다.";
		} else {
			msg = "삭제에 실패했습니다.";
		}
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath()+"/ugGoods/main");
	}

}
