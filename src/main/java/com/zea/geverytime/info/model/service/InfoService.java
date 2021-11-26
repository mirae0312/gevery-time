package com.zea.geverytime.info.model.service;

import java.sql.Connection;
import java.util.List;

import com.zea.geverytime.info.model.dao.InfoDao;
import com.zea.geverytime.info.model.vo.Info;
import static com.zea.geverytime.common.JdbcTemplate.*;

public class InfoService {
	
	private InfoDao infoDao = new InfoDao();

	public List<Info> selectPopList() {
		Connection conn = null;
		List<Info> popList = null;
		try {
			conn = getConnection();
			popList = infoDao.selectPopList(conn);
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return popList;
	}

	public List<Info> selectAllList(int start, int end) {
		Connection conn = null;
		List<Info> list = null;
		try {
			conn = getConnection();
			list = infoDao.selectAllList(conn, start, end);
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return list;
	}

}
