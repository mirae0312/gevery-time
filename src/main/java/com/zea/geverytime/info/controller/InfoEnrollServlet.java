package com.zea.geverytime.info.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
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
		try {
//			String memberId = loginMember.getMemberId();
			String memberId = "businessWoman";
			boolean bool = true;
			
			// 글을 작성한적 있는지 확인 (개인 사업자는 사업체 한개인 것을 이용)
			String check = infoService.checkInfoBoard(memberId);
			if(check.isEmpty())
				bool = false;
			
			if(!bool) {
				Info info = infoService.selectBeforeWrite(memberId);
				System.out.println("[infoEnollServlet] info : " + info);
				// 게시물 등록jsp로 이동
				request.setAttribute("info", info);
				request
					.getRequestDispatcher("/WEB-INF/views/info/infoEnroll.jsp")
					.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("msg", "이미 등록하여 수정만 가능합니다.");
				String location = request.getContextPath() + "/";
				response.sendRedirect(location);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
			
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
			
//			// 전화번호 000-0000-0000
			String tel = multipartRequest.getParameter("tel");
			info.setBusinessTel(tel);
			
			// 주소
			String addr = multipartRequest.getParameter("addr");
			info.setBusinessAddress(addr);
			
			// 영업시간 09:00~19:00
			String startHour = multipartRequest.getParameter("startHour");
			String endHour = multipartRequest.getParameter("endHour");
			String startLaunch = multipartRequest.getParameter("startLaunch");
			String endLaunch = multipartRequest.getParameter("endLaunch");
			String startDinner = multipartRequest.getParameter("startDinner");
			String endDinner = multipartRequest.getParameter("endDinner");
			String[] holidays = multipartRequest.getParameterValues("holiday");
			String holiday = holidays != null ? String.join(",", holidays) : "";
			
			info.setHoliday(holiday);
			info.setStartHour(startHour);
			info.setEndHour(endHour);
			info.setStartLaunch(startLaunch);
			info.setEndLaunch(endLaunch);
			info.setStartDinner(startDinner);
			info.setEndDinner(endDinner);
			
			// www.naver.com + 2
			String site1 = multipartRequest.getParameter("site1");
			String site2 = multipartRequest.getParameter("site2");
			StringBuilder sites = new StringBuilder();		
			if(site1 != null && !site1.isEmpty()) {
				sites.append(site1);
				if(site2 != null && !site2.isEmpty()) {
					sites.append("," + site2);
				}
			}
			String site = sites.toString();
			info.setSite(site);
			
			// 병원: 진료과목, 다른곳: 가격표 + 7
			String service1 = multipartRequest.getParameter("service1");
			String price1 = multipartRequest.getParameter("price1");
			String service2 = multipartRequest.getParameter("service2");
			String price2 = multipartRequest.getParameter("price2");
			String service3 = multipartRequest.getParameter("service3");
			String price3 = multipartRequest.getParameter("price3");
			String service4 = multipartRequest.getParameter("service4");
			String price4 = multipartRequest.getParameter("price4");
			String service5 = multipartRequest.getParameter("service5");
			String price5 = multipartRequest.getParameter("price5");
			String service6 = multipartRequest.getParameter("service6");
			String price6 = multipartRequest.getParameter("price6");
			String service7 = multipartRequest.getParameter("service7");
			String price7 = multipartRequest.getParameter("price7");
			
			String[] services = {service1, service2, service3, service4,
								service5, service6, service7};
			String[] prices = {price1, price2, price3, price4, price5,
								price6, price7};
			StringBuilder service = new StringBuilder();
			
			for(int i = 0; i < services.length; i++) {
				if(services[i] != null && !services[i].isEmpty()) {
					service.append(services[i] + ":" + prices[i]);
				}
			}
			info.setServiceContent(service.toString());
	
			// 설명부분 + 3
			String bodyContent = multipartRequest.getParameter("bodyContent");
			
			info.setBodyContents(bodyContent);
			
			// 길안내
			String way = multipartRequest.getParameter("way");
			info.setBusinessAddress(way);
					
			// 썸네일 관리
			File headFile = multipartRequest.getFile("headFile");
			File file1 = multipartRequest.getFile("file1");
			
			if(headFile != null || file1 != null) {
				List<Attachment> attachments = new ArrayList<>();
				
				if(headFile != null) {
					Attachment attach1 = MvcUtils.makeAttachment(multipartRequest, "headFile");
					attachments.add(attach1);
				}
				if(file1 != null) {
					Attachment attach2 = MvcUtils.makeAttachment(multipartRequest, "file1");
					attachments.add(attach2);
				}
				info.setAttachments(attachments);
			}
			
			System.out.println("[InfoEnrollServlet] info : " + info);
						
			// 업무로직
			int result = infoService.insertInfo(info);
			
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
