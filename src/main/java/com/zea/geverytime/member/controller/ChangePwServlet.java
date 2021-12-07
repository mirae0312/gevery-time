package com.zea.geverytime.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.member.model.vo.Member;

/**
 * Servlet implementation class ChangePwServlet
 */
@WebServlet("/ChangePwServlet")
public class ChangePwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/searchPasswordEnd.jsp");
		rd.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
	
        String AuthenticationKey = (String)request.getSession().getAttribute("AuthenticationKey");
        String password = request.getParameter("NewPw1");
        String passwordCheck = request.getParameter("NewPw2");
        String AuthenticationUser = request.getParameter("AuthenticationUser");
        if(!AuthenticationKey.equals(AuthenticationUser))
        {
            System.out.println("인증번호 일치하지 않음");
            request.setAttribute("msg", "인증번호가 일치하지 않습니다");
            request.setAttribute("loc", "/member/searchPw");
            request.getRequestDispatcher("WEB-INF/views/common/msg.jsp").forward(request, response);
            return;
        } else if (password.equals(passwordCheck)){ 
        }
        		
        	
        	
        	
        }
	}
