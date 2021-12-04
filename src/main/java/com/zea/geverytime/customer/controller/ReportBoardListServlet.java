package com.zea.geverytime.customer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import com.zea.geverytime.common.MvcUtilsCustomer;
import com.zea.geverytime.customer.model.service.QnaBoardService;
import com.zea.geverytime.customer.model.vo.ReportBoard;

/**
 * Servlet implementation class ReportBoardListServlet
 */
@WebServlet("/customer/reportBoardList")
public class ReportBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자입력값
//			final int numPerPage = 8;
//			int cPage = 1;
//			try {
//				cPage = Integer.parseInt(request.getParameter("cPage"));
//			} catch (NumberFormatException e) {}
//			int start = (cPage - 1) * numPerPage + 1; 
//			int end = cPage * numPerPage;
//			Map<String, Integer> param = new HashMap<>();
//			param.put("start", start);
//			param.put("end", end);
			
			// 2. 업무로직
			// 2-a. content영역 : 페이징쿼리
			List<ReportBoard> list = qnaBoardService.selectAllReportBoard();
			//System.out.println("list@servlet = " + list);
			
			// 2-b. pagebar영역 : MvcUtils.getPagebar호출
			// totalContent
			// url
//			int totalContent = qnaBoardService.selectTotalReportBoardCount();
//			String url = request.getRequestURI(); // /mvc/board/boardList
//			String pagebar = MvcUtilsCustomer.getPagebar(cPage, numPerPage, totalContent, url);
			//System.out.println("pagebar@servlet = " + pagebar);
			
			
			// 3. view단 처리
			request.setAttribute("list", list);
//			request.setAttribute("pagebar", pagebar);
			request
				.getRequestDispatcher("/WEB-INF/views/customer/reportBoardList.jsp")
				.forward(request, response);
 
		} catch (Exception e) {
			e.printStackTrace();//로깅
			throw e; //WAS에게 예외를 전달해서 예외페이지로 이동하도록 함
		}
 
	}

}
