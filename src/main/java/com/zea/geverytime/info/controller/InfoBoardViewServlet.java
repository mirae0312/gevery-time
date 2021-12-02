package com.zea.geverytime.info.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.Info;

/**
 * Servlet implementation class InfoBoardViewServlet
 */
@WebServlet("/info/view")
public class InfoBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 조회용 코드
			String code = (String) request.getParameter("code");
			
			// 조회수를 위한 쿠키 생성
			Cookie[] cookies = request.getCookies();
			boolean hasRead = false;
			String infoBoardCooVal = "";
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if("infoBoardCoo".equals(name)) {
						infoBoardCooVal = value;
						if(value.contains("[" + code + "]")) {
							hasRead = true;
							break;
						}
					}
				}
			}
			
			// 조회수 증가
			if(!hasRead) {
				int result = infoService.updateReadCount(code);
				
				Cookie cookie = new Cookie("infoBoardCoo", infoBoardCooVal + "[" + code + "]");
				cookie.setPath(request.getContextPath() + "info/view");
				cookie.setMaxAge(90 * 24 * 60 * 60);
				response.addCookie(cookie);
				
			}
			
			// 게시물 가져오기
			Info info = infoService.selectOneView(code);
			System.out.println("[infoBoardViewServlet] info : " + info);
			
			String no = info.getCode();
			
			// view단 처리
			request.setAttribute("info", info);
			request
				.getRequestDispatcher("/WEB-INF/views/info/infoView.jsp")
				.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
			
	}

}
