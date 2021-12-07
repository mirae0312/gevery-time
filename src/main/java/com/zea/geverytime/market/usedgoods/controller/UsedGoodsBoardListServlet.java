package com.zea.geverytime.market.usedgoods.controller;

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
import com.zea.geverytime.market.usedgoods.model.service.UsedGoodsService;
import com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard;

/**
 * Servlet implementation class UsedGoodsBoardListServlet
 */
@WebServlet("/ugGoods/boardList")
public class UsedGoodsBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsedGoodsService ugService = new UsedGoodsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage = Integer.parseInt(request.getParameter("cPage"));
		
		// 검색 조건
		String keyword = request.getParameter("searchKeyword");
		if(keyword == null || keyword.equals("")) {
			keyword = "%%";
		}
		String type = request.getParameter("searchType");
		
		int numPerPage = 10;
		int startNum = (cPage-1)*10+1;
		int endNum = cPage*numPerPage;
		
		Map<String, Object> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<UsedGoodsBoard> list = ugService.getProductSaleBoardAll(startNum, endNum, keyword, type);
		System.out.println("LIST : "+list);
		// board에 attachment 첨부
		for(UsedGoodsBoard board : list) {
			String orCode = board.getOrCode();
			List<Attachment> attachments = ugService.getUgBoardAttachment(orCode);
			board.setAttachments(attachments);
		}
		
		final int pageBarSize = 10;
		
		int totalContentCount = ugService.getUgBoardCount();
		
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
