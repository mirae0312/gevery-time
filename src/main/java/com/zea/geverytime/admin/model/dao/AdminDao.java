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
import java.util.Map;
import java.util.Properties;

import com.zea.geverytime.customer.model.vo.ReportBoard;
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
	public List<Info> selectInfoBoard(Connection conn, Map<String, Object> param) {
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectInfoBoard");	
			ResultSet rset = null;
			List<Info> list = new ArrayList<>();
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, (int)param.get("start"));
				pstmt.setInt(2, (int)param.get("end"));
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Info info = new Info();
					info.setCode(rset.getString("code"));
					info.setMemberId(rset.getString("writer"));
					info.setBusinessName(rset.getString("business_name"));
					info.setHeadContent(rset.getString("head_content"));
					info.setRegCheck(rset.getString("reg_check"));
					info.setRegDate(rset.getDate("reg_date"));
					info.setDeleteCheck(rset.getString("delete_check"));
					
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
	public List<ReportBoard> selectReportList(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectReportList");	
		ResultSet rset = null;
		List<ReportBoard> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)param.get("start"));
			pstmt.setInt(2, (int)param.get("end"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReportBoard report = new ReportBoard();
				report.setReportCode(rset.getString("report_code"));
				report.setTitle(rset.getString("title"));
				report.setMemberId(rset.getString("member_id"));
				report.setContent(rset.getString("content"));
				report.setRegDate(rset.getDate("reg_date"));
				report.setReportCheck(rset.getString("report_check"));
				report.setDeleteCheck(rset.getString("delete_check"));
				
				list.add(report);
			}
		} catch (SQLException e) {
			throw new InfoBoardException("신고사항 불러오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	public int checkReport(Connection conn, String code, String output) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkReport");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			result = pstmt.executeUpdate();
			System.out.println("checkReport 반려 = " + result);
		} catch (SQLException e) {
			throw new InfoBoardException("신고사항 반려 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int deleteReport(Connection conn, String code, String output) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCheckReport");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			result = pstmt.executeUpdate();
			System.out.println("checkReport 삭제 = " + result);
		} catch (SQLException e) {
			throw new InfoBoardException("신고사항 삭제 실패!", e);
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int adminTotalCount(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("adminTotalCount");	
		ResultSet rset = null;
		int totalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt(1);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InfoBoardException("정보게시물 받아오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
			
		}	
		return totalCount;
		
	}
	public int reportTotalCount(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("reportTotalCount");	
		ResultSet rset = null;
		int totalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt(1);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InfoBoardException("정보게시물 받아오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
			
		}	
		return totalCount;
	}

}
