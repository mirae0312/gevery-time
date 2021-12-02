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
				h.setNo(rset.getInt("no"));
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
				cr.setNo(rset.getInt("no"));
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
				p.setNo(rset.getInt("no"));
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
				s.setNo(rset.getInt("no"));
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

	public int updateInfoLike(Connection conn, String code, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateInfoLike");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new InfoBoardException("좋아요 업데이트 실패!", e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
