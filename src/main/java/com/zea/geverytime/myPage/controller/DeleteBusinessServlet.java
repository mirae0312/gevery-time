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
 * Servlet implementation class DeleteBusinessServlet
 */
@WebServlet("/myPage/businessDelete")
public class DeleteBusinessServlet extends HttpServlet {
	private MyPageService myPageService = new MyPageService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String businessId = request.getParameter("businessId");
			int result = myPageService.deleteBusiness(businessId);
			HttpSession session = request.getSession();
			System.out.println("[DeleteBusinessServlet 쿠키삭제전] = " + session + businessId);
			if(result > 0) {
				session.setAttribute("msg", "사업자탈퇴성공");
				session.removeAttribute("businessMember");

				Cookie cookie = new Cookie("saveId", businessId);
				System.out.println("[DeleteBusinessServlet 쿠키삭제후] = " + businessId);
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			else {
				session.setAttribute("msg", "사업자탈퇴실패");
			}
			response.sendRedirect(request.getContextPath() + "/");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	}

}
