package com.zea.geverytime.common.model.dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.zea.geverytime.common.model.exception.CommonException;
import com.zea.geverytime.customer.model.vo.ReportBoard;
import static com.zea.geverytime.common.JdbcTemplate.close;

public class CommonDao {
	
	private Properties prop = new Properties();
	
	public CommonDao() {
		File filepath = new File(CommonDao.class.getResource("/common-query.properties").getPath());
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertReport(Connection conn, ReportBoard report) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReport");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, report.getTitle());
			pstmt.setString(2, report.getContent());
			pstmt.setString(3, report.getReportCode());
			pstmt.setString(4, report.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CommonException("신고 등록 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
