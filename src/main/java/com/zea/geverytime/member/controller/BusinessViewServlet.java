package com.zea.geverytime.member.controller;

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
 * Servlet implementation class BusinessViewServlet
 */
@WebServlet("/member/BusinessViewServlet")
public class BusinessViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService businessService = new BusinessService();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
		    request.setCharacterEncoding("utf-8");
		    HttpSession session = request.getSession(true); 
	        session.setMaxInactiveInterval(10*60);
	        Member loginMember = (Member) session.getAttribute("loginMember"); 
	        String businessId = loginMember.getMemberId();
	        Business business = businessService.selectOneMember(businessId);
	        System.out.println("member@MemberLoginServlet.doPost = " + businessId);
	        
	        session.setAttribute("businessMember", business);
	        System.out.println("businessMember :"  + business);                	                         	          
	            
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

					HttpSession session = request.getSession();
					Business businessMember  =  (Business)session.getAttribute("businessMember");
	
					   request.getRequestDispatcher("/WEB-INF/views/member/BusinessMypage.jsp")
				        .forward(request, response);
	
	}

	

}
