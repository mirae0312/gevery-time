package com.zea.geverytime.info.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
						
			int no = Integer.parseInt(multipartRequest.getParameter("attachNo"));			
			String code = multipartRequest.getParameter("pCode");
			String rCode = multipartRequest.getParameter("reviewCode");
			String headContent = multipartRequest.getParameter("mHead");
			String bodyContent = multipartRequest.getParameter("mBody");
			
			InfoReview infoReview = new InfoReview();
			infoReview.setCode(code);
			infoReview.setrCode(rCode);
			infoReview.setHeadContent(headContent);
			infoReview.setContent(bodyContent);
			
			System.out.println("[infoReviewModifyServlet] infoReview : " + infoReview);
			
			Enumeration fileNames = multipartRequest.getFileNames();
			List<Attachment> attachments = new ArrayList<>();
			while(fileNames.hasMoreElements()) {
				String fileName = (String) fileNames.nextElement();
				System.out.println("[infoReviewModifyServlet] fileName : " + fileName);
				File upFile = multipartRequest.getFile(fileName);
				if(upFile != null) {
					Attachment attach = MvcUtils.makeAttachment(multipartRequest, fileName);
					attach.setCode(rCode);
					attachments.add(attach);
				}
			}
			
			if(!attachments.isEmpty()) {
				infoReview.setAttachments(attachments);
			}
			
			if(delFiles != null) {
				for(String temp)
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
