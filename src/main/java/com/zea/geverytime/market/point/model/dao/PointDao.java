package com.zea.geverytime.market.point.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static com.zea.geverytime.common.JdbcTemplate.*;

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
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return point;
	}
	
	
	
	
}
