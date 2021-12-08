package com.zea.geverytime.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zea.geverytime.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int no = Integer.parseInt(request.getParameter("commentNo"));
			int result = boardService.deleteComment(no);
			
			String msg = result>0? "댓글 삭제 완료":"댓글 삭제 실패";
			request.getSession().setAttribute("msg",msg);
			response.sendRedirect(request.getContextPath() + "/board/boardView?no=" + boardNo);
			
		}catch(Exception e) {
			throw e;
		}
	}

}
