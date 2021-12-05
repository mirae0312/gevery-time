package com.zea.geverytime.info.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.zea.geverytime.common.MvcFileRenamePolicy;
import com.zea.geverytime.info.model.service.InfoService;

/**
 * Servlet implementation class InfoReviewDeleteServlet
 */
@WebServlet("/info/deleteReview")
public class InfoReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String saveDirectory = getServletContext().getRealPath("/upload/info");
			
			int maxPostSize = 1024 * 1024 * 10;
			String encoding = "utf-8";
			
			FileRenamePolicy policy = new MvcFileRenamePolicy();
			
			MultipartRequest multipartRequest = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			String code = multipartRequest.getParameter("pCode");
			String rCode = multipartRequest.getParameter("code");
			String attachName0 = multipartRequest.getParameter("attachName0");
			String attachName1 = multipartRequest.getParameter("attachName1");

			if(attachName0 != null) {
				File del = new File(saveDirectory, attachName0);
				boolean removed = del.delete();
				System.out.println("[InfoReviewDeleteServlet] 사진삭제1 : " + removed);
			}
			if(attachName1 != null) {
				File del = new File(saveDirectory, attachName1);
				boolean removed = del.delete();
				System.out.println("[InfoReviewDeleteServlet] 사진삭제2 : " + removed);
			}
			
			infoService.deleteReview(rCode);
			infoService.deleteAttachment(rCode);
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", "리뷰 삭제 성공!");
			String location = request.getContextPath() + "/info/view?code=" + code;
			response.sendRedirect(location);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
