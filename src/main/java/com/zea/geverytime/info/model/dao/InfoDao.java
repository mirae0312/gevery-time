package com.zea.geverytime.info.model.dao;

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
import com.zea.geverytime.info.model.vo.InfoAttachment;
import static com.zea.geverytime.common.JdbcTemplate.close;

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

	public List<Info> selectPopList(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectPopList");
		ResultSet rset = null;
		List<Info> popList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Info info = new Info();
				info.setHeadContent(rset.getString("head_content"));
				info.setBodyContents(rset.getString("body_contents"));
				info.setRecommend(rset.getInt("recommend_count"));
				
				
				List<InfoAttachment> attachments = new ArrayList<>();
				InfoAttachment attach = new InfoAttachment();
				attach.setNo(rset.getInt("no"));
				attach.setCode(rset.getString("code"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
				
				attachments.add(attach);
				info.setAttachments(attachments);
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

	public List<Info> selectAllList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllList");
		ResultSet rset = null;
		List<Info> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Info info = new Info();
				info.setBusinessName(rset.getString("business_name"));
				info.setHeadContent(rset.getString("head_content"));
				info.setBodyContents(rset.getString("body_contents"));
				
				
				List<InfoAttachment> attachments = new ArrayList<>();
				InfoAttachment attach = new InfoAttachment();
				attach.setNo(rset.getInt("no"));
				attach.setCode(rset.getString("code"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
				
				attachments.add(attach);
				info.setAttachments(attachments);
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
