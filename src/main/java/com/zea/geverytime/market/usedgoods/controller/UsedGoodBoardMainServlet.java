package com.zea.geverytime.market.usedgoods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsedGoodBoardMainServlet
 */
@WebServlet("/ugGoods/main")
public class UsedGoodBoardMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/market/ugGoods/ugGoodsSaleMain.jsp").forward(request, response);
	}

}
