package com.zea.geverytime.market.cart.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zea.geverytime.market.cart.model.vo.Cart;
import com.zea.geverytime.market.productsale.model.dao.ProductSaleDao;

public class CartDao {
	
	Properties prop = new Properties();
	
	public CartDao() {
		String filepath = ProductSaleDao.class.getResource("/cart-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int insertIntoCart(Connection conn, Cart cart) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertIntoCart");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cart.getMemberId());
			pstmt.setInt(2, cart.getProductNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int checkDupPdt(Connection conn, Cart cart) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkDupPdt");
		ResultSet rset = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cart.getMemberId());
			pstmt.setInt(2, cart.getProductNo());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
