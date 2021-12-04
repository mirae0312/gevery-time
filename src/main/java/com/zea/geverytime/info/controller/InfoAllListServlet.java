package com.zea.geverytime.info.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class InfoAllListServlet
 */
@WebServlet("/info/allList")
public class InfoAllListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String board = request.getParameter("board");
//            System.out.println("[infoAllListServlet] board : " + board);
   
            // 상호명(사업자), 썸네일(첨부파일), head설명(info), 조회수(한주인기게시물경우)
            // 인기 게시물
            List<Info> popList = infoService.selectPopList(board);
//            System.out.println("[InfoAllListServlet] + popList : " + popList);

            // 공통사항 list속성, jsp로 보내기
            request.setAttribute("popList", popList);
//            request.setAttribute("list", list);

            // ajax에서 사용할 구분자
            request.setAttribute("check", board);

            // 위치지정
            String location = "/WEB-INF/views/info/" + board + ".jsp";
            request
                .getRequestDispatcher(location)
                .forward(request, response);

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }

	}
	
}
