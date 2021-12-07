package com.zea.geverytime.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.member.model.service.MemberService;
import com.zea.geverytime.member.model.vo.Member;


@WebServlet("/NewPwServlet")
public class NewPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/searchPasswordStart.jsp");
		rd.forward(request, response);
		}

	
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			
			String memberId = request.getParameter("memberId");
			System.out.println(memberId);
	        String email = request.getParameter("email");
	        System.out.println(email);
	        Member m = new MemberService().memberCallPw(memberId);
	        if(m==null || !m.getEmail().equals(email))
	        {
	            request.setAttribute("msg", "아이디나 이메일 정보가 맞지 않습니다");
	           
	            return;
	        }
	        String location = request.getContextPath() + "/";
	    	response.sendRedirect(location);
	               
		}
}
