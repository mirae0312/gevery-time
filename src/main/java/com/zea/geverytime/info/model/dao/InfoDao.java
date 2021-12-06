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
import com.zea.geverytime.info.model.vo.CafeRestaurant;
import com.zea.geverytime.info.model.vo.Hospital;
import com.zea.geverytime.info.model.vo.Info;
import com.zea.geverytime.info.model.vo.InfoReview;
import com.zea.geverytime.info.model.vo.Pension;
import com.zea.geverytime.info.model.vo.Salon;

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
				info.setCommentCount(rset.getInt("comment_count"));
				
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
				info.setRegDate(rset.getDate("reg_date"));
				info.setCommentCount(rset.getInt("comment_count"));
				info.setLocation(rset.getString("location"));
				
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
	
	public List<Info> selectAllListAsc(String board, Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(board) {
		case "info": 
			sql = prop.getProperty("selectAllListAsc");
			break;
		case "hospital":
			sql = prop.getProperty("selectHospitalAllListAsc");
			break;
		case "cafe": 
			sql = prop.getProperty("selectCafeAllListAsc");
			break;
		case "restaurant":
			sql = prop.getProperty("selectRestaurantAllListAsc");
			break;
		case "pension": 
			sql = prop.getProperty("selectPensionAllListAsc");
			break;
		case "salon": 
			sql = prop.getProperty("selectSalonAllListAsc");
			break;
		}
		
		
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
				info.setRegDate(rset.getDate("reg_date"));
				info.setCommentCount(rset.getInt("comment_count"));
				info.setLocation(rset.getString("location"));
				
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
	
	public List<Info> selectAllListView(String board, Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(board) {
		case "info": 
			sql = prop.getProperty("selectAllListView");
			break;
		case "hospital":
			sql = prop.getProperty("selectHospitalAllListView");
			break;
		case "cafe": 
			sql = prop.getProperty("selectCafeAllListView");
			break;
		case "restaurant":
			sql = prop.getProperty("selectRestaurantAllListView");
			break;
		case "pension": 
			sql = prop.getProperty("selectPensionAllListView");
			break;
		case "salon": 
			sql = prop.getProperty("selectSalonAllListView");
			break;
		}
		
		
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
				info.setRegDate(rset.getDate("reg_date"));
				info.setCommentCount(rset.getInt("comment_count"));
				info.setLocation(rset.getString("location"));
				
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
	
	public List<Info> selectAllListPop(String board, Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(board) {
		case "info": 
			sql = prop.getProperty("selectAllListPop");
			break;
		case "hospital":
			sql = prop.getProperty("selectHospitalAllListPop");
			break;
		case "cafe": 
			sql = prop.getProperty("selectCafeAllListPop");
			break;
		case "restaurant":
			sql = prop.getProperty("selectRestaurantAllListPop");
			break;
		case "pension": 
			sql = prop.getProperty("selectPensionAllListPop");
			break;
		case "salon": 
			sql = prop.getProperty("selectSalonAllListPop");
			break;
		}
		
		
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
				info.setRegDate(rset.getDate("reg_date"));
				info.setCommentCount(rset.getInt("comment_count"));
				info.setLocation(rset.getString("location"));
				
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
	
	public List<Info> selectAllListSido(String board, Connection conn, int start, int end, Info sido) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(board) {
		case "info": 
			sql = prop.getProperty("selectAllListSido");
			break;
		case "hospital":
			sql = prop.getProperty("selectHospitalAllListSido");
			break;
		case "cafe": 
			sql = prop.getProperty("selectCafeAllListSido");
			break;
		case "restaurant":
			sql = prop.getProperty("selectRestaurantAllListSido");
			break;
		case "pension": 
			sql = prop.getProperty("selectPensionAllListSido");
			break;
		case "salon": 
			sql = prop.getProperty("selectSalonAllListSido");
			break;
		}
		
		
		ResultSet rset = null;
		List<Info> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sido.getLocation());
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Info info = new Info();
				info.setCode(rset.getString("code"));
				info.setMemberId(rset.getString("writer"));
				info.setBusinessName(rset.getString("business_name"));
				info.setHeadContent(rset.getString("head_content"));
				info.setViewCount(rset.getInt("view_count"));
				info.setRecommend(rset.getInt("count"));
				info.setRegDate(rset.getDate("reg_date"));
				info.setCommentCount(rset.getInt("comment_count"));
				info.setLocation(rset.getString("location"));
				
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
	
	public List<Info> selectAllListAscSido(String board, Connection conn, int start, int end, Info sido) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(board) {
		case "info": 
			sql = prop.getProperty("selectAllListAscSido");
			break;
		case "hospital":
			sql = prop.getProperty("selectHospitalAllListAscSido");
			break;
		case "cafe": 
			sql = prop.getProperty("selectCafeAllListAscSido");
			break;
		case "restaurant":
			sql = prop.getProperty("selectRestaurantAllListAscSido");
			break;
		case "pension": 
			sql = prop.getProperty("selectPensionAllListAscSido");
			break;
		case "salon": 
			sql = prop.getProperty("selectSalonAllListAscSido");
			break;
		}
		
		
		ResultSet rset = null;
		List<Info> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sido.getLocation());
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Info info = new Info();
				info.setCode(rset.getString("code"));
				info.setMemberId(rset.getString("writer"));
				info.setBusinessName(rset.getString("business_name"));
				info.setHeadContent(rset.getString("head_content"));
				info.setViewCount(rset.getInt("view_count"));
				info.setRecommend(rset.getInt("count"));
				info.setRegDate(rset.getDate("reg_date"));
				info.setCommentCount(rset.getInt("comment_count"));
				info.setLocation(rset.getString("location"));
				
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
	
	public List<Info> selectAllListViewSido(String board, Connection conn, int start, int end, Info sido) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(board) {
		case "info": 
			sql = prop.getProperty("selectAllListViewSido");
			break;
		case "hospital":
			sql = prop.getProperty("selectHospitalAllListViewSido");
			break;
		case "cafe": 
			sql = prop.getProperty("selectCafeAllListViewSido");
			break;
		case "restaurant":
			sql = prop.getProperty("selectRestaurantAllListViewSido");
			break;
		case "pension": 
			sql = prop.getProperty("selectPensionAllListViewSido");
			break;
		case "salon": 
			sql = prop.getProperty("selectSalonAllListViewSido");
			break;
		}
		
		
		ResultSet rset = null;
		List<Info> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sido.getLocation());
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Info info = new Info();
				info.setCode(rset.getString("code"));
				info.setMemberId(rset.getString("writer"));
				info.setBusinessName(rset.getString("business_name"));
				info.setHeadContent(rset.getString("head_content"));
				info.setViewCount(rset.getInt("view_count"));
				info.setRecommend(rset.getInt("count"));
				info.setRegDate(rset.getDate("reg_date"));
				info.setCommentCount(rset.getInt("comment_count"));
				info.setLocation(rset.getString("location"));
				
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
	
	public List<Info> selectAllListPopSido(String board, Connection conn, int start, int end, Info sido) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(board) {
		case "info": 
			sql = prop.getProperty("selectAllListPopSido");
			break;
		case "hospital":
			sql = prop.getProperty("selectHospitalAllListPopSido");
			break;
		case "cafe": 
			sql = prop.getProperty("selectCafeAllListPopSido");
			break;
		case "restaurant":
			sql = prop.getProperty("selectRestaurantAllListPopSido");
			break;
		case "pension": 
			sql = prop.getProperty("selectPensionAllListPopSido");
			break;
		case "salon": 
			sql = prop.getProperty("selectSalonAllListPopSido");
			break;
		}
		
		
		ResultSet rset = null;
		List<Info> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sido.getLocation());
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Info info = new Info();
				info.setCode(rset.getString("code"));
				info.setMemberId(rset.getString("writer"));
				info.setBusinessName(rset.getString("business_name"));
				info.setHeadContent(rset.getString("head_content"));
				info.setViewCount(rset.getInt("view_count"));
				info.setRecommend(rset.getInt("count"));
				info.setRegDate(rset.getDate("reg_date"));
				info.setCommentCount(rset.getInt("comment_count"));
				info.setLocation(rset.getString("location"));
				
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

	public List<Attachment> selectAllAttach(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllAttach");
		ResultSet rset = null;
		List<Attachment> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment info = new Attachment();
				info.setNo(rset.getInt("no"));
				info.setCode(rset.getString("or_no"));
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

	public int insertInfo(Connection conn, Info info, String no) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(no) {
		case "1":
			sql = prop.getProperty("insertHospitalInfo");
			break;
		case "2": 
			sql = prop.getProperty("insertCafeInfo");
			break;
		case "3":
			sql = prop.getProperty("insertRestaurantInfo");
			break;
		case "4": 
			sql = prop.getProperty("insertPensionInfo");
			break;
		case "5": 
			sql = prop.getProperty("insertSalonInfo");
			break;
		}
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, info.getMemberId());
			pstmt.setString(2, info.getBusinessNo());
			pstmt.setString(3, info.getHeadContent());
			pstmt.setString(4, info.getBodyContents());
			pstmt.setString(5, info.getSite());
			pstmt.setString(6, info.getStartHour());
			pstmt.setString(7, info.getEndHour());
			pstmt.setString(8, info.getStartLaunch());
			pstmt.setString(9, info.getEndLaunch());
			pstmt.setString(10, info.getStartDinner());
			pstmt.setString(11, info.getEndDinner());
			pstmt.setString(12, info.getHoliday());
			pstmt.setString(13, info.getRoadGuide());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new InfoBoardException("게시물 등록 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateInfo(Connection conn, Info info) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateInfo");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, info.getHeadContent());
			pstmt.setString(2, info.getBodyContents());
			pstmt.setString(3, info.getSite());
			pstmt.setString(4, info.getStartHour());
			pstmt.setString(5, info.getEndHour());
			pstmt.setString(6, info.getStartLaunch());
			pstmt.setString(7, info.getEndLaunch());
			pstmt.setString(8, info.getStartDinner());
			pstmt.setString(9, info.getEndDinner());
			pstmt.setString(10, info.getHoliday());
			pstmt.setString(11, info.getRoadGuide());
			pstmt.setString(12, info.getCode());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new InfoBoardException("게시물 수정 실패!", e);
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
			
			pstmt.setString(1, code);			
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				info = new Info();
				info.setCode(rset.getString("code"));
				info.setMemberId(rset.getString("writer"));
				info.setBusinessNo(rset.getString("business_no"));
				info.setViewCount(rset.getInt("view_count"));
				info.setHeadContent(rset.getString("head_content"));
				info.setBodyContents(rset.getString("body_contents"));
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
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new InfoBoardException("조회수 카운트 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertHospitalService(Connection conn, Hospital hospital) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertHospitalService");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, hospital.getCode());
			pstmt.setString(2, hospital.getService());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("병원 진료과목 저장 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertCafeRestaurantService(Connection conn, CafeRestaurant cr) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCafeRestaurantService");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cr.getCode());
			pstmt.setString(2, cr.getService());
			pstmt.setString(3, cr.getPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("가격표 저장 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertPensionService(Connection conn, Pension pension) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPensionService");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pension.getCode());
			pstmt.setString(2, pension.getRoom());
			pstmt.setString(3, pension.getPrice1());
			pstmt.setString(4, pension.getPrice2());
			pstmt.setString(5, pension.getPrice3());
			pstmt.setString(6, pension.getPrice4());
			pstmt.setString(7, pension.getPrice5());
			pstmt.setString(8, pension.getPrice6());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("가격표(펜션) 저장 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertSalonService(Connection conn, Salon salon) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertSalonService");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, salon.getCode());
			pstmt.setString(2, salon.getSmallBath());
			pstmt.setString(3, salon.getMiddleBath());
			pstmt.setString(4, salon.getSpecialBath());
			pstmt.setString(5, salon.getSmallBathAnd());
			pstmt.setString(6, salon.getMiddleBathAnd());
			pstmt.setString(7, salon.getSpecialBathAnd());
			pstmt.setString(8, salon.getSmallMachine());
			pstmt.setString(9, salon.getMiddleMachine());
			pstmt.setString(10, salon.getSpecialMachine());
			pstmt.setString(11, salon.getSmallSpotting());
			pstmt.setString(12, salon.getMiddleSpotting());
			pstmt.setString(13, salon.getSpecialSpotting());
			pstmt.setString(14, salon.getSmallScissors());
			pstmt.setString(15, salon.getMiddleScissors());
			pstmt.setString(16, salon.getSpecialScissors());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("가격표(미용실) 저장 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Hospital> selectRightHospital(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectRightHospital");
		ResultSet rset = null;
		List<Hospital> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Hospital h = new Hospital();
				h.setCode(rset.getString("code"));
				h.setService(rset.getString("service"));
				list.add(h);
			}
		} catch (SQLException e) {
			throw new InfoBoardException("병원 진료정보 가져오기 실패!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<CafeRestaurant> selectRightCafeRestaurant(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectRightCafeRestaurant");
		ResultSet rset = null;
		List<CafeRestaurant> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				CafeRestaurant cr = new CafeRestaurant();
				cr.setCode(rset.getString("code"));
				cr.setService(rset.getString("service"));
				cr.setPrice(rset.getString("price"));
				list.add(cr);
				
			}
		} catch (SQLException e) {
			throw new InfoBoardException("가격표 가져오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Pension> selectRightPension(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectRightPension");
		ResultSet rset = null;
		List<Pension> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Pension p = new Pension();
				p.setCode(rset.getString("code"));
				p.setRoom(rset.getString("room"));
				p.setPrice1(rset.getString("price1"));
				p.setPrice2(rset.getString("price2"));
				p.setPrice3(rset.getString("price3"));
				p.setPrice4(rset.getString("price4"));
				p.setPrice5(rset.getString("price5"));
				p.setPrice6(rset.getString("price6"));
				list.add(p);
			}
		} catch (SQLException e) {
			throw new InfoBoardException("가격표(펜션) 가져오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Salon> selectRightSalon(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectRightSalon");
		ResultSet rset = null;
		List<Salon> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Salon s = new Salon();
				s.setCode(rset.getString("code"));
				s.setSmallBath(rset.getString("small_bath"));
				s.setMiddleBath(rset.getString("middle_bath"));
				s.setSpecialBath(rset.getString("special_bath"));
				s.setSmallBathAnd(rset.getString("small_bath_and"));
				s.setMiddleBathAnd(rset.getString("middle_bath_and"));
				s.setSpecialBathAnd(rset.getString("special_bath_and"));
				s.setSmallMachine(rset.getString("small_machine"));
				s.setMiddleMachine(rset.getString("middle_machine"));
				s.setSpecialMachine(rset.getString("special_machine"));
				s.setSmallSpotting(rset.getString("small_spotting"));
				s.setMiddleSpotting(rset.getString("middle_spotting"));
				s.setSpecialSpotting(rset.getString("special_spotting"));
				s.setSmallScissors(rset.getString("small_scissors"));
				s.setMiddleScissors(rset.getString("middle_scissors"));
				s.setSpecialScissors(rset.getString("special_scissors"));
				list.add(s);
				
			}
		} catch (SQLException e) {
			throw new InfoBoardException("가격표(미용실) 가져오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int checkInfoLike(Connection conn, String code, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkInfoLike");
		ResultSet rset = null;
		int no = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setString(2, memberId);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				no = rset.getInt(1);
		} catch (SQLException e) {
			throw new InfoBoardException("좋아요 확인 실패!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return no;
	}

	public int insertInfoLike(Connection conn, String codeN, String code, String memberId) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(codeN) {
		case "1": 
			sql = prop.getProperty("insertHospitalLike");
			break;
		case "2": 
			sql = prop.getProperty("insertCafeLike");
			break;
		case "3":
			sql = prop.getProperty("insertRestaurantLike");
			break;
		case "4": 
			sql = prop.getProperty("insertPensionLike");
			break;
		case "5": 
			sql = prop.getProperty("insertSalonLike");
			break;
		}
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("좋아요 등록 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateInfoLike(Connection conn, String state, String code, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateInfoLike");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, state);
			pstmt.setString(2, code);
			pstmt.setString(3, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("좋아요 업데이트 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public String checkInfoRecommend(Connection conn, String code, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkInfoRecommend");
		ResultSet rset = null;
		String recommend = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setString(2, memberId);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				recommend = rset.getString("recommend");
			
		} catch (SQLException e) {
			throw new InfoBoardException("좋아요 가져오기 실패!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return recommend;
	}

	public InfoReview selectAllReview(Connection conn, String rCode) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllReview");
		ResultSet rset = null;
		InfoReview re = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rCode);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				re = new InfoReview();
				re.setNo(rset.getInt("no"));
				re.setrCode(rset.getString("r_code"));
				re.setCode(rset.getString("code"));
				re.setMemberId(rset.getString("member_id"));
				re.setRecommend(rset.getString("recommend"));
				re.setHeadContent(rset.getString("head_content"));
				re.setContent(rset.getString("content"));
				re.setRegDate(rset.getDate("reg_date"));
				
				String attachCode = rCode;
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
					re.setAttachments(attachments);
				}
				
			}
		} catch (SQLException e) {
			throw new InfoBoardException("리뷰 가져오기 실패!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return re;
	}

	public InfoReview checkReview(Connection conn, String code, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkReview");
		ResultSet rset = null;
		InfoReview check = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setString(2, memberId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				check = new InfoReview();
				check.setrCode(rset.getString("r_code"));					
				check.setHeadContent(rset.getString("head_content"));
			}
		} catch (SQLException e) {
			throw new InfoBoardException("리뷰 체크 실패!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return check;
	}

	public int insertInfoReview(Connection conn, InfoReview ir, String codeN) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(codeN) {
		case "1": 
			sql = prop.getProperty("insertHospitalReview");
			break;
		case "2": 
			sql = prop.getProperty("insertCafeReview");
			break;
		case "3":
			sql = prop.getProperty("insertRestaurantReview");
			break;
		case "4": 
			sql = prop.getProperty("insertPensionReview");
			break;
		case "5": 
			sql = prop.getProperty("insertSalonReview");
			break;
		}
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ir.getCode());
			pstmt.setString(2, ir.getMemberId());
			pstmt.setString(3, ir.getHeadContent());
			pstmt.setString(4, ir.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("리뷰 등록 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public String getReviewCode(Connection conn, InfoReview ir) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getReviewCode");
		ResultSet rset = null;
		String code = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ir.getCode());
			pstmt.setString(2, ir.getMemberId());
			
			rset = pstmt.executeQuery();
			if(rset.next())
				code = rset.getString("r_code");
		} catch (SQLException e) {
			throw new InfoBoardException("리뷰 등록(코드가져오기) 실패!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return code;
	}

	public int insertReviewAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReviewAttachment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, attach.getCode());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new InfoBoardException("리뷰 등록(첨부파일) 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	public int updateInfoReview(Connection conn, InfoReview ir) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateInfoReview");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ir.getHeadContent());
			pstmt.setString(2, ir.getContent());
			pstmt.setString(3, ir.getrCode());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new InfoBoardException("리뷰 등록(수정) 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int checkInfoTrue(Connection conn, String code, String in) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkInfoTrue");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, in);
			pstmt.setString(2, code);
			
			result = pstmt.executeUpdate();
			System.out.println("Dao admin = " + result);
		} catch (SQLException e) {
			throw new InfoBoardException("게시물 승인 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, attach.getOriginalFilename());
			pstmt.setString(2, attach.getRenamedFilename());
			pstmt.setString(3, attach.getCode());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new InfoBoardException("첨부파일 수정 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public void deleteReview(Connection conn, String rCode) {
		PreparedStatement pstmt = null;
		String spl = prop.getProperty("deleteReview");
		int result = 0;
		
		try {
			pstmt = conn.prepareCall(spl);
			
			pstmt.setString(1, rCode);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new InfoBoardException("리뷰 삭제 실패!", e);
		}finally {
			close(pstmt);
		}
		
	}

	public void deleteAttachment(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String spl = prop.getProperty("deleteAttachment");
		int result = 0;
		
		try {
			pstmt = conn.prepareCall(spl);
			
			pstmt.setString(1, code);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new InfoBoardException("첨부파일 삭제 실패!", e);
		}finally {
			close(pstmt);
		}
		
	}

	public int deleteInfoMain(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteInfoMain");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new InfoBoardException("게시물 삭제 실패!", e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Attachment> selectAttachment(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAttachment");
		ResultSet rset = null;
		List<Attachment> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setCode(rset.getString("or_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
				list.add(attach);
			}
		} catch (SQLException e) {
			throw new InfoBoardException("첨부파일 불러오기 실패!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int updateHospitalService(Connection conn, Hospital hospital) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateHospitalService");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, hospital.getService());
			pstmt.setString(2, hospital.getCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("게시물 수정(병원) 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateCafeRestaurantService(Connection conn, CafeRestaurant cr) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateCafeRestaurantService");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cr.getService());
			pstmt.setString(2, cr.getPrice());
			pstmt.setString(3, cr.getCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("게시물 수정(카페,음식점) 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updatePensionService(Connection conn, Pension pension) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePensionService");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pension.getRoom());
			pstmt.setString(2, pension.getPrice1());
			pstmt.setString(3, pension.getPrice2());
			pstmt.setString(4, pension.getPrice3());
			pstmt.setString(5, pension.getPrice4());
			pstmt.setString(6, pension.getPrice5());
			pstmt.setString(7, pension.getPrice6());
			pstmt.setString(8, pension.getCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("게시물 수정(펜션) 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateSalonService(Connection conn, Salon salon) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateSalonService");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, salon.getSmallBath());
			pstmt.setString(2, salon.getMiddleBath());
			pstmt.setString(3, salon.getSpecialBath());
			pstmt.setString(4, salon.getSmallBathAnd());
			pstmt.setString(5, salon.getMiddleBathAnd());
			pstmt.setString(6, salon.getSpecialBathAnd());
			pstmt.setString(7, salon.getSmallMachine());
			pstmt.setString(8, salon.getMiddleMachine());
			pstmt.setString(9, salon.getSpecialMachine());
			pstmt.setString(10, salon.getSmallSpotting());
			pstmt.setString(11, salon.getMiddleSpotting());
			pstmt.setString(12, salon.getSpecialSpotting());
			pstmt.setString(13, salon.getSmallScissors());
			pstmt.setString(14, salon.getMiddleScissors());
			pstmt.setString(15, salon.getSpecialScissors());
			pstmt.setString(16, salon.getCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("게시물 수정(미용실) 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public void deleteService(Connection conn, String code, String codeN) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(codeN) {
		case "1": 
			sql = prop.getProperty("deleteHospitalService");
			break;
		case "2": 
			sql = prop.getProperty("deleteCafeRestaurantService");
			break;
		case "3": 
			sql = prop.getProperty("deletePensionService");
			break;
		case "4": 
			sql = prop.getProperty("deleteSalonService");
			break;
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new InfoBoardException("서비스 이전 정보 삭제 실패!", e);
		} finally {
			close(pstmt);
		}
		
	}

	public List<InfoReview> selectAllRealReviewCode(Connection conn, String code) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllRealReviewCode");
		ResultSet rset = null;
		List<InfoReview> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				InfoReview ir = new InfoReview();
				ir.setrCode(rset.getString("r_code"));
				list.add(ir);				
			}
		} catch (SQLException e) {
			throw new InfoBoardException("리뷰코드 배열 가져오기 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Info selectMyBusiness(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectMyBusiness");
		ResultSet rset = null;
		Info info = new Info();
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				info = new Info();
				info.setCode(rset.getString("code"));
				info.setMemberId(rset.getString("writer"));
				info.setRegCheck(rset.getString("reg_check"));
				info.setHeadContent(rset.getString("head_content"));
				info.setRegDate(rset.getDate("reg_date"));
				info.setDeleteCheck(rset.getString("delete_check"));
				info.setBusinessName(rset.getString("business_name"));
			}
		} catch (SQLException e) {
			throw new InfoBoardException("정보 게시물 승인여부 가져오기 실패!, e");
		} finally {
			close(pstmt);
		}
		
		return info;
	}



}
