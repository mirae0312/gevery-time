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
 * Servlet implementation class InfoReviewModifyServlet
 */
@WebServlet("/info/reviewModify")
public class InfoReviewModifyServlet extends HttpServlet {
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
			String headContent = multipartRequest.getParameter("mHead");
			String bodyContent = multipartRequest.getParameter("mBody");
			String re1 = multipartRequest.getParameter("attachName0");
			String re2 = multipartRequest.getParameter("attachName1");
			
			InfoReview infoReview = new InfoReview();
			infoReview.setCode(code);
			infoReview.setrCode(rCode);
			infoReview.setHeadContent(headContent);
			infoReview.setContent(bodyContent);
			
			System.out.println("[infoReviewModifyServlet] infoReview : " + infoReview);
			
			
			File mPic1 = multipartRequest.getFile("mPic1");
			File mPic2 = multipartRequest.getFile("mPic2");
			
			// 새로 가져온 사진이 있다면 서버에서 우선 삭제
			List<Attachment> attachments = new ArrayList<>();
			if(mPic1 != null || mPic2 != null) {
				if(re1 != null) {
					File delRe = new File(saveDirectory, re1);
					boolean removed = delRe.delete();
					System.out.println("[InfoReviewModifyServlet] 기존파일1 삭제 : " + removed);
				}
				if(re2 != null) {
					File delRe = new File(saveDirectory, re2);
					boolean removed = delRe.delete();
					System.out.println("[InfoReviewModifyServlet] 기존파일2 삭제 : " + removed);
				}
				
				
				
				if(mPic1 != null) {
					Attachment attach1 = MvcUtils.makeAttachment(multipartRequest, "mPic1");
					attach1.setCode(rCode);
					attachments.add(attach1);
				}
				if(mPic2 != null) {
					Attachment attach2 = MvcUtils.makeAttachment(multipartRequest, "mPic2");
					attach2.setCode(rCode);
					attachments.add(attach2);
				}			
			}
			
			infoService.updateInfoReview(infoReview);
			infoService.updateAttachment(attachments);
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", "리뷰 수정 성공!");
			String location = request.getContextPath() + "/info/view?code=" + code;
			response.sendRedirect(location);			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
