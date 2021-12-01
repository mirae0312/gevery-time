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
//		System.out.println("[InfoDao] board : " + board);
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

	public int insertInfo(Connection conn, Info info) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertInfo");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, info.getMemberId());
			pstmt.setString(2, info.getBusinessNo());
			pstmt.setString(3, info.getHeadContent());
			pstmt.setString(4, info.getBodyContents());
			pstmt.setString(5, info.getServiceContent());
			pstmt.setString(6, info.getSite());
			pstmt.setString(7, info.getStartHour());
			pstmt.setString(8, info.getEndHour());
			pstmt.setString(9, info.getStartLaunch());
			pstmt.setString(10, info.getEndLaunch());
			pstmt.setString(11, info.getStartDinner());
			pstmt.setString(12, info.getEndDinner());
			pstmt.setString(13, info.getHoliday());
			pstmt.setString(14, info.getRoadGuide());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new InfoBoardException("게시물 등록 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectCode(Connection conn, Info info) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectCode");
		ResultSet rset = null;
		String code = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, info.getMemberId());
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				code = rset.getString(1);
			}
			
		} catch (SQLException e) {
			throw new InfoBoardException("코드 가져오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return code;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, attach.getCode());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new InfoBoardException("첨부파일 저장 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Info selectBeforeWrite(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBeforeWrite");
		ResultSet rset = null;
		Info info = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				info = new Info();
				info.setMemberId(rset.getString("business_id"));
				info.setBusinessNo(rset.getString("business_no"));
				info.setBusinessName(rset.getString("business_name"));
				info.setBusinessAddress(rset.getString("business_address"));
				info.setBusinessTel(rset.getString("business_tel"));
				info.setLocation(rset.getString("location"));
				
			}
		} catch (SQLException e) {
			throw new InfoBoardException("사업자 정보 불러오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return info;
	}

	public String checkInfoBoard(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkInfoBoard");
		ResultSet rset = null;
		String check = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				check = rset.getString(1);
			}
		} catch (SQLException e) {
			throw new InfoBoardException("게시글 등록자 확인 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return check;
	}

	public Info selectOneView(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOneView");
		ResultSet rset = null;
		Info info = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rset.getString("code"));			
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				info = new Info();
				info.setCode(rset.getString("code"));
				info.setMemberId(rset.getString("writer"));
				info.setBusinessNo(rset.getString("business_no"));
				info.setViewCount(rset.getInt("view_count"));
				info.setHeadContent(rset.getString("head_content"));
				info.setBodyContents(rset.getString("body_contents"));
				info.setServiceContent(rset.getString("service_content"));
				info.setSite(rset.getString("site"));
				info.setStartHour(rset.getString("start_hour"));
				info.setEndHour(rset.getString("end_hour"));
				info.setStartLaunch(rset.getString("start_launch"));
				info.setEndLaunch(rset.getString("end_launch"));
				info.setStartDinner(rset.getString("start_dinner"));
				info.setEndDinner(rset.getString("end_dinner"));
				info.setHoliday(rset.getString("holiday"));
				info.setRoadGuide(rset.getString("road_guide"));
				info.setRegDate(rset.getDate("reg_date"));
				info.setBusinessName(rset.getString("business_name"));
				info.setBusinessAddress(rset.getString("business_address"));
				info.setBusinessTel(rset.getString("business_tel"));
				info.setLocation(rset.getString("location"));
				
				String attachCode = info.getCode();
				if(attachCode != null && !attachCode.isEmpty()) {
					List<Attachment> attachments = new ArrayList<>();
					do {
						Attachment attach = new Attachment();
						attach.setNo(rset.getInt("no"));
						attach.setCode(rset.getString("or_no"));
						attach.setOriginalFilename(rset.getString("original_filename"));
						attach.setRenamedFilename(rset.getString("renamed_filename"));
						attach.setRegDate(rset.getDate("reg_date"));
						attachments.add(attach);
					}while(rset.next());
					info.setAttachments(attachments);
				}
				
			}
		} catch (SQLException e) {
			throw new InfoBoardException("게시글 가져오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return info;
	}

	public int updateReadCount(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
		} catch (SQLException e) {
			throw new InfoBoardException("조회수 카운트 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
