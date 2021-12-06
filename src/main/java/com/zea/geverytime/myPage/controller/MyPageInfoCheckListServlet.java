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
import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.Info;
import com.zea.geverytime.myPage.model.service.MyPageService;

/**
 * Servlet implementation class MyPageInfoCheckList
 */
@WebServlet("/myPage/businessList")
public class MyPageInfoCheckListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyPageService myPageService = new MyPageService();   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cPage = 1;
			try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {}
			String memberId = request.getParameter("id");
			
			System.out.println("[MyPageInfoCheckList@Servlet] cPage = " + cPage);
			System.out.println("[MyPageInfoCheckList@Servlet] id : " + memberId);
			int numPerPage = 5;
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			
			Map<String, Object> param = new HashMap<>();
			param.put("memberId", memberId);
			param.put("start", start);
			param.put("end", end);

			List<Info> list = myPageService.selectInfoList(param);
			
			// 페이지바 호출 MvcUtils.getPagebar(cPage, numPerPage, pageBarSize, totalContentCount, url);
			final int pageBarSize = 10;
			int totalContentCount = myPageService.myPageinfoListCount(param);
			String url = request.getRequestURI();
			
			String pagebar = MvcUtils.getPagebar(cPage, numPerPage, pageBarSize, totalContentCount, url);
			
			// ajax로 요청한 infolist
			Map<String, Object> jMap = new HashMap<>();
			jMap.put("list", list);
			jMap.put("pagebar", pagebar);
			
			System.out.println("[AdminList@Servlet] list : " + list);			
			System.out.println("[AdminList@Servlet] pagebar = " + pagebar);
							
			
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new InfoBoardException("myPage 정보게시글불러오기 오류!", e);
		}
		
	}

}
