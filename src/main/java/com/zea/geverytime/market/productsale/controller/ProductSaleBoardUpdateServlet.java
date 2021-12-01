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
 * Servlet implementation class ProductSaleBoardUpdateServlet
 */
@WebServlet("/product/boardUpdate")
public class ProductSaleBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductSaleService pdtService = new ProductSaleService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 첨부파일 
				String saveDirectory = getServletContext().getRealPath("/upload/market/productSale");
				System.out.println("saveDirectory : "+saveDirectory);

				int maxPostSize = 1024*1024*10;
				
				FileRenamePolicy policy = new MvcFileRenamePolicy();
				
				MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", policy);
				
				// 게시글정보
				String title = multipartRequest.getParameter("title");
				String content = multipartRequest.getParameter("summernote");
				int boardNo = Integer.parseInt(multipartRequest.getParameter("boardNo"));
				String orCode = multipartRequest.getParameter("orCode");
				
				ProductBoard board = new ProductBoard();
				board.setTitle(title);
				board.setContent(content);
				board.setBoardNo(boardNo);
				board.setOrCode(orCode);
				
				
				// attachment DB 등록 프로세스 진행
				Enumeration fileNames = multipartRequest.getFileNames();
				List<Attachment> attachments = new ArrayList<>();
				while(fileNames.hasMoreElements()){
					String fileName = (String) fileNames.nextElement();
					System.out.println("pdtBoardEnrollServ@fileName : "+fileName);
					
					File upFile = multipartRequest.getFile(fileName);
					if(upFile != null) {
						Attachment attach = MvcUtils.makeAttachment(multipartRequest, fileName);
						attachments.add(attach);
					}
				}
				
				board.setAttachments(attachments);
				
				// DB 등록
				int result = pdtService.productSaleBoardUpdate(board);
				
				String msg = "";
				if(result > 0) {
					msg = "수정에 성공했습니다.";
				}
				
				request.getSession().setAttribute("msg", msg);
				
				response.sendRedirect(request.getContextPath()+"/product/boardView?no="+boardNo);
	}

}
