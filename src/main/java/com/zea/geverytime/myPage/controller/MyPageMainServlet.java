package com.zea.geverytime.myPage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.member.model.service.BusinessService;
import com.zea.geverytime.member.model.vo.Business;
import com.zea.geverytime.member.model.vo.Member;

/**
 * Servlet implementation class MyPageMainServlet
 */
@WebServlet("/myPage/myPageMain")
public class MyPageMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService businessService = new BusinessService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		System.out.println("[MyPageMain@Servlet session] = " + session);
		Member loginMember = (Member) session.getAttribute("loginMember"); 
		Business businessMember  =  (Business)session.getAttribute("businessMember");
		System.out.println("[MyPageMain@Servlet businessMember] = " + businessMember);
		request
		.getRequestDispatcher("/WEB-INF/views/myPage/myPageMain.jsp")
		.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
		    request.setCharacterEncoding("utf-8");
		    HttpSession session = request.getSession(); 
		    
	        Member loginMember = (Member) session.getAttribute("loginMember");
	        Business businessMember  =  (Business)session.getAttribute("businessMember");
	        String businessId = loginMember.getMemberId();
	        String memberId = loginMember.getMemberId();
	        Business business = businessService.selectOneMember(businessId);
	        System.out.println("member@MemberLoginServlet.doPost = " + businessId);
	        
	        session.setAttribute("businessMember", business);
	        System.out.println("businessMember :"  + business);                	                         	          
	            
	
	}

}
