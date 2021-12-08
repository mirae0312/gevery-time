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


@WebServlet(name="SearchId", urlPatterns={"/member/IdFindGetServlet"})
public class IdFindGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MemberService memberService = new MemberService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.변수저장
		String memberName = request.getParameter("name");
		System.out.println(memberName);
		String email = request.getParameter("email");
		//3.비지니스로직
		Member member = memberService.searchId(memberName,email);
		
		System.out.println("member : " + member);
		 HttpSession session = request.getSession(true); 
		  session.setAttribute("loginMember", member);
          Member loginMember = (Member) session.getAttribute("loginMember"); 
	
		if(member != null) {
			request.setAttribute("member", member);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/findIdSecond.jsp");
			rd.forward(request, response);
			}else {
			request.setAttribute("msg", "정확한 정보를 입력해 주세요!");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/checkidmsg.jsp");
			rd.forward(request, response);	
		}
		
	}	
}
