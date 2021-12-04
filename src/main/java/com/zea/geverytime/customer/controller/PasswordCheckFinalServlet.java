package com.zea.geverytime.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.customer.model.service.QnaBoardService;

/**
 * Servlet implementation class PasswordCheckFinalServlet
 */
@WebServlet("/customer/PasswordFinalCheck")
public class PasswordCheckFinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService qnaBoardService = new QnaBoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String password = request.getParameter("password");	
		
		int cnt = qnaBoardService.passwordCheck(no,password);
		
		//String msg = cnt > 0 ? "비밀번호가 일치합니다. " : "비밀번호가 일치하지 않습니다.";
		
		String msg = null;
		if(cnt == 0) {
			msg = "비밀번호가 일치하지 않습니다.";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		if(cnt > 0) {
		String location = request.getContextPath() +"/customer/qnaBoardView?no=" + no;
		response.sendRedirect(location);	
		}else {
			String location = request.getContextPath() +"/customer/qnaBoardList";
			response.sendRedirect(location);	
		}
	 }
		
		
		
		
		
		
		
		
		
		
	 

}
