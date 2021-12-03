package com.zea.geverytime.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.admin.model.service.AdminService;
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class AdminListServlet
 */
@WebServlet("/admin/adminList")
public class AdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();

	/**
	 * select * from info_common order by reg_date desc
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//            String board = (String) request.getParameter("board");
//            int start = 1;
//            int end = 5;

            String location = "";
            // 전체 게시물
            List<Info> list = adminService.selectInfoBoard();
            System.out.println("[AdminListServlet] list : " + list);

            request.setAttribute("list", list);
            request
                .getRequestDispatcher("/WEB-INF/views/admin/adminList.jsp")
                .forward(request, response);

		 	} catch(Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	}
}
