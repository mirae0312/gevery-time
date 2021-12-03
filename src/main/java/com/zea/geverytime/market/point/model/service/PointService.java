package com.zea.geverytime.market.point.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;

import java.sql.Connection;

import com.zea.geverytime.market.point.model.dao.PointDao;

public class PointService {
	private PointDao pointDao = new PointDao();

	public int getPointBalance(String memberId) {
		Connection conn = getConnection();
		int point = pointDao.getPointBalance(conn, memberId);
		close(conn);
		return point;
	}
}
