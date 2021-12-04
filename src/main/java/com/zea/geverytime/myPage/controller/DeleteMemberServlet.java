package com.zea.geverytime.myPage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.myPage.model.service.MyPageService;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet("/myPage/memberDelete")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyPageService myPageService = new MyPageService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String memberId = request.getParameter("memberId");
			int result = myPageService.deleteMember(memberId);
			HttpSession session = request.getSession(false);
			
			if(result > 0) {
				System.out.println("탈퇴성공");
				session.setAttribute("msg", "회원탈퇴성공");
				session.removeAttribute("loginMember");
				
				if(session != null)
					session.invalidate();
				
				// cookie 제거
				Cookie cookie = new Cookie("saveId", memberId);
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			else {
				session.setAttribute("msg", "회원탈퇴실패");
			}
			response.sendRedirect(request.getContextPath() + "/");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	}

}
