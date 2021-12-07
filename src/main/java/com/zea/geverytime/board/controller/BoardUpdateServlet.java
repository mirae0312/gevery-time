package com.zea.geverytime.board.controller;

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
import com.zea.geverytime.board.model.service.BoardService;
import com.zea.geverytime.board.model.vo.Board;
import com.zea.geverytime.common.MvcFileRenamePolicy;
import com.zea.geverytime.common.MvcUtils;
import com.zea.geverytime.common.model.vo.Attachment;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		// 1. 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		// 2. 업무로직
		Board board = boardService.selectOneBoard(no);
		// 3. 응답처리
		request.setAttribute("board",board);
		request
			.getRequestDispatcher("/WEB-INF/views/board/boardUpdateForm.jsp")
			.forward(request, response);
		}catch(Exception e) {
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 1. 입력값 처리
			// 서버에 저장할 파일 먼저 처리 - MultipartRequest - 디렉토리, 최대사이즈, 인코딩, 리네임정책
			String saveDirectory = getServletContext().getRealPath("/upload/board");
			int max = 1024*1024*10;
			String encoding = "utf-8";
			FileRenamePolicy policy = new MvcFileRenamePolicy();
			MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, max, encoding, policy); // 서버 저장 완료
			
			// 2. 업무로직
			// 1) DB저장 작업
			Board board = new Board();
			board.setOrCode(multipartRequest.getParameter("orCode"));
			board.setNo(Integer.parseInt(multipartRequest.getParameter("no")));
			board.setTitle(multipartRequest.getParameter("title"));
			board.setContent(multipartRequest.getParameter("content"));
			
	
			Enumeration fileNames = multipartRequest.getFileNames();
			List<Attachment> list = new ArrayList<>();
			while(fileNames.hasMoreElements()) {
				String fileName = (String)fileNames.nextElement();
				if(!fileName.equals("files") && multipartRequest.getFile(fileName)!= null) {
					Attachment a = MvcUtils.makeAttachment(multipartRequest,fileName);
					a.setCode(board.getOrCode());
					list.add(a);
				}
			}
			if(!list.isEmpty()) {
				board.setAttachments(list);
			}
			System.out.println(board);
			int result = boardService.updateBoard(board);
			System.out.println(result);
			// 선택파일 삭제
			String delFiles[] = multipartRequest.getParameterValues("delFile");
			if(delFiles != null) {
				for(String temp : delFiles) {
					int no = Integer.parseInt(temp);
					Attachment a = boardService.selectOneAttachment(no);
					//서버에서 삭제
					String renamedFilename = a.getRenamedFilename();
					if(renamedFilename!=null) {
						File delFile = new File(saveDirectory,renamedFilename);
						delFile.delete();
						//DB에서 삭제
						result = boardService.deleteAttachment(no);
					}
				}
			}
			
			// 3. 응답처리
			String msg = result>0? "게시글 수정 완료":"게시글 수정 실패";
			System.out.println(result);
			System.out.println(msg);
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath()+"/board/boardView?no="+board.getNo());
		}catch(Exception e) {
			throw e;
		}
	}
}
