package com.zea.geverytime.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.common.MvcUtils;
import com.zea.geverytime.member.model.service.MemberService;
import com.zea.geverytime.member.model.vo.Member;


@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();


	/**
	 *  @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.인코딩처리
		request.setCharacterEncoding("utf-8");

		//2.사용자 입력값처리
		String memberId = request.getParameter("memberId");
		String password = MvcUtils.getEncryptedPassword(request.getParameter("password"));
		System.out.println("memberId = " + memberId + ", password = " + password  );

	
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member@MemberLoginServlet.doPost = " + member);
		
		HttpSession session = request.getSession(true); 
		System.out.println(session.getId());

		
		session.setMaxInactiveInterval(10*60);//초단위
	
				if(member != null && password.equals(member.getPassword())) {
					
					session.setAttribute("loginMember", member);

					session.setAttribute("msg", "로그인 성공!");
					
				
			
				}else {
					session.setAttribute("msg", "로그인 실패!");
					
				}
				
				

				String location = request.getContextPath() + "/";
				response.sendRedirect(location);
	}
				
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
		.forward(request, response);

	}
	}