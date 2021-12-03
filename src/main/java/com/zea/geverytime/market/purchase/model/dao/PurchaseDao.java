package com.zea.geverytime.market.purchase.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zea.geverytime.market.productsale.model.dao.ProductSaleDao;
import com.zea.geverytime.market.purchase.model.vo.PurchaseHistory;

public class PurchaseDao {

	Properties prop = new Properties();
	
	public PurchaseDao() {
		String filepath = ProductSaleDao.class.getResource("/purchase-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int insertPurchaseHistory(Connection conn, PurchaseHistory ph) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPurchaseHistory");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ph.getMemberId());
			pstmt.setInt(2, ph.getPrice());
			pstmt.setString(3, ph.getUid());
			pstmt.setString(4, ph.getMuid());
			pstmt.setString(5, ph.getState());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getHistoryNo(Connection conn, String uid) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getHistoryNo");
		ResultSet rset = null;
		int historyNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				historyNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return historyNo;
	}

	public int insertPurchaseDetailHistory(Connection conn, int historyNo, int pdtNo, int pdtCount) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPurchaseDetailHistory");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, historyNo);
			pstmt.setInt(2, pdtNo);
			pstmt.setInt(3, pdtCount);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
