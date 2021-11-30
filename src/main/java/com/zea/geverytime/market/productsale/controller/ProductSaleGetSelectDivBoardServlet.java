package com.zea.geverytime.market.productsale.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.market.productsale.model.service.ProductSaleService;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

/**
 * Servlet implementation class ProductSaleGetSelectDivBoardServlet
 */
@WebServlet("/product/getSelectDivList")
public class ProductSaleGetSelectDivBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String div = request.getParameter("div");
		System.out.println("DivBoardListServ@div : "+div);
		
		List<ProductBoard> list = pdtService.getSelectedDivBoardList(div);
		System.out.println("SelectedDivBoardListServlet@list : "+ list);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println("jsonStr : "+jsonStr);
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonStr);
	}
}
