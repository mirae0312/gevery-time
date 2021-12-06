package com.zea.geverytime.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zea.geverytime.board.model.dao.BoardDao;
import com.zea.geverytime.board.model.vo.Board;
import com.zea.geverytime.board.model.vo.BoardComment;
import com.zea.geverytime.common.model.vo.Attachment;


import static com.zea.geverytime.common.JdbcTemplate.*;

public class BoardService {
	BoardDao boardDao = new BoardDao();
	
	public final String SORT_LATEST = "no";
	public final String SORT_LIKE = "like_count";
	public final String SORT_READ = "read_count";
	
	public List<Board> selectAllContentList(Map<String, Object> map) {
		
		Connection conn = getConnection();
		List<Board> list = boardDao.selectAllContentList(map,conn);
		close(conn);	
		return list;
	}

	public int getTotalContentCount(Map<String, Object> map) {
		Connection conn = getConnection();
		int count = boardDao.getTotalContentCount(conn,map);
		close(conn);
		return count;
	}

	public int enrollBoard(Board board) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			//게시물 등록
			boardDao.enrollBoard(conn,board);
			//게시물 번호 가져오기
			int no = boardDao.selectLastBoardNo(conn);
			Board newBoard = boardDao.selectOneBoard(conn,no); // 식별번호-seq번호
			board.setNo(newBoard.getNo());
			board.setOrCode(newBoard.getOrCode());
			//attachment 등록
			List<Attachment> list = board.getAttachments();
			if(list!=null && !list.isEmpty()) {
				for(Attachment a : board.getAttachments()) {
					a.setCode(board.getOrCode());
					boardDao.enrollBoardAttachment(conn, a);
				}
			}
			commit(conn);
			result = 1;
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return result;
	}

	public Board selectOneBoard(int no) {
		Connection conn = getConnection();
		Board board = boardDao.selectOneBoard(conn, no);
		if(board.getAttachCount()>0) {
			List<Attachment> list = boardDao.selectBoardAttachments(conn, board.getOrCode());
			board.setAttachments(list);
		}
		close(conn);
		return board;
	}

	public Attachment selectOneAttachment(int no) {
		Connection conn = getConnection();
		Attachment attach = boardDao.selectOneAttachment(conn, no);
		close(conn);
		return attach;
	}

	public int updateBoard(Board board) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			result = boardDao.updateBoard(conn, board);
			List<Attachment> list = board.getAttachments();
			if(list != null && !list.isEmpty()) {
				for(Attachment a : list) {
					result = boardDao.enrollBoardAttachment(conn, a);
				}
			}
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int deleteAttachment(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = boardDao.deleteAttachment(conn, no);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return result;
	}

	public int  deleteBoard(int no) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = boardDao.deleteBoard(conn, no);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}

	public int deleteBoardAttachments(String orCode) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = getConnection();
			result = boardDao.deleteBoardAttachments(conn, orCode);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally{
			close(conn);
		}
		
		return result;
	}

	public List<BoardComment> getBoardCommentList(int no) {
		Connection conn = getConnection();
		List<BoardComment> list = boardDao.getBoardCommentList(conn, no);
		close(conn);
		return list;
	}

	public int enrollBoardComment(BoardComment bc) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = getConnection();
			result = boardDao.enrollBoardComment(conn, bc);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int readCountUp(int boardNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = boardDao.readCountUp(conn,boardNo);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}

	public List<Board> getFreePopularList(int count) {
		Connection conn = getConnection();
		List<Board> list = boardDao.getFreePopularList(conn,count);
		close(conn);
		return list;
	} 
	public List<Board> getReviewPopularList(int count) {
		Connection conn = getConnection();
		List<Board> list = boardDao.getReviewPopularList(conn,count);
		close(conn);
		return list;
	}

	public List<Board> selectOtherContentList(Map<String, Object> map) {
		Connection conn = getConnection();
		List<Board> list = boardDao.selectOtherContentList(conn, map);
		close(conn);
		return list;
	}

	public int getTotalOtherContentCount(Map<String, Object> map) {
		Connection conn = getConnection();
		int count = boardDao.getTotalOtherContentCount(conn,map);
		close(conn);
		return count;
	}

	public int upCommentLike(Map<String, Object> map) {
		Connection conn = null;
		int count = -1;
		
		try {
			conn = getConnection();
			int check = boardDao.checkCommentLike(conn, map);
			if(check == 0) {
				int result = boardDao.upCommentLike(conn, map);
				count = boardDao.getCommentLike(conn, (int)map.get("no"));
			}
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return count;
	}
	public int upBoardLike(Map<String, Object> map) {
		Connection conn = null;
		int count = -1;
		
		try {
			conn = getConnection();
			int check = boardDao.checkBoardLike(conn, map);
			if(check == 0) {
				int result = boardDao.upBoardLike(conn, map);
				count = boardDao.getBoardLike(conn, (int)map.get("no"));
			}
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return count;
	}


}
