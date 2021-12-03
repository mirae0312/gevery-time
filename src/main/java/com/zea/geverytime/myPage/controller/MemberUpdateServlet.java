package com.zea.geverytime.myPage.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.member.model.service.MemberService;
import com.zea.geverytime.member.model.vo.Member;
import com.zea.geverytime.myPage.model.service.MyPageService;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/myPage/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyPageService myPageService = new MyPageService();
	private MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String _birthday = request.getParameter("birthday");
		
		Date birthday = null;
		if(!"".equals(_birthday))
			birthday = Date.valueOf(_birthday);
		
		Member member = new Member(memberId, null, memberName, phone, address, email, null, null, birthday);
		System.out.println("memberUpdateServlet " + member);
		
		int result = myPageService.updateMember(member);
		String msg = (result > 0) ? "회원정보 수정 성공" : "회원정보 수정 실패";
		// 회원정보 수정결과 반영
		if(result > 0) {
			Member updateMember = memberService.selectOneMember(memberId);
			session.setAttribute("loginMember", updateMember);
		}
		
		
		String location = request.getContextPath() + "/myPage/myPageMain";
		response.sendRedirect(location);
	}

}
