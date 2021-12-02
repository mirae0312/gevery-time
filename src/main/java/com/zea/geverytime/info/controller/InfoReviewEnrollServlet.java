package com.zea.geverytime.info.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.zea.geverytime.common.MvcFileRenamePolicy;
import com.zea.geverytime.common.MvcUtils;
import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.info.model.service.InfoService;
import com.zea.geverytime.info.model.vo.InfoReview;

/**
 * Servlet implementation class InfoReviewEnrollServlet
 */
@WebServlet("/info/insertReview")
public class InfoReviewEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoService infoService = new InfoService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			
			String saveDirectory = getServletContext().getRealPath("/upload/info");
			
			int maxPostSize = 1024 * 1024 * 10;
			String encoding = "utf-8";
			
			FileRenamePolicy policy = new MvcFileRenamePolicy();
			
			MultipartRequest multipartRequest = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
								
			
			String memberId = multipartRequest.getParameter("infoWriter");
			String codeN = multipartRequest.getParameter("categoryNo");
			String code = multipartRequest.getParameter("infoCode");
			String headContent = multipartRequest.getParameter("headContent");
			String bodyContent = multipartRequest.getParameter("bodyContent");

			InfoReview ir = new InfoReview(0, null, code, memberId, null, headContent, bodyContent, null, null);
			
			// 리뷰를 쓴적 있는지 확인
			InfoReview check = infoService.checkReview(code, memberId);
			
						
			File reviewPic1 = multipartRequest.getFile("reviewPic1");
			File reviewPic2 = multipartRequest.getFile("reviewPic2");
			
			if(reviewPic1 != null || reviewPic2 != null) {
				List<Attachment> attachments = new ArrayList<>();
				
				if(reviewPic1 != null) {
					Attachment attach1 = MvcUtils.makeAttachment(multipartRequest, "reviewPic1");
					attachments.add(attach1);
				}
				if(reviewPic2 != null) {
					Attachment attach2 = MvcUtils.makeAttachment(multipartRequest, "reviewPic2");
					attachments.add(attach2);
				}
				ir.setAttachments(attachments);
			}
			
			
			
			String location = request.getContextPath() + "/info/view?code=" + code;
			int result = 0;
			if(check.getrCode() == null || check.getrCode().isEmpty()) {
				result = infoService.insertInfoReview(ir, codeN);
				session.setAttribute("msg", "리뷰 작성 성공!");
			}else if((check.getrCode() != null || !check.getrCode().isEmpty()) &&
					(check.getHeadContent() == null || !check.getHeadContent().isEmpty())) {
				ir.setrCode(check.getrCode());
				result = infoService.updateInfoReview(ir);
				session.setAttribute("msg", "리뷰 작성 성공!");
			}else {
				session.setAttribute("msg", "리뷰는 한번만 작성 가능합니다!");
			}
			response.sendRedirect(location);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
