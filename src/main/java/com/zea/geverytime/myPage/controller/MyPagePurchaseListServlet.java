package com.zea.geverytime.myPage.controller;

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
import com.zea.geverytime.info.model.exception.InfoBoardException;
import com.zea.geverytime.myPage.model.service.MyPageService;
import com.zea.geverytime.myPage.model.vo.Purchase;

/**
 * Servlet implementation class MyPagePurchaseListServlet
 */
@WebServlet("/myPage/purchaseList")
public class MyPagePurchaseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyPageService myPageService = new MyPageService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cPage = 1;
			try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {}
			String memberId = request.getParameter("id");
			
			System.out.println("[MyPagePurchaseList@Servlet] cPage = " + cPage);
			System.out.println("[MyPagePurchaseList@Servlet] id : " + memberId);
			int numPerPage = 5;
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			Map<String, Object> param = new HashMap<>();
			param.put("memberId", memberId);
			param.put("start", start);
			param.put("end", end);

			List<Purchase> list = myPageService.getPurchase(param);
			
			// 페이지바 호출 MvcUtils.getPagebar(cPage, numPerPage, pageBarSize, totalContentCount, url);
			final int pageBarSize = 5;
			int totalContentCount = myPageService.getPurchaseCount(param);
			String url = request.getRequestURI();
			
			String pagebar = MvcUtils.getPagebar(cPage, numPerPage, pageBarSize, totalContentCount, url);
			
			// ajax로 요청한 infolist
			Map<String, Object> jMap = new HashMap<>();
			jMap.put("list", list);
			jMap.put("pagebar", pagebar);
			
			System.out.println("[MyPagePurchaseList@servlet] list : " + list);			
			System.out.println("[MyPagePurchaseList@servlet] pagebar = " + pagebar);
						
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(jMap, response.getWriter());
			
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new InfoBoardException("myPage 구매 내역불러오기 오류!", e);
		}
		
	}


}
