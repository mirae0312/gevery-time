package com.zea.geverytime.market.productsale.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.productsale.model.service.ProductSaleService;

/**
 * Servlet implementation class ProductSaleOnsaleProductOptionChangeServlet
 */
@WebServlet("/product/onsaleProductOptionChange")
public class ProductSaleOnsaleProductOptionChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pdtNo = Integer.parseInt(request.getParameter("pdtNo"));
		String colname = request.getParameter("colname");
		String val = request.getParameter("val");
		System.out.println("optionChangeServ@col, val : "+colname+val+pdtNo);
		
		// 변경요청
		int result = pdtService.productOptionChange(colname, val, pdtNo);
		
		response.sendRedirect(request.getContextPath()+"/product/onsaleProduct");
	}

}
