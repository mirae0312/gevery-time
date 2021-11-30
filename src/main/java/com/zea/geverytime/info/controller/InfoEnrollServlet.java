package com.zea.geverytime.info.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InfoEnrollServlet
 */
@WebServlet("/info/Enroll")
public class InfoEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 게시물 등록jsp로 이동
		request
			.getRequestDispatcher("/WEB-INF/views/info/infoEnroll.jsp")
			.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 상호명, headcontent
		String businessName = request.getParameter("businessName");
		String headContent = request.getParameter("headContent");
		
		// 000-0000-0000
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		
		// 주소
		String addr = request.getParameter("addr");
		
		// 영업시간 09:00~19:00
		String sat = request.getParameter("sat");
		String sun = request.getParameter("sun");
		String mon = request.getParameter("mon");
		String tue = request.getParameter("tue");
		String wed = request.getParameter("wed");
		String thu = request.getParameter("thu");
		String fri = request.getParameter("fir");
		String launch = request.getParameter("launch");
		String dinner = request.getParameter("dinner");
		
		// select
		String holiday = request.getParameter("holiday");
		
		// www.naver.com + 2
		String site1 = request.getParameter("site1");
		String site2 = request.getParameter("site2");
		
		// 병원: 진료과목, 다른곳: 가격표 + 15
		String service1 = request.getParameter("service1");
		
		// 설명부분 + 3
		String bodyHead1 = request.getParameter("bodyHead1");
		String bodyContent1 = request.getParameter("bodyContent1");
		
		// 길안내 + 2
		String way1 = request.getParameter("way1");
		
		
		
	}

}
