package com.zea.geverytime.market.productsale.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.productsale.model.service.ProductSaleService;

/**
 * Servlet implementation class ProductSaleBoardAnswerEnroll
 */
@WebServlet("/product/answerEnroll")
public class ProductSaleBoardAnswerEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String awriter = request.getParameter("awriter");
		String acontent = request.getParameter("acontent");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));

		Map<String, Object> map = new HashMap<>();
		map.put("awriter", awriter);
		map.put("acontent", acontent);
		map.put("boardNo", boardNo);
		map.put("commentNo", commentNo);
		System.out.println("answerServ@map : "+map);
		
		int result = pdtService.productSaleBoardAnswerEnroll(map);
		System.out.println(result);
		
		response.sendRedirect(request.getContextPath()+"/product/boardView?no="+boardNo);
	}

}
