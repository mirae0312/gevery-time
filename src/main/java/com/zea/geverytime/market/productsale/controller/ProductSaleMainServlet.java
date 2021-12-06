package com.zea.geverytime.market.productsale.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductSaleMainServlet
 */
@WebServlet("/product/main")
public class ProductSaleMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String div = request.getParameter("div");
		if(div.equals("all")){
			div = "%%";
		}
		System.out.println("div= "+div);
		request.setAttribute("div", div);
		request.getRequestDispatcher("/WEB-INF/views/market/productSaleMain.jsp").forward(request, response);
	}

}
