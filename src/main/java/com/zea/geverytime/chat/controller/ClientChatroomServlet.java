package com.zea.geverytime.chat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/chat/chatroom")
public class ClientChatroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 단순 연결용
		request
			.getRequestDispatcher("/WEB-INF/views/chat/chatroom.jsp")
			.forward(request, response);
	}

}
