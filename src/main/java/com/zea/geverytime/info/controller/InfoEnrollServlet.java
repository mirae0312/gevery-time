package com.zea.geverytime.info.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.zea.geverytime.common.MvcFileRenamePolicy;
import com.zea.geverytime.common.MvcUtils;
import com.zea.geverytime.common.model.vo.Attachment;
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
		
		try {
			Info info = new Info();
			
			// server컴에 저장 경로
			String saveDirectory = getServletContext().getRealPath("/upload/info");
			
			int maxPostSize = 1024*1024*10;
			String encoding= "utf-8";
			
			FileRenamePolicy policy = new MvcFileRenamePolicy();
			
			MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory,
																maxPostSize, encoding, policy);
			
			
			
			// 아이디
	//		String memberId = loginMember.getMemberId();
	//		info.setMemberId(memberId);
			
			// 상호명, headcontent
			String businessName = multipartRequest.getParameter("businessName");
			String headContent = multipartRequest.getParameter("headContent");
			info.setBusinessName(businessName);
			info.setHeadContent(headContent);
			
			// 전화번호 000-0000-0000
			String bar = "-";
			String tel1 = multipartRequest.getParameter("tel1");
			String tel2 = multipartRequest.getParameter("tel2");
			String tel3 = multipartRequest.getParameter("tel3");
			StringBuilder tels = new StringBuilder();
			tels.append(tel1 + bar + tel2 + bar + tel3);
			String tel = tels.toString();
			info.setBusinessTel(tel);
			
			// 주소
			String addr = multipartRequest.getParameter("addr");
			info.setBusinessAddress(addr);
			
			// 영업시간 09:00~19:00
			String mon = multipartRequest.getParameter("mon");
			String tue = multipartRequest.getParameter("tue");
			String wed = multipartRequest.getParameter("wed");
			String thu = multipartRequest.getParameter("thu");
			String fri = multipartRequest.getParameter("fir");
			String sat = multipartRequest.getParameter("sat");
			String sun = multipartRequest.getParameter("sun");
			String launch = multipartRequest.getParameter("launch");
			String dinner = multipartRequest.getParameter("dinner");
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
			String holiday = multipartRequest.getParameter("holiday");
			info.setHoliday(holiday);
			
			// www.naver.com + 2
			String site1 = multipartRequest.getParameter("site1");
			String site2 = multipartRequest.getParameter("site2");
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
			String service1 = multipartRequest.getParameter("service1");
			String service2 = multipartRequest.getParameter("service2");
			String service3 = multipartRequest.getParameter("service3");
			String service4 = multipartRequest.getParameter("service4");
			String service5 = multipartRequest.getParameter("service5");
			String service6 = multipartRequest.getParameter("service6");
			String service7 = multipartRequest.getParameter("service7");
			
			String[] services = {service1, service2, service3, service4,
								service5, service6, service7};
			StringBuilder service = new StringBuilder();
			
			for(int i = 0; i < services.length; i++) {
				if(services[i] != null || !services[i].isEmpty()) {
					service.append(services[i] + ",");
				}
			}
			info.setServiceContent(service.toString());
	
			// 설명부분 + 3
			String bodyHead1 = multipartRequest.getParameter("bodyHead1");
			String bodyContent1 = multipartRequest.getParameter("bodyContent1");
			String bodyHead2 = multipartRequest.getParameter("bodyHead2");
			String bodyContent2 = multipartRequest.getParameter("bodyContent2");
			String bodyHead3 = multipartRequest.getParameter("bodyHead3");
			String bodyContent3 = multipartRequest.getParameter("bodyContent3");
			
			String[] head = {bodyHead1, bodyHead2, bodyHead3};
			String[] body = {bodyContent1, bodyContent2, bodyContent3};
			StringBuilder headContents = new StringBuilder();
			StringBuilder bodyContents = new StringBuilder();
			
			for(int i = 0; i < head.length; i++) {
				if(head[i] != null || !head[i].isEmpty()) {
					headContents.append(head[i] + ",");
				}
			}
			for(int i = 0; i < body.length; i++) {
				if(body[i] != null || !body[i].isEmpty()) {
					bodyContents.append(body[i] + ",");
				}
			}
			info.setHeadContent(headContents.toString());
			info.setBodyContents(bodyContents.toString());
			
			// 길안내 + 2
			String way1 = multipartRequest.getParameter("way1");
			String way2 = multipartRequest.getParameter("way2");
			
			String[] ways = {way1, way2};
			StringBuilder way = new StringBuilder();
			
			for(int i = 0; i < ways.length; i++) {
				if(ways[i] != null || !ways[i].isEmpty()) {
					way.append(ways[i] + ",");
				}
			}
			info.setBusinessAddress(way.toString());
			
			System.out.println("[InfoEnrollServlet] info : " + info);
			
			File headFile = multipartRequest.getFile("headFile");
			File file1 = multipartRequest.getFile("file1");
			File file2 = multipartRequest.getFile("file2");
			File file3 = multipartRequest.getFile("file3");
			
			if(headFile != null || file1 != null || file2 != null || file3 != null) {
				List<Attachment> attachments = new ArrayList<>();
				
				if(headFile != null) {
					Attachment attach1 = MvcUtils.makeAttachment(multipartRequest, "headFile");
				}
			}
			
			
			// 업무로직
	//		int result = infoService.insertInfo(info);
			
			// redirect
			HttpSession session = request.getSession();
			session.setAttribute("msg", "글 등록이 완료되었습니다. 관리자의 확인후 2~3일 이내에 게시될 예정입니다.");
			String location = request.getContextPath() + "/";
			response.sendRedirect(location);
		
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}

}
