package com.zea.geverytime.board.controller;

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
import com.zea.geverytime.board.model.service.BoardService;
import com.zea.geverytime.board.model.vo.Board;
import com.zea.geverytime.common.MvcFileRenamePolicy;
import com.zea.geverytime.common.MvcUtils;
import com.zea.geverytime.common.model.vo.Attachment;

/**
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService(); 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	
			// 1. 사용자 입력값 처리
			String saveDirectory = getServletContext().getRealPath("/upload/board");
			int max = 1024*1024*10; // 10메가바이트
			String encoding = "utf-8";
			FileRenamePolicy policy = new MvcFileRenamePolicy();
			
			MultipartRequest multipartRequest = new MultipartRequest(request,saveDirectory,max,encoding,policy);
			
			String title = multipartRequest.getParameter("title");
			String writer = multipartRequest.getParameter("writer");
			String content = multipartRequest.getParameter("content");
			String boardCode = multipartRequest.getParameter("boardCode");
			Board board = new Board();
			board.setWriter(writer);
			board.setTitle(title);
			board.setContent(content);
			board.setOrCode(boardCode);
			
			// 첨부파일 있는 경우 board객체에 첨부파일 List 추가
			Enumeration fileNames = multipartRequest.getFileNames();
			List<Attachment> list = new ArrayList<>();
			while(fileNames.hasMoreElements()) {
				String fileName = (String)fileNames.nextElement();
				if(!fileName.equals("files") && multipartRequest.getFile(fileName)!= null) {
					Attachment a = MvcUtils.makeAttachment(multipartRequest,fileName);
					list.add(a);
				}
			}
		
			// 2. 업무처리
			int result = boardService.enrollBoard(board);
			String msg = result>0? "게시물 등록 성공" : "게시물 등록 실패"; 
			
			// 3. 응답처리
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath()+"/board/boardView?no="+board.getNo());
		}catch(Exception e) {
			throw e;
		}
	}

}
