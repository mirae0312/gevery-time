package com.zea.geverytime.myPage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.member.model.vo.Member;
import com.zea.geverytime.myPage.model.service.MyPageService;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet("/myPage/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyPageService myPageService = new MyPageService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
			.getRequestDispatcher("/WEB-INF/views/myPage/updatePassword.jsp")
			.forward(request, response);
	}

	/**
	 * 비밀번호 변경처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String location = request.getContextPath();
		String msg = null;
		int result = 0;
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		// 입력 비밀번호 일치여부
		if(oldPassword.equals(loginMember.getPassword())) {
			
			// 기존비밀번호가 일치한 경우만 신규비밀번호로 업데이트
			loginMember.setPassword(newPassword);
			result = myPageService.updatePassword(loginMember);
			msg = (result > 0) ? 
					"비밀번호를 성공적으로 변경했습니다." : "비밀번호를 변경에 실패했습니다.";
			location += "/myPage/myPageMain";
		}
		else {
			msg = "비밀번호가 일치하지 않습니다.";				
			location += "/myPage/updatePassword";
		}
		// 사용자 경고창
		session.setAttribute("msg", msg);
		response.sendRedirect(location);
	}

}
