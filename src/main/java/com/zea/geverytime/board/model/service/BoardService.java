package com.zea.geverytime.board.model.service;

import java.sql.Connection;
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
			String code = boardDao.selectLastBoardCode(conn);
			//attachment 등록
			for(Attachment a : board.getAttachments()) {
				a.setCode(code);
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
}
