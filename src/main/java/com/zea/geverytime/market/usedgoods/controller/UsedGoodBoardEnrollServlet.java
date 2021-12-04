package com.zea.geverytime.market.usedgoods.controller;

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
import com.zea.geverytime.market.usedgoods.model.service.UsedGoodsService;
import com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard;

/**
 * Servlet implementation class UsedGoodBoardEnrollServlet
 */
@WebServlet("/ugGoods/boardEnroll")
public class UsedGoodBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsedGoodsService ugService = new UsedGoodsService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 첨부파일 관리
		String saveDirectory = getServletContext().getRealPath("/upload/market/UgSale");
		int maxPostSize = 1024*1024*10;
		
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", policy);
		
		// 요청 파라미터
		String title = multipartRequest.getParameter("title");
		String writer = multipartRequest.getParameter("writer");
		int price = Integer.parseInt(multipartRequest.getParameter("price"));
		String content = multipartRequest.getParameter("summernote");
		
		// attachment DB 등록
		Enumeration fileNames = multipartRequest.getFileNames();
		List<Attachment> attachments = new ArrayList<>();
		while(fileNames.hasMoreElements()) {
			String fileName = (String) fileNames.nextElement();
			
			File upFile = multipartRequest.getFile(fileName);
			if(upFile != null) {
				Attachment attach = MvcUtils.makeAttachment(multipartRequest, fileName);
				attachments.add(attach);
			}
		}
		
		UsedGoodsBoard ugBoard = new UsedGoodsBoard(0, title, content, null, null, writer, price);
		ugBoard.setAttachments(attachments);
		
		// 게시물 DB 등록
		int result = ugService.insertUgBoard(ugBoard, attachments);
		
		// 게시물 상태 등록
		int boardNo = ugBoard.getNo();
		int stateResult = ugService.insertUgBoardState(boardNo);
		
		String msg = "";
		if(result == 1 && stateResult == 1) {
			msg = "판매글이 등록되었습니다.";
		} else if(result != 1 || stateResult != 1){
			msg = "판매글이 정상적으로 등록되지 않았습니다.";
		}
		
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath()+"/ugGoods/boardView?boardNo="+boardNo);
	}

}
