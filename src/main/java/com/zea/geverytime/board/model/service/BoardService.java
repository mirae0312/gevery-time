package com.zea.geverytime.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zea.geverytime.board.model.dao.BoardDao;
import com.zea.geverytime.board.model.vo.Board;
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
			String code = board.getOrCode() + "-" +boardDao.selectLastBoardCode(conn); // 식별번호-seq번호
			System.out.println(code);
			//attachment 등록
			List<Attachment> list = board.getAttachments();
			if(list!=null && !list.isEmpty()) {
				for(Attachment a : board.getAttachments()) {
					a.setCode(code);
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

	public Attachment selectOneAttahment(int no) {
		Connection conn = getConnection();
		Attachment attach = boardDao.selectOneAttahment(conn, no);
		close(conn);
		return attach;
	}
}
