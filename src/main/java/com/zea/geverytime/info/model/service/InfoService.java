package com.zea.geverytime.info.model.service;

import static com.zea.geverytime.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.info.model.dao.InfoDao;
import com.zea.geverytime.info.model.vo.CafeRestaurant;
import com.zea.geverytime.info.model.vo.Hospital;
import com.zea.geverytime.info.model.vo.Info;
import com.zea.geverytime.info.model.vo.InfoReview;
import com.zea.geverytime.info.model.vo.Pension;
import com.zea.geverytime.info.model.vo.Salon;

public class InfoService {
	
	private InfoDao infoDao = new InfoDao();

	public List<Info> selectPopList(String board) {
		Connection conn = null;
		List<Info> popList = null;
		List<Attachment> attach = null;
		try {
			conn = getConnection();
			popList = infoDao.selectPopList(board, conn);	
			for(int i = 0; i < popList.size(); i++) {
				String code = popList.get(i).getCode();
				attach = infoDao.selectPopAttach(conn, code);
//				System.out.println("service attach : " + attach);
				popList.get(i).setAttachments(attach);
			}

		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return popList;
	}

	public List<Info> selectAllList(String board, int start, int end, String n, Info sido) {
		Connection conn = null;
		List<Info> list = null;
		List<Attachment> attach = null;
		try {
			conn = getConnection();
			if("all".equals(sido.getLocation())) {
				switch(n) {
				case "new": 
					list = infoDao.selectAllListAsc(board, conn, start, end);
					break;
				case "old": 
					list = infoDao.selectAllList(board, conn, start, end);
					break;
				case "view": 
					list = infoDao.selectAllListView(board, conn, start, end);
					break;
				case "like": 
					list = infoDao.selectAllListPop(board, conn, start, end);
					break;
				}				
			} else {
				switch(n) {
				case "new": 
					list = infoDao.selectAllListAscSido(board, conn, start, end, sido);
					break;
				case "old": 
					list = infoDao.selectAllListSido(board, conn, start, end, sido);
					break;
				case "view": 
					list = infoDao.selectAllListViewSido(board, conn, start, end, sido);
					break;
				case "like": 
					list = infoDao.selectAllListPopSido(board, conn, start, end, sido);
					break;
				}		
			}
			
			for(int i = 0; i < list.size(); i++) {
				String code = list.get(i).getCode();
				attach = infoDao.selectAllAttach(conn, code);
//				System.out.println("[infoService] AllAttach : " + attach);
//				System.out.println("[infoService] code : " + code);
				
				list.get(i).setAttachments(attach);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return list;
	}

	public int insertInfo(Info info, String no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = infoDao.insertInfo(conn, info, no);
			String code = infoDao.selectCode(conn, info);
			info.setCode(code);
			
			List<Hospital> hospitals = info.getHospitals();
			if(hospitals != null && !hospitals.isEmpty()) {
				for(Hospital hospital : hospitals) {
//					System.out.println("[infoservice] hospitalService : " + hospital.getService());
					hospital.setCode(code);
					result = infoDao.insertHospitalService(conn, hospital);
				}
			}
			
			List<CafeRestaurant> crs = info.getCafeRestaurants();
			if(crs != null && !crs.isEmpty()) {
				for(CafeRestaurant cr : crs) {
					cr.setCode(code);
					result = infoDao.insertCafeRestaurantService(conn, cr);
				}
			}
			
			List<Pension> pensions = info.getPensions();
			if(pensions != null && !pensions.isEmpty()) {
				for(Pension pension : pensions) {
					pension.setCode(code);
					result = infoDao.insertPensionService(conn, pension);
				}
			}
			
			List<Salon> salons = info.getSalons();
			if(salons != null && !salons.isEmpty()) {
				for(Salon salon : salons) {
					salon.setCode(code);
					result = infoDao.insertSalonService(conn, salon);
				}
			}
			
			List<Attachment> attachments = info.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					attach.setCode(code);
					result = infoDao.insertAttachment(conn, attach);
				}
			}
			if(result > 0)
				commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally{
			close(conn);
		}
		return result;
	}
	
	public int updateInfo(Info info, String codeN) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = infoDao.updateInfo(conn, info);
			String code = info.getCode();
			infoDao.deleteService(conn,code, codeN);
			switch(codeN) {
			case "1": 
				List<Hospital> hospitals = info.getHospitals();
				for(Hospital hospital : hospitals) {
					hospital.setCode(code);
//					System.out.println("[infoservice] hospitalService : " + hospital);
					result = infoDao.insertHospitalService(conn, hospital);
				}
				break;
			case "2": 
				List<CafeRestaurant> crs = info.getCafeRestaurants();
				for(CafeRestaurant cr : crs) {
					cr.setCode(code);
					result = infoDao.insertCafeRestaurantService(conn, cr);
				}
				break;
			case "3": 
				List<Pension> pensions = info.getPensions();
				for(Pension pension : pensions) {
					pension.setCode(code);
					result = infoDao.insertPensionService(conn, pension);
				}
				break;
			case "4": 
				List<Salon> salons = info.getSalons();
				for(Salon salon : salons) {
					salon.setCode(code);
					result = infoDao.insertSalonService(conn, salon);
				}
				break;			
			}
			
			List<Attachment> attachments = info.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					attach.setCode(code);
					result = infoDao.updateAttachment(conn, attach);
				}
			}
			if(result > 0)
				commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally{
			close(conn);
		}
		return result;
	}

	public Info selectBeforeWrite(String memberId) {
		Connection conn = null;
		Info info = null;
		try {
			conn = getConnection();
			info = infoDao.selectBeforeWrite(conn, memberId);
		} catch(Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return info;
	}

	public String checkInfoBoard(String memberId) {
		Connection conn = null;
		String check = "";
		try {
			conn = getConnection();
			check = infoDao.checkInfoBoard(conn, memberId);
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return check;
	}

	public Info selectOneView(String code, String codeN) {
		Connection conn = null;
		Info info = null;
		List<Hospital> h = null;
		List<CafeRestaurant> cr = null;
		List<Pension> p = null;
		List<Salon> s = null;
		try {
			conn = getConnection();
			info = infoDao.selectOneView(conn, code);
			if("1".equals(codeN)) {
				h = infoDao.selectRightHospital(conn, code);
				info.setHospitals(h);
			}else if("2".equals(codeN) || "3".equals(codeN)) {
				cr = infoDao.selectRightCafeRestaurant(conn, code);
				info.setCafeRestaurants(cr);
			}else if("4".equals(codeN)) {
				p = infoDao.selectRightPension(conn, code);
				info.setPensions(p);
			}else if("5".equals(codeN)) {
				s = infoDao.selectRightSalon(conn, code);
				info.setSalons(s);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return info;
	}

	public int updateReadCount(String code) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = infoDao.updateReadCount(conn, code);
			if(result > 0)
				commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int checkInfoLike(String code, String memberId) {
		Connection conn = null;
		int no = 0;
		try {
			conn = getConnection();
			no = infoDao.checkInfoLike(conn, code, memberId);
		}catch(Exception e) {
			throw e;
		} finally{
			close(conn);
		}
		return no;
	}

	public int insertInfoLike(String codeN, String code, String memberId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = infoDao.insertInfoLike(conn, codeN, code, memberId);
			if(result > 0)
				commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int updateInfoLike(String state, String code, String memberId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = infoDao.updateInfoLike(conn, state, code, memberId);
			if(result > 0)
				commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public String checkInfoRecommend(String code, String memberId) {
		Connection conn = null;
		String recommend = "";
		try {
			conn = getConnection();
			recommend = infoDao.checkInfoRecommend(conn, code, memberId);
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return recommend;
	}

	public List<InfoReview> selectAllReview(String code) {
		Connection conn = null;
		List<InfoReview> ir = new ArrayList<>();
		try {
			conn = getConnection();
			List<InfoReview> rcodes = infoDao.selectAllRealReviewCode(conn, code);
			if(rcodes != null && !rcodes.isEmpty()){
				for(InfoReview rcode : rcodes) {
					InfoReview review = new InfoReview();
					review = infoDao.selectAllReview(conn, rcode.getrCode());
					ir.add(review);
				}							
			}			
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return ir;
	}

	public InfoReview checkReview(String code, String memberId) {
		Connection conn = null;
		InfoReview check = null;
		try {
			conn = getConnection();
			check = infoDao.checkReview(conn, code, memberId);
//			System.out.println("[infoService] + check : " + check);
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return check;
	}

	public int insertInfoReview(InfoReview ir, String codeN) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = infoDao.insertInfoReview(conn, ir, codeN);
			String rCode = infoDao.getReviewCode(conn, ir);
			ir.setrCode(rCode);
			
			List<Attachment> attachments = ir.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					attach.setCode(rCode);
					result = infoDao.insertReviewAttachment(conn, attach);
				}
			}
			if(result > 0)
				commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int updateInfoReview(InfoReview ir) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = infoDao.updateInfoReview(conn, ir);
			List<Attachment> attachments = ir.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					attach.setCode(ir.getrCode());
					result = infoDao.insertReviewAttachment(conn, attach);
				}
			}
			if(result > 0)
				commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int checkInfoTrue(String code, String output) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			if(!"A".equals(output))
				result = infoDao.checkInfoTrue(conn, code, output);
			else
				result = infoDao.deleteInfoMain(conn, code);
//			System.out.println("[InfoServiceCheckInfoTrue] Admin서비스 : " + result);
			if(result > 0)
				commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int updateAttachment(List<Attachment> attachments) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					result = infoDao.updateAttachment(conn, attach);
				}				
			}
			if(result > 0)
				commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public void deleteReview(String rCode) {
		Connection conn = null;
		try {
			conn = getConnection();
			infoDao.deleteReview(conn, rCode);			
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
	}

	public void deleteAttachment(String code) {
		Connection conn = null;
		try {
			conn = getConnection();
			infoDao.deleteAttachment(conn, code);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
	}

	public int deleteInfoMain(String code) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = infoDao.deleteInfoMain(conn, code);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public List<Attachment> selectAttachment(String code) {
		Connection conn = null;
		List<Attachment> list = null;
		try {
			conn = getConnection();
			list = infoDao.selectAttachment(conn, code);
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return list;
	}

	public Info selectMyBusiness(String memberId) {
		Connection conn = null;
		Info info = null;
		try {
			conn = getConnection();
			info = infoDao.selectMyBusiness(conn, memberId);
		} catch(Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return info;
	}



}
