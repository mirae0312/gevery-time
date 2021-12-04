package com.zea.geverytime.market.productsale.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zea.geverytime.common.MvcUtils;
import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.market.productsale.model.service.ProductSaleService;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

/**
 * Servlet implementation class ProductSaleMain
 */
@WebServlet("/product/productList")
public class ProductSaleMainListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 parameter 가져오기(div, onSale)
		String div = request.getParameter("selectedDiv");
		String state = request.getParameter("selectedOnSale");
		if(state.equals("true")) {
			state = "판매중";
		} else {
			state = "%%";
		}
		
		// 페이지바 처리
		int cPage = Integer.parseInt(request.getParameter("cPage"));
		
		int numPerPage = 10;
		int startNum = (cPage-1)*10+1;
		int endNum = cPage*numPerPage;
		
		Map<String, Object> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		// 페이지바에 맞는 게시물 가져오기
		List<ProductBoard> list = pdtService.getProductSaleBoardAll(startNum, endNum, div, state);
		
		// 게시물에 이미지 첨부
		for(ProductBoard board : list) {
			String orCode = board.getOrCode();
			List<Attachment> attachments = pdtService.getproductSaleBoardAttachment(orCode);
			board.setAttachments(attachments);
		}
		
		final int pageBarSize = 10;
		
		// 총 게시물 수 가져오기
		int totalContentCount = pdtService.getProductSaleBoardCount(div, state);
		
		String url = request.getRequestURI();
		
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, pageBarSize, totalContentCount, url);
		
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("pagebar", pagebar);
		jsonMap.put("list", list);
		jsonMap.put("totalContent", totalContentCount);
		
		System.out.println("jsonMap: " +jsonMap);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(jsonMap, response.getWriter());
	}

}
