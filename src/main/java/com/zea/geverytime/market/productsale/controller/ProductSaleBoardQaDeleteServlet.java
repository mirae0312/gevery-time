package com.zea.geverytime.market.productsale.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.productsale.model.service.ProductSaleService;

/**
 * Servlet implementation class ProductSaleBoardQaDeleteServlet
 */
@WebServlet("/product/qaDelete")
public class ProductSaleBoardQaDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentNo = Integer.parseInt(request.getParameter("delCommentNo"));
		int boardNo = Integer.parseInt(request.getParameter("delCommentBoardNo"));
		System.out.println("delComment : "+commentNo);
		
		int result = pdtService.productSaleBoardQaDelete(commentNo);
		System.out.println("QA delete result : "+result);
		
		response.sendRedirect(request.getContextPath()+"/product/boardView?no="+boardNo);
	}

}
