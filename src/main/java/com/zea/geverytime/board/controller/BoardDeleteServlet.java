package com.zea.geverytime.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.board.model.service.BoardService;
import com.zea.geverytime.board.model.vo.Board;
import com.zea.geverytime.common.model.vo.Attachment;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			int no = Integer.parseInt(request.getParameter("no"));
			
			// 2. 업무처리
			// 게시물 고유코드 가져와서 첨부파일 삭제, 게시물 삭제, 서버 파일 삭제
			// 1) 삭제할 게시물 정보 (첨부파일)
			Board board = boardService.selectOneBoard(no);
			List<Attachment> list = board.getAttachments();
			System.out.println("10");
			//디비 내 파일정보 삭제
			boardService.deleteBoardAttachments(board.getOrCode());
			if(list != null && !list.isEmpty()) {
				for(Attachment a : list) {
					//서버에서 파일 삭제
					File file = new File(getServletContext().getRealPath("/upload/board"),a.getRenamedFilename());
					if(file!=null) {
						file.delete();

					}
				}
			}
			int result = boardService.deleteBoard(no);

			// 3. 응답처리
			String msg = result>0? "게시글 삭제 완료":"게시글 삭제 실패";
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath()+"/board/freeBoard");
			
		}catch(Exception e) {
			throw e;
		}
	}
}
