package com.zea.geverytime.market.productsale.controller;

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
import com.zea.geverytime.market.productsale.model.service.ProductSaleService;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

/**
 * Servlet implementation class ProductSaleBoardEnroll
 */
@WebServlet("/product/boardEnroll")
public class ProductSaleBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 첨부파일 
		String saveDirectory = getServletContext().getRealPath("/upload/market/productSale");

		int maxPostSize = 1024*1024*10;
		
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", policy);
		
		// 게시글정보
		String title = multipartRequest.getParameter("title");
		String author = multipartRequest.getParameter("author");
		String content = multipartRequest.getParameter("summernote");
		System.out.println("enrollServ@content : "+content);
		
		// 물품정보
		int pdtNo = Integer.parseInt(multipartRequest.getParameter("pdtNo"));
		
		// vo객체에 담기
		ProductBoard pdtBoard = new ProductBoard(0, null, title, content, null, author, pdtNo);
				
		// attachment original FileName, renamed FileName set
		Enumeration fileNames = multipartRequest.getFileNames();
		List<Attachment> attachments = new ArrayList<>();
		while(fileNames.hasMoreElements()){
			String fileName = (String) fileNames.nextElement();
			
			File upFile = multipartRequest.getFile(fileName);
			if(upFile != null) {
				Attachment attach = MvcUtils.makeAttachment(multipartRequest, fileName);
				attachments.add(attach);
			}
		}

		// board DB 등록 프로세스 진행
		int result = pdtService.productSaleBoardEnroll(pdtBoard, attachments);
		System.out.println("serv@result:" + result);
		
		
		// boardNo 가져오기
		int boardNo = pdtBoard.getBoardNo();
		System.out.println("bdEnrollServ@boardNo :" + boardNo);
		
		response.sendRedirect(request.getContextPath()+"/product/boardView?no="+boardNo);
	}

}
