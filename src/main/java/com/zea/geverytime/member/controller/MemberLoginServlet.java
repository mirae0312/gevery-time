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

		request.setCharacterEncoding("utf-8");

		
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String saveId = request.getParameter("saveId");
		System.out.println("memberId = " + memberId + ", password = " + password + ", saveId = " + saveId);

	
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member@MemberLoginServlet.doPost = " + member);
		
	
		HttpSession session = request.getSession(true); 
		System.out.println(session.getId());

		
		session.setMaxInactiveInterval(10*60);
	
				if(member != null && password.equals(member.getPassword())) {
					
					session.setAttribute("loginMember", member);

					session.setAttribute("msg", "로그인 성공!");
					
					
					// 아이디저장 체크박스 처리
					Cookie cookie = new Cookie("saveId", memberId);
					cookie.setPath(request.getContextPath());
						
					if(saveId != null) {
						cookie.setMaxAge(7 * 24 * 60 * 60); // 7일
					}
					else {
						cookie.setMaxAge(0); // 즉시 삭제
					}
					response.addCookie(cookie);
					
					
					
				}
				else {
					// 로그인 실패
					session.setAttribute("msg", "로그인 실패!");
					
				}
				
				
				// 4.응답처리 (jsp위임 | redirect)
				// redirect 요청주소를 변경, 새로고침을 통한 오류를 방지
				// location은 브라우져가 새로 요청할 주소
				String location = request.getContextPath() + "/";
				response.sendRedirect(location);
				
				
			}

	
				
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
		.forward(request, response);

	}
}