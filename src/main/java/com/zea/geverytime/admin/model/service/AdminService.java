package com.zea.geverytime.admin.model.service;
import static com.zea.geverytime.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;

import com.zea.geverytime.admin.model.dao.AdminDao;
import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.customer.model.vo.ReportBoard;
import com.zea.geverytime.info.model.vo.Info;

public class AdminService {
	private AdminDao adminDao = new AdminDao();

//	public List<Info> selectInfoBoard() {
//		Connection conn = getConnection();
//		List<Info> list = adminDao.selectInfoBoard(conn);
//		close(conn);
//		return list;
//	}
	public List<Info> selectInfoBoard(Map<String, Object> param) {
		Connection conn = null;
		List<Info> list = null;
		try {
			conn = getConnection();
			list = adminDao.selectInfoBoard(conn, param);
			
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return list;
	}

	public List<ReportBoard> selectReportList(Map<String, Object> param) {
		Connection conn = null;
		List<ReportBoard> list = null;
		try {
			conn = getConnection();
			list = adminDao.selectReportList(conn, param);
			
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return list;
	}

	public int checkReport(String code, String delCode, String output) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			if(!"d".equals(delCode))
				result = adminDao.checkReport(conn, code, output);
			else	
				result = adminDao.deleteReport(conn, code, output);

			if(result > 0)
				commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int adminTotalCount(Map<String, Object> param) {
		Connection conn = null;
		int totalCount = 0;
		try {
			conn = getConnection();
			totalCount = adminDao.adminTotalCount(conn, param);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}		
		return totalCount;
	}

	public int reportTotalCount(Map<String, Object> param) {
		Connection conn = null;
		int totalCount = 0;
		try {
			conn = getConnection();
			totalCount = adminDao.reportTotalCount(conn, param);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}		
		return totalCount;
	}



}
