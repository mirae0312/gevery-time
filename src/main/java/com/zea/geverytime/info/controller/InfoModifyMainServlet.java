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
import com.zea.geverytime.info.model.vo.CafeRestaurant;
import com.zea.geverytime.info.model.vo.Hospital;
import com.zea.geverytime.info.model.vo.Info;
import com.zea.geverytime.info.model.vo.Pension;
import com.zea.geverytime.info.model.vo.Salon;

/**
 * Servlet implementation class InfoModifyMainServlet
 */
@WebServlet("/info/modifyMain")
public class InfoModifyMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String code = request.getParameter("code");
			String codeN = request.getParameter("codeN");
			String id = request.getParameter("id");
			
			// 게시물 가져오기
			Info info = infoService.selectOneView(code, codeN);
//			System.out.println("[infoBoardViewServlet] info : " + info);
			String site1 = "";
			String site2 = "";
			String site = info.getSite();
			if(site != null && !site.isEmpty()) {
				String[] sites = site.split(",");
				if(sites.length == 2) {
					site1 = sites[0];
					site2 = sites[1];
					request.setAttribute("site1", site1);
					request.setAttribute("site2", site2);
				}else {
					site1 = site;
					request.setAttribute("site1", site1);
				}
				
			}
			
			// view단 처리
			request.setAttribute("codeN", codeN);
			request.setAttribute("info", info);
			request
				.getRequestDispatcher("/WEB-INF/views/info/modifyMain.jsp")
				.forward(request, response);
			
		}catch(Exception e) {
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
//			String memberId = loginMember.getMemberId();
	//		info.setMemberId(memberId);
			String memberId = multipartRequest.getParameter("writer");
			String businessNo = multipartRequest.getParameter("businessNo");
			info.setMemberId(memberId);
			info.setBusinessNo(businessNo);
			
			// 업종 확인용
			String no = businessNo.substring(businessNo.length() - 1);
			
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
			info.setStartLaunch(startLaunch);
			info.setEndLaunch(endLaunch);
			info.setStartHour(startHour);
			info.setEndHour(endHour);
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
			
			// 병원1: 진료과목 ,
			if("1".equals(no)) {
				String service1 = multipartRequest.getParameter("hservice1");
//				System.out.println("service1 : " + service1);
				String service2 = multipartRequest.getParameter("hservice2");
//				System.out.println("service2 : " + service2);
				String service3 = multipartRequest.getParameter("hservice3");
//				System.out.println("service3 : " + service3);
				String service4 = multipartRequest.getParameter("hservice4");
				String service5 = multipartRequest.getParameter("hservice5");
				String service6 = multipartRequest.getParameter("hservice6");
				String service7 = multipartRequest.getParameter("hservice7");
				
				String[] services = {service1, service2, service3, service4,
						service5, service6, service7};
				Hospital h = new Hospital();
				List<Hospital> list = new ArrayList<>();
				for(int i = 0; i < services.length; i++) {
					if(services[i] != null && !services[i].isEmpty()) {
						h = new Hospital();
						System.out.println("for service[i] : " + services[i]);
						h.setService(services[i]);
						list.add(h);
					}
				}
				info.setHospitals(list);
			}
			// 카페2 음식점3 / :
			if("2".equals(no) || "3".equals(no)) {
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
				CafeRestaurant cr = new CafeRestaurant();
				List<CafeRestaurant> list = new ArrayList<>();
				for(int i = 0; i < services.length; i++) {
					if(services[i] != null && !services[i].isEmpty()) {
						cr = new CafeRestaurant();
						cr.setService(services[i]);
						cr.setPrice(prices[i]);
						list.add(cr);
					}
				}
				info.setCafeRestaurants(list);
				
			}
			
			// 펜션 4 - : ,
			if("4".equals(no)) {
				String room1 = multipartRequest.getParameter("room1");
				String price11 = multipartRequest.getParameter("price11");
				String price12 = multipartRequest.getParameter("price12");
				String price13 = multipartRequest.getParameter("price13");
				String price14 = multipartRequest.getParameter("price14");
				String price15 = multipartRequest.getParameter("price15");
				String price16 = multipartRequest.getParameter("price16");
				String room2 = multipartRequest.getParameter("room2");
				String price21 = multipartRequest.getParameter("price21");
				String price22 = multipartRequest.getParameter("price22");
				String price23 = multipartRequest.getParameter("price23");
				String price24 = multipartRequest.getParameter("price24");
				String price25 = multipartRequest.getParameter("price25");
				String price26 = multipartRequest.getParameter("price26");
				String room3 = multipartRequest.getParameter("room3");
				String price31 = multipartRequest.getParameter("price31");
				String price32 = multipartRequest.getParameter("price32");
				String price33 = multipartRequest.getParameter("price33");
				String price34 = multipartRequest.getParameter("price34");
				String price35 = multipartRequest.getParameter("price35");
				String price36 = multipartRequest.getParameter("price36");
				
				Pension pension1 = new Pension(0, null, room1, price11, price12, price13, price14, price15, price16);
				Pension pension2 = new Pension(0, null, room2, price21, price22, price23, price24, price25, price26);
				Pension pension3 = new Pension(0, null, room3, price31, price32, price33, price34, price35, price36);
				
				List<Pension> pensions = new ArrayList<>();
				pensions.add(pension1);
				pensions.add(pension2);
				pensions.add(pension3);
				
				info.setPensions(pensions);
				
			}
			
			// 미용실 5 - ,
			if("5".equals(no)) {
				String smallBath1 = multipartRequest.getParameter("smallBath1");
				String middleBath1 = multipartRequest.getParameter("middleBath1");
				String specialBath1 = multipartRequest.getParameter("specialBath1");
				String smallBathAnd1 = multipartRequest.getParameter("smallBathAnd1");
				String middleBathAnd1 = multipartRequest.getParameter("middleBathAnd1");
				String specialBathAnd1 = multipartRequest.getParameter("specialBathAnd1");
				String smallMachine1 = multipartRequest.getParameter("smallMachine1");
				String middleMachine1 = multipartRequest.getParameter("middleMachine1");
				String specialMachine1 = multipartRequest.getParameter("specialMachine1");
				String smallSpotting1 = multipartRequest.getParameter("smallSpotting1");
				String middleSpotting1 = multipartRequest.getParameter("middleSpotting1");
				String specialSpotting1 = multipartRequest.getParameter("specialSpotting1");
				String smallScissors1 = multipartRequest.getParameter("smallScissors1");
				String middleScissors1 = multipartRequest.getParameter("middleScissors1");
				String specialScissors1 = multipartRequest.getParameter("specialScissors1");
				String smallBath2 = multipartRequest.getParameter("smallBath2");
				String middleBath2 = multipartRequest.getParameter("middleBath2");
				String specialBath2 = multipartRequest.getParameter("specialBath2");
				String smallBathAnd2 = multipartRequest.getParameter("smallBathAnd2");
				String middleBathAnd2 = multipartRequest.getParameter("middleBathAnd2");
				String specialBathAnd2 = multipartRequest.getParameter("specialBathAnd2");
				String smallMachine2 = multipartRequest.getParameter("smallMachine2");
				String middleMachine2 = multipartRequest.getParameter("middleMachine2");
				String specialMachine2 = multipartRequest.getParameter("specialMachine2");
				String smallSpotting2 = multipartRequest.getParameter("smallSpotting2");
				String middleSpotting2 = multipartRequest.getParameter("middleSpotting2");
				String specialSpotting2 = multipartRequest.getParameter("specialSpotting2");
				String smallScissors2 = multipartRequest.getParameter("smallScissors2");
				String middleScissors2 = multipartRequest.getParameter("middleScissors2");
				String specialScissors2 = multipartRequest.getParameter("specialScissors2");
				String smallBath3 = multipartRequest.getParameter("smallBath3");
				String middleBath3 = multipartRequest.getParameter("middleBath3");
				String specialBath3 = multipartRequest.getParameter("specialBath3");
				String smallBathAnd3 = multipartRequest.getParameter("smallBathAnd3");
				String middleBathAnd3 = multipartRequest.getParameter("middleBathAnd3");
				String specialBathAnd3 = multipartRequest.getParameter("specialBathAnd3");
				String smallMachine3 = multipartRequest.getParameter("smallMachine3");
				String middleMachine3 = multipartRequest.getParameter("middleMachine3");
				String specialMachine3 = multipartRequest.getParameter("specialMachine3");
				String smallSpotting3 = multipartRequest.getParameter("smallSpotting3");
				String middleSpotting3 = multipartRequest.getParameter("middleSpotting3");
				String specialSpotting3 = multipartRequest.getParameter("specialSpotting3");
				String smallScissors3 = multipartRequest.getParameter("smallScissors3");
				String middleScissors3 = multipartRequest.getParameter("middleScissors3");
				String specialScissors3 = multipartRequest.getParameter("specialScissors3");
				Salon salon1 = new Salon(0, null, smallBath1, middleBath1, specialBath1, smallBathAnd1, middleBathAnd1, specialBathAnd1, smallMachine1, middleMachine1, specialMachine1, smallSpotting1, middleSpotting1, specialSpotting1, smallScissors1, middleScissors1, specialScissors1);
				Salon salon2 = new Salon(0, null, smallBath2, middleBath2, specialBath2, smallBathAnd2, middleBathAnd2, specialBathAnd2, smallMachine2, middleMachine2, specialMachine2, smallSpotting2, middleSpotting2, specialSpotting2, smallScissors2, middleScissors2, specialScissors2);
				Salon salon3 = new Salon(0, null, smallBath3, middleBath3, specialBath3, smallBathAnd3, middleBathAnd3, specialBathAnd3, smallMachine3, middleMachine3, specialMachine3, smallSpotting3, middleSpotting3, specialSpotting3, smallScissors3, middleScissors3, specialScissors3);
				List<Salon> listS = new ArrayList<>();
				listS.add(salon1);
				listS.add(salon2);
				listS.add(salon3);
				info.setSalons(listS);
				
			}
			
	
			// 설명부분 + 3
			String bodyContent = multipartRequest.getParameter("bodyContent");
			info.setBodyContents(bodyContent);
			
			// 길안내
			String way = multipartRequest.getParameter("way");
			info.setRoadGuide(way);

			// 썸네일 관리
			File headFile = multipartRequest.getFile("headFile");
			File file1 = multipartRequest.getFile("file1");
			
			String code = multipartRequest.getParameter("code");
			String codeN = code.substring(2, 3);
			info.setCode(code);
			
			List<Attachment> attachList = infoService.selectAttachment(code);
			if(attachList != null && !attachList.isEmpty()) {
				for(int i = 0; i < attachList.size(); i++) {
					if(attachList.get(i).getRenamedFilename() != null) {
						File del = new File(saveDirectory, attachList.get(i).getRenamedFilename());
						boolean removed = del.delete();
						System.out.println("[infoModifyMainServlet] 첨부파일" + i + " : " + removed);
					}
				}
			}
			
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
			int result = infoService.updateInfo(info, codeN);
			
			// redirect
			HttpSession session = request.getSession();
			session.setAttribute("msg", "글 수정이 완료되었습니다. 관리자의 확인후 2~3일 이내에 게시될 예정입니다.");
			String location = request.getContextPath() + "/";
			response.sendRedirect(location);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
