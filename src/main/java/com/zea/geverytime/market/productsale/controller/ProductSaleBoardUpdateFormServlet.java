package com.zea.geverytime.market.productsale.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.productsale.model.service.ProductSaleService;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

/**
 * Servlet implementation class ProductSaleBoardUpdateServlet
 */
@WebServlet("/product/productBoardUpdate")
public class ProductSaleBoardUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("no"));
		
		ProductBoard board = pdtService.getProductSaleBoard(boardNo);
		
		request.setAttribute("board", board);
		
		request.getRequestDispatcher("/WEB-INF/views/market/productSaleBoardUpdateForm.jsp").forward(request, response);
		
	}

}
