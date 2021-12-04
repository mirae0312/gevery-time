package com.zea.geverytime.market.productsale.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.market.productsale.model.service.ProductSaleService;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

/**
 * Servlet implementation class ProductSaleBoardUpdateServlet
 */
@WebServlet("/product/productBoardUpdateForm")
public class ProductSaleBoardUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("no"));
		
		ProductBoard board = pdtService.getProductSaleBoard(boardNo);
		
		// 기존 첨부파일 삭제
		// 1. server 삭제
		List<Attachment> attachments = board.getAttachments();
		for(Attachment attachment : attachments) {
			String rfn = attachment.getRenamedFilename();
			File delFile = new File(getServletContext().getRealPath("/upload/market/productSale"), rfn);
			boolean removed = delFile.delete();
		}
		// 2. db attachment 삭제
		String orCode = board.getOrCode();
		int result = pdtService.productBoardDeleteAttachment(orCode);
		
		
		request.setAttribute("board", board);
		
		request.getRequestDispatcher("/WEB-INF/views/market/productSaleBoardUpdateForm.jsp").forward(request, response);
		
	}

}
