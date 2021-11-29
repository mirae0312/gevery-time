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
 * Servlet implementation class ProductSaleBoardQuestionEnrollServlet
 */
@WebServlet("/product/questionEnroll")
public class ProductSaleBoardQuestionEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer");
		String title = request.getParameter("qtitle");
		String qcontent = request.getParameter("qcontent");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		Map<String, Object> map = new HashMap<>();
		map.put("writer", writer);
		map.put("title", title);
		map.put("qcontent", qcontent);
		map.put("boardNo", boardNo);
		
		int result = pdtService.productSaleBoardQuestionEnroll(map);
		System.out.println("answerEnrollServ@result : "+result);
		
		response.sendRedirect(request.getContextPath()+"/product/boardView?no="+boardNo);
	}

}
