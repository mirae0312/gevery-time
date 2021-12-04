package com.zea.geverytime.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.member.model.service.MemberService;
import com.zea.geverytime.member.model.vo.Member;


@WebServlet("/member/findId")
public class IdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.변수저장
		String memberName = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		//3.비지니스로직
		MemberService service = new MemberService();
		Member member = service.searchId(memberName,email,phone);
		
		System.out.println("member : " + member);

		//4.뷰 처리
		if(member != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/findId.jsp");
			request.setAttribute("members", member);
			rd.forward(request, response);
			
		}else {
			request.setAttribute("msg", "정확한 정보를 입력해 주세요!");
			request.setAttribute("loc", "/");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}