package com.zea.geverytime.market.usedgoods.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard;

public class UsedGoodsDao {
	
	Properties prop = new Properties();
	
	public UsedGoodsDao() {
		String filepath = UsedGoodsDao.class.getResource("/usedGoods-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertUgBoard(Connection conn, UsedGoodsBoard ugBoard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertUgBoard");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ugBoard.getTitle());
			pstmt.setString(2, ugBoard.getContent());
			pstmt.setString(3, ugBoard.getWriter());
			pstmt.setInt(4, ugBoard.getPrice());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertBoardAttachments(Connection conn, Attachment attach, String orCode) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertboardAttachments");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orCode);
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getLastBoard(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getLastBoard");
		ResultSet rset = null;
		int boardNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return boardNo;
	}

	public UsedGoodsBoard getUgGoodsBoard(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getUgGoodsBoard");
		ResultSet rset = null;
		UsedGoodsBoard board = new UsedGoodsBoard();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board.setNo(rset.getInt("no"));
				board.setTitle(rset.getString("title"));
				board.setContent(rset.getString("content"));
				board.setOrCode(rset.getString("or_code"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setWriter(rset.getString("writer"));
				board.setPrice(rset.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return board;
	}
}
