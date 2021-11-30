package com.zea.geverytime.info.model.dao;

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

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.info.model.exception.InfoBoardException;
import com.zea.geverytime.info.model.vo.Info;

public class InfoDao {
	
	private Properties prop = new Properties();
	
	public InfoDao() {
		File filepath = new File(InfoDao.class.getResource("/info-query.properties").getPath());
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Info> selectPopList(String board, Connection conn) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(board) {
		case "info": 
			sql = prop.getProperty("selectPopList");
			break;
		case "hospital": 
			sql = prop.getProperty("selectHospitalPopList");
			break;
		case "cafe": 
			sql = prop.getProperty("selectCafePopList");
			break;
		case "restaurant": 
			sql = prop.getProperty("selectRestaurantPopList");
			break;
		case "pension": 
			sql = prop.getProperty("selectPensionPopList");
			break;
		case "salon": 
			sql = prop.getProperty("selectSalonPopList");
			break;
		}
		ResultSet rset = null;
		List<Info> popList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Info info = new Info();
				info.setCode(rset.getString("code"));
				info.setMemberId(rset.getString("writer"));
				info.setBusinessName(rset.getString("business_name"));
				info.setHeadContent(rset.getString("head_content"));
				info.setViewCount(rset.getInt("view_count"));
				info.setRecommend(rset.getInt("count"));
				
				popList.add(info);				
			}
		} catch (SQLException e) {
			throw new InfoBoardException("게시글 불러오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return popList;
	}

	public List<Info> selectAllList(String board, Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		String sql = "";
		System.out.println("[Dao] board : " + board);
		switch(board) {
		case "info": 
			sql = prop.getProperty("selectAllList");
			break;
		case "hospital":
			sql = prop.getProperty("selectHospitalAllList");
			break;
		case "cafe": 
			sql = prop.getProperty("selectCafeAllList");
			break;
		case "restaurant":
			sql = prop.getProperty("selectRestaurantAllList");
			break;
		case "pension": 
			sql = prop.getProperty("selectPensionAllList");
			break;
		case "salon": 
			sql = prop.getProperty("selectSalonAllList");
			break;
		}
		
		System.out.println("[dao] query : " + sql);
		
		ResultSet rset = null;
		List<Info> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Info info = new Info();
				info.setCode(rset.getString("code"));
				info.setMemberId(rset.getString("writer"));
				info.setBusinessName(rset.getString("business_name"));
				info.setHeadContent(rset.getString("head_content"));
				info.setViewCount(rset.getInt("view_count"));
				info.setRecommend(rset.getInt("count"));
				
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

	public List<Attachment> selectPopAttach(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectPopAttach");
		ResultSet rset = null;
		List<Attachment> attach = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment info = new Attachment();
				info.setNo(rset.getInt("no"));
				info.setOriginalFilename(rset.getString("original_filename"));
				info.setRenamedFilename(rset.getString("renamed_filename"));
				info.setRegDate(rset.getDate("reg_date"));
				
				attach.add(info);
			}
			
		} catch (SQLException e) {
			throw new InfoBoardException("게시물 첨부파일 불러오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return attach;
	}

	public List<Attachment> selectAllAttach(Connection conn, String code, int start, int end) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllAttach");
		ResultSet rset = null;
		List<Attachment> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment info = new Attachment();
				info.setNo(rset.getInt("no"));
				info.setOriginalFilename(rset.getString("original_filename"));
				info.setRenamedFilename(rset.getString("renamed_filename"));
				info.setRegDate(rset.getDate("reg_date"));
				
				list.add(info);
			}
			
		} catch (SQLException e) {
			throw new InfoBoardException("게시물 첨부파일 불러오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}
