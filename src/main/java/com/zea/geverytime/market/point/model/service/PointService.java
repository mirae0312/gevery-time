package com.zea.geverytime.market.point.model.service;

import static com.zea.geverytime.common.JdbcTemplate.*;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;

import java.sql.Connection;

import com.zea.geverytime.market.point.model.dao.PointDao;
import com.zea.geverytime.market.point.model.vo.PointHistory;

public class PointService {
	private PointDao pointDao = new PointDao();

	public int getPointBalance(String memberId) {
		Connection conn = getConnection();
		int point = pointDao.getPointBalance(conn, memberId);
		close(conn);
		return point;
	}

	public int withdrawPoint(String memberId, int usePoint) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = pointDao.withdrawPoint(conn, memberId, usePoint);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public int getPointNo(String memberId) {
		Connection conn = getConnection();
		int pointNo = pointDao.getPointNo(conn, memberId);
		close(conn);
		return pointNo;
	}

	public int insertPointHistory(PointHistory ht, String memberId) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = pointDao.insertPointHistory(conn, ht, memberId);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return result;
	}
}
