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


@WebServlet("/member/findId")
public class IdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/findId.jsp");
		rd.forward(request, response);
	}
	

}