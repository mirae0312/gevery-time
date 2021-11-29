package com.zea.geverytime.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.info.model.service.MemberService;
import com.zea.geverytime.info.model.vo.Member;


/**
 * Servlet implementation class MemberLoginServlet
 * 
 * servlet이 mvc pattern에서 controller역할이다.
 */
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
		String password = request.getParameter("password");
		String saveId = request.getParameter("saveId");
		System.out.println("memberId = " + memberId + ", password = " + password + ", saveId = " + saveId);

	
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member@MemberLoginServlet.doPost = " + member);
		
		HttpSession session = request.getSession(true); 
		System.out.println(session.getId());

		
		session.setMaxInactiveInterval(10*60);//초단위
	
				if(member != null && password.equals(member.getPassword())) {
					
					session.setAttribute("loginMember", member);

					session.setAttribute("msg", "로그인 성공!");
					
					
					// 아이디저장 체크박스 처리
					// 브라우져 호환성을 고려해 도메인당 쿠키개수 50개, 하나의 value값은 4kb를 넘지 않도록 한다.
					Cookie cookie = new Cookie("saveId", memberId);
					cookie.setPath(request.getContextPath());
						
					if(saveId != null) {
						cookie.setMaxAge(7 * 24 * 60 * 60); // 7일
						// persistent(영속)쿠키 : 초단위로 시간을 입력
						// session 쿠키 : setMaxAge설정 안한 경우				
					}
					else {
						cookie.setMaxAge(0); // 즉시 삭제
					}
					response.addCookie(cookie);
					
					
					
				}
				else {
					session.setAttribute("msg", "로그인 실패!");
					
				}
				
				

				String location = request.getContextPath() + "/";
				response.sendRedirect(location);
				
				
			}


	}