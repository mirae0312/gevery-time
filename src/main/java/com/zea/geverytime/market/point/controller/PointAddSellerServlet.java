package com.zea.geverytime.market.point.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.market.point.model.service.PointService;
import com.zea.geverytime.market.point.model.vo.PointHistory;
import com.zea.geverytime.market.productsale.model.service.ProductSaleService;
import com.zea.geverytime.market.productsale.model.vo.Product;

/**
 * Servlet implementation class PointAddSellerServlet
 */
@WebServlet("/point/addSellerPoint")
public class PointAddSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();
	private PointService pointService = new PointService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int countNum = Integer.parseInt(request.getParameter("countNum"));
		for(int i = 1; i < countNum; i++) {
			int pdtNo = Integer.parseInt(request.getParameter("pdtNo"+i));
			int pdtCount = Integer.parseInt(request.getParameter("pdtCount"+i));
			
			Product pdt = pdtService.getProduct(pdtNo);
					
			String sellerId = pdt.getSellerId();
			int price = pdt.getPdtPrice();
			
			int withdrawVal = (int)Math.round((price*pdtCount)/100*97/10) * 10;
			
			PointHistory ht = new PointHistory();
			ht.setDiv("I");
			ht.setDeposit(withdrawVal);
			ht.setHistory("판매자 상품 판매 : "+pdtNo);
			ht.setPurchaseUid(null);
			
			int result = pointService.insertPointHistory(ht, sellerId);
		}
	}

}
