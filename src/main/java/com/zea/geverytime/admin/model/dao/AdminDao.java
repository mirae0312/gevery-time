package com.zea.geverytime.admin.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.zea.geverytime.info.model.exception.InfoBoardException;
import com.zea.geverytime.info.model.vo.Info;
import com.zea.geverytime.info.model.vo.InfoEntity;

public class AdminDao {
	
	private Properties prop = new Properties();
	
	public AdminDao() {
	File filepath = new File(AdminDao.class.getResource("/admin-query.properties").getPath());
	
	try {
		prop.load(new FileReader(filepath));
	} catch (IOException e) {
		e.printStackTrace();
	}
}
//	public List<Info> selectInfoBoard(Connection conn) {
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("selectInfoBoard");
//		ResultSet rset = null;
//		List<Info> list = new ArrayList<>();
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rset = pstmt.executeQuery();
//			
//			while(rset.next()) {
//				Info info = new Info();
//
//				info.setCode(rset.getString("code"));
//				info.setMemberId(rset.getString("writer"));
//				info.setBusinessName(rset.getString("business_name"));
//				info.setHeadContent(rset.getString("head_content"));
//				info.setRegCheck(rset.getString("reg_check"));
//				info.setRegDate(rset.getDate("reg_date"));
//				
//				list.add(info);
//			}
//			
//		} catch (SQLException e) {
//			throw new InfoBoardException("게시글 목록 조회 오류!", e);
//		} finally {
//			close(rset);
//			close(pstmt);
//		}
//		return list;
//		
//	}
	public List<Info> selectInfoBoard(Connection conn) {
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectInfoBoard");	
			ResultSet rset = null;
			List<Info> list = new ArrayList<>();
			
			try {
				pstmt = conn.prepareStatement(sql);
				
//				pstmt.setInt(1, start);
//				pstmt.setInt(2, end);
				
				rset = pstmt.executeQuery();
				while(rset.next()) {
					Info info = new Info();
					info.setCode(rset.getString("code"));
					info.setMemberId(rset.getString("writer"));
					info.setBusinessName(rset.getString("business_name"));
					info.setHeadContent(rset.getString("head_content"));
					info.setRegCheck(rset.getString("reg_check"));
					info.setRegDate(rset.getDate("reg_date"));
					
					list.add(info);
				}
			} catch (SQLException e) {
				throw new InfoBoardException("게시글 불러오기 실패!", e);
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}

}
