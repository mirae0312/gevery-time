package com.zea.geverytime.info.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.Info;
import com.zea.geverytime.info.model.vo.InfoReview;
import com.zea.geverytime.member.model.vo.Member;

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
			String codeN = code.substring(2, 3);
			
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
				cookie.setPath(request.getContextPath() + "/info/view");
				cookie.setMaxAge(90 * 24 * 60 * 60);
				response.addCookie(cookie);
				
			}
			
			// 게시물 가져오기
			Info info = infoService.selectOneView(code, codeN);
//			System.out.println("[infoBoardViewServlet] info : " + info);
			String site1 = "";
			String site2 = "";
			String site = info.getSite();
			if(site != null && !site.isEmpty()) {
				String[] sites = site.split(",");
				if(sites.length == 2) {
					site1 = sites[0];
					site2 = sites[1];
					request.setAttribute("site1", site1);
					request.setAttribute("site2", site2);
				}else {
					site1 = site;
					request.setAttribute("site1", site1);
				}
				
			}
				
			
			// 좋아요를 이미 체크 했다면 체크 되도록
			HttpSession session = request.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			String memberId = "";
			String recommend = "";
			if(loginMember != null) {
				memberId = loginMember.getMemberId();
				recommend = infoService.checkInfoRecommend(code, memberId);
			}
			
			// 리뷰 가져오기
			List<InfoReview> ir = infoService.selectAllReview(code);			
			
			// view단 처리
			if(ir != null && !ir.isEmpty())
				request.setAttribute("ir", ir);
			request.setAttribute("recommend", recommend);
			request.setAttribute("codeN", codeN);
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
