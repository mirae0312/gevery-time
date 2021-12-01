package com.zea.geverytime.market.productsale.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.productsale.model.service.ProductSaleService;
import com.zea.geverytime.market.productsale.model.vo.Product;

/**
 * Servlet implementation class ProductSaleOnsaleProductServlet
 */
@WebServlet("/product/onsaleProduct")
public class ProductSaleOnsaleProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sellerId = request.getParameter("sellerId");
		String state = "%%";
		System.out.println(sellerId);
	
		// 판매자 상품목록 받아오기
		List<Product> list = pdtService.getSellerProduct(sellerId, state);
		System.out.println("serv@@list: "+list);
		
		// 저장 및 전달
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/market/productSaleOnsaleProduct.jsp").forward(request, response);
	}

}
