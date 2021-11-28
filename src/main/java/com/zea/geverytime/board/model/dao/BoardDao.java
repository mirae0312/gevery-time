package com.zea.geverytime.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.zea.geverytime.board.model.vo.Board;
import static com.zea.geverytime.common.JdbcTemplate.*;

public class BoardDao {
	Properties prop = new Properties();
	
	public BoardDao(){
		String directory = BoardDao.class.getResource("/board-query.properties").getPath();
		try {
			prop.load(new FileReader(directory));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Board> selectAllContentList(Map<String, Object> map, Connection conn) {
		List<Board> list = new ArrayList<>();
		String key = "selectAllContentList"+(String)map.get("boardSelect");
		String sql = prop.getProperty(key);
		System.out.println(sql);
		System.out.println((String)map.get("sort"));
		System.out.println((int)map.get("startNum"));
		System.out.println((int)map.get("endNum"));
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,(String)map.get("sort"));
			pstmt.setInt(2,(int)map.get("startNum"));
			pstmt.setInt(3,(int)map.get("endNum"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("no");
				String orCode = rset.getString("or_code");
				String title = rset.getString("title");
				String writer = rset.getString("writer");
				String content = rset.getString("content");
				int readCount = rset.getInt("read_count");
				int likeCount = rset.getInt("like_count");
				Date regDate = rset.getDate("reg_date");
				Board board = new Board(no,orCode,title,writer,content,readCount,likeCount,regDate);
				list.add(0,board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int getTotalContentCount(Connection conn) {
		int count = 0;
		String sql = prop.getProperty("getTotalContentCount");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}
}
