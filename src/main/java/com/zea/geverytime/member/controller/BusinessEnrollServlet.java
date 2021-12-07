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
			
			String Id = request.getParameter("Id");
			String password =request.getParameter("password");
			String name = request.getParameter("name");
			
			String email01 = request.getParameter("email01");
			String email02 = request.getParameter("selectEmail");
			String bName = request.getParameter("businessName");
			String bAddress = request.getParameter("baddress");
			String location1 = request.getParameter("location1");
			String location2 = request.getParameter("location2");
			String location = location1.concat(location2);
			String btel1 = request.getParameter("tel1");
			String btel2 = request.getParameter("tel2");
			String btel3 = request.getParameter("tel3");
			String btel = btel1.concat(btel2).concat("-"+btel3);
			String businessNo1 = request.getParameter("businessNo1");
			String businessNo2 = request.getParameter("businessNo2");
			String businessNo3 = request.getParameter("businessNo3");
			String businessNo4 = request.getParameter("businessNo4");
			String businessNo = businessNo1.concat("-"+businessNo2).concat("-"+businessNo3).concat(businessNo4);
			System.out.println(email02);
	Business business = new Business
			(Id,
			 password,
			 name,
			 email01+"@"+email02, 
			 bName, 
			 bAddress,
			 businessNo ,
			 btel,
			 location,
			 businessService.BUSINESSTYPE);
			
			int result = businessService.insertBmember(business);
			String msg = result > 0 ? "회원가입성공!" : "회원가입실패!";

			
			// 4.redirect 및 msg처리
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			String start = request.getContextPath() + "/";
			response.sendRedirect(start);
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		}
	
	

	}
	


}