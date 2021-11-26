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
 * Servlet implementation class ProductSaleBoardEnroll
 */
@WebServlet("/productSale/boardEnroll")
public class ProductSaleBoardEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글정보
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("content");
		
		System.out.println("게시글 정보 : "+title+", "+author+", "+content);
		
		// 물품정보
		int pdtNo = Integer.parseInt(request.getParameter("pdtNo"));
		
		System.out.println("상품 번호 : " + pdtNo);
		
		// 상품 판매상태 가져오기
		
		
		// vo객체에 담기
		ProductBoard pdtBoard = new ProductBoard(0, null, title, content, null, author, pdtNo, null);
		
		// DB 등록 프로세스 진행
		int result = pdtService.productSaleBoardEnroll(pdtBoard);
		System.out.println("serv@result:" + result);
	}

}
