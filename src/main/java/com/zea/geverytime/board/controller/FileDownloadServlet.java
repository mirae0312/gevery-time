package com.zea.geverytime.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.board.model.service.BoardService;
import com.zea.geverytime.common.model.vo.Attachment;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/board/fileDownload")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 1. 입력값 처리
			int no = Integer.parseInt(request.getParameter("no"));
			// 2. 업무처리
			// attachment정보 가져오기
			Attachment attach = boardService.selectOneAttachment(no);
			
			// server 내 파일 가져오기 (경로,파일이름)
			String saveDirectory = getServletContext().getRealPath("/upload/board");
			File file = new File(saveDirectory,attach.getRenamedFilename());
			// 인풋스트림에 파일내용 담기 (이진파일)
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			// 아웃풋 스트림과 response 아웃풋 스트림 연결
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			
		    // 3. 응답처리
			response.setContentType("application/octet-stream");
			String originalFilename = new String(attach.getOriginalFilename().getBytes("utf-8"),"iso-8859-1");
			response.setHeader("Content-Disposition","attachment; filename="+originalFilename);
			
			// bos에 파일정보 입력
			int data = -1;
			while((data = bis.read()) != -1) {
				bos.write(data);
			}
			bos.close();
			bis.close();
		}catch(Exception e) {
			
		}
	
	}


}
