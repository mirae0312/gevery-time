package com.zea.geverytime.myPage.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.member.model.service.BusinessService;
import com.zea.geverytime.member.model.vo.Business;
import com.zea.geverytime.member.model.vo.Member;
import com.zea.geverytime.myPage.model.service.MyPageService;

/**
 * Servlet implementation class BusinessUpdateServlet
 */
@WebServlet("/myPage/businessUpdate")
public class BusinessUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyPageService myPageService = new MyPageService();
	private BusinessService businessService = new BusinessService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String businessId = request.getParameter("businessId");
		String businessName = request.getParameter("businessName");
		String email = request.getParameter("email");
		String businessNo = request.getParameter("businessNo");
		String bName = request.getParameter("bName");
		String bAddress = request.getParameter("bAddress");
		String bTel = request.getParameter("bTel");
		String location = request.getParameter("location");
		
		Business business = 
				new Business(businessId, null, businessName, email, 
				 businessNo, bName, bAddress, bTel,
				 location, businessService.BUSINESSTYPE);
		System.out.println("BusinessUpdate@Servlet " + business);
		
		int result = myPageService.updateBusiness(business);
		String msg = (result > 0) ? "사업자정보 수정 성공" : "사업자정보 수정 실패";
		// 회원정보 수정결과 반영
		if(result > 0) {
			Business updateBusiness = businessService.selectOneMember(businessId);
			session.setAttribute("businessMember", updateBusiness);
		}
		
		
		String locate = request.getContextPath() + "/myPage/myPageMain";
		response.sendRedirect(locate);
	}
	
}
