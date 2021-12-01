package com.zea.geverytime.market.productsale.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.market.productsale.model.service.ProductSaleService;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

/**
 * Servlet implementation class ProductSaleBoardView
 */
@WebServlet("/product/boardView")
public class ProductSaleBoardView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 게시글 번호 통해서 board 정보 가져오기(첨부파일 포함)
		ProductBoard board = pdtService.getProductSaleBoard(no);
		
		// 댓글 가져오기
		List<Map<String, Object>> questions = pdtService.getProductSaleBoardQuestion(no);
		
		// set
		request.setAttribute("questions", questions);
		request.setAttribute("board", board);
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/market/productSaleBoardView.jsp").forward(request, response);
	}

}
