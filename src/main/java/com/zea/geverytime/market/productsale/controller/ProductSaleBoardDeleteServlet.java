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
 * Servlet implementation class ProductSaleBoardDeleteServlet
 */
@WebServlet("/product/productBoardDelete")
public class ProductSaleBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		ProductBoard board = pdtService.getProductSaleBoard(boardNo);
		List<Attachment> attachments = board.getAttachments();
		// 1. server attachment 삭제
		for(Attachment attachment : attachments) {
			String rfn = attachment.getRenamedFilename();
			File delFile = new File(getServletContext().getRealPath("/upload/market/productSale"), rfn);
			delFile.delete();
		}
		// 2. db attachment 삭제
		String orCode = board.getOrCode();
		int attachResult = pdtService.productBoardDeleteAttachment(orCode);
		System.out.println("AttachREsult : + "+attachResult);
		
		// productBoard 삭제
		int result = pdtService.productBoardDelete(boardNo);
		System.out.println("BoardDeleteResult : "+result);
		
		String msg = "";
		if(result > 0) {
			msg = "삭제되었습니다.";
		} else {
			msg = "삭제되지 않았습니다.";
		}
		
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath()+"/product/main?div=all");
	}

}
