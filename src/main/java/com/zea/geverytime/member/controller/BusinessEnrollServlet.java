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

/**
 * Servlet implementation class BusinessEnrollServlet
 */
@WebServlet("/member/businessEnroll")
public class BusinessEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     BusinessService businessService = new BusinessService(); 
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
			.getRequestDispatcher("/WEB-INF/views/member/businessEnroll.jsp")
			.forward(request, response);
		}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
			request.setCharacterEncoding("utf-8");
			
			String businessId = request.getParameter("businessId");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String businessNo = request.getParameter("businessNo");
			String bName = request.getParameter("businessName");
			String baddress = request.getParameter("address");
			String btel = request.getParameter("tel");
			String location = request.getParameter("location");
			
						
			System.out.println("email = " + email);
			
			
			
			
			Business business = new Business(businessId,password,name,email,businessNo,bName,baddress,btel,location,BusinessService.BUSINESSTYPE);
			System.out.println("member@servlet = " + business);
			
			// 3.업무로직 service객체의 insertMember호출 & 생성한 member객체 전달
			int result = BusinessService.insertBusiness();
			String msg = result > 0 ? "회원가입성공!" : "회원가입실패!";
			
			// 4.redirect 및 msg처리
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			String start = request.getContextPath() + "/";
			response.sendRedirect(start);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // tomcat이 error.jsp로 위임하도록 처리
		}
	}


}
