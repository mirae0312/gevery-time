package com.zea.geverytime.market.point.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zea.geverytime.market.point.model.exception.PointException;
import com.zea.geverytime.market.point.model.vo.PointHistory;
import com.zea.geverytime.market.productsale.model.dao.ProductSaleDao;

public class PointDao {
	
	Properties prop = new Properties();
	
	public PointDao() {
		String filepath = ProductSaleDao.class.getResource("/point-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPointBalance(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int point = 0;
		String sql = prop.getProperty("getPointBalance");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				point = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new PointException("포인트 잔액 조회 오류", e);
		} finally {
			close(pstmt);
			close(rset);
		}
		return point;
	}

	public int withdrawPoint(Connection conn, String memberId, int usePoint) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("withdrawPoint");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, usePoint);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new PointException("포인트 출금 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getPointNo(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getPointNo");
		ResultSet rset = null;
		int pointNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pointNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new PointException("포인트 번호 조회 오류", e);
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return pointNo;
	}

	public int insertPointHistory(Connection conn, PointHistory ht, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPointHistory");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			if(ht.getDiv().equals("O")) {
				pstmt.setInt(2, ht.getWithdraw());
				pstmt.setInt(3, 0);
			} else {
				pstmt.setInt(2, 0);
				pstmt.setInt(3, ht.getDeposit());
			}
			pstmt.setString(4, ht.getHistory());
			pstmt.setString(5, ht.getPurchaseUid());
			pstmt.setString(6, ht.getDiv());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new PointException("포인트 내역 추가 오류", e);
		}
		return result;
	}
	
	
	
	
}
