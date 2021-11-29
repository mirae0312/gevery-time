package com.zea.geverytime.market.productsale.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.productsale.model.service.ProductSaleService;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

/**
 * Servlet implementation class ProductSaleMain
 */
@WebServlet("/product/main")
public class ProductSaleMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ProductBoard> list = pdtService.getProductSaleBoardAll();
		System.out.println("pdtSaleMain@list : "+list);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/market/productSaleMain.jsp").forward(request, response);
	}

}
