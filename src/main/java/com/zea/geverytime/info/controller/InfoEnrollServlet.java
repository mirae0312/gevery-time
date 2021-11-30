package com.zea.geverytime.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class InfoEnrollServlet
 */
@WebServlet("/info/Enroll")
public class InfoEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

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

		Info info = new Info();
		
		// 아이디
//		String memberId = loginMember.getMemberId();
//		info.setMemberId(memberId);
		
		// 상호명, headcontent
		String businessName = request.getParameter("businessName");
		String headContent = request.getParameter("headContent");
		info.setBusinessName(businessName);
		info.setHeadContent(headContent);
		
		// 전화번호 000-0000-0000
		String bar = "-";
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		StringBuilder tels = new StringBuilder();
		tels.append(tel1 + bar + tel2 + bar + tel3);
		String tel = tels.toString();
		info.setBusinessTel(tel);
		
		// 주소
		String addr = request.getParameter("addr");
		info.setBusinessAddress(addr);
		
		// 영업시간 09:00~19:00
		String mon = request.getParameter("mon");
		String tue = request.getParameter("tue");
		String wed = request.getParameter("wed");
		String thu = request.getParameter("thu");
		String fri = request.getParameter("fir");
		String sat = request.getParameter("sat");
		String sun = request.getParameter("sun");
		String launch = request.getParameter("launch");
		String dinner = request.getParameter("dinner");
		info.setMon(mon);
		info.setTue(tue);
		info.setWed(wed);
		info.setThu(thu);
		info.setFri(fri);
		info.setSat(sat);
		info.setSun(sun);
		info.setLaunch(launch);
		info.setDinner(dinner);
		
		// select
		String holiday = request.getParameter("holiday");
		info.setHoliday(holiday);
		
		// www.naver.com + 2
		String site1 = request.getParameter("site1");
		String site2 = request.getParameter("site2");
		StringBuilder sites = new StringBuilder();		
		if(site1 != null || !site1.isEmpty()) {
			sites.append(site1);
			if(site2 != null || !site2.isEmpty()) {
				sites.append("," + site2);
			}
		}
		String site = sites.toString();
		info.setSite(site);
		
		// 병원: 진료과목, 다른곳: 가격표 + 7
		String service1 = request.getParameter("service1");
		String service2 = request.getParameter("service2");
		String service3 = request.getParameter("service3");
		String service4 = request.getParameter("service4");
		String service5 = request.getParameter("service5");
		String service6 = request.getParameter("service6");
		String service7 = request.getParameter("service7");
		String[] services = {service1, service2, service3, service4,
							service5, service6, service7};
		StringBuilder service = new StringBuilder();
		for(int i = 0; i < services.length; i++) {
			if(services[i] != null || !services[i].isEmpty()) {
				service.append(service1);
			}
		}
		
		// 설명부분 + 3
		String bodyHead1 = request.getParameter("bodyHead1");
		String bodyContent1 = request.getParameter("bodyContent1");
		String bodyHead2 = request.getParameter("bodyHead2");
		String bodyContent2 = request.getParameter("bodyContent2");
		String bodyHead3 = request.getParameter("bodyHead3");
		String bodyContent3 = request.getParameter("bodyContent3");
		
		// 길안내 + 2
		String way1 = request.getParameter("way1");
		String way2 = request.getParameter("way2");
		StringBuilder way = new StringBuilder();
		way.append(way1 + "," + way2);
		
		
		
		
		// 업무로직
//		int result = infoService.insertInfo(info);
		
		
	}

}
