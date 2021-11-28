package com.zea.geverytime.board.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.zea.geverytime.board.model.dao.BoardDao;
import com.zea.geverytime.board.model.vo.Board;
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

	public int getTotalContentCount() {
		Connection conn = getConnection();
		int count = boardDao.getTotalContentCount(conn);
		close(conn);
		return count;
	}
}
