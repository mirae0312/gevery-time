package com.zea.geverytime.common.model.service;

import java.sql.Connection;

import com.zea.geverytime.common.model.dao.CommonDao;
import com.zea.geverytime.customer.model.vo.ReportBoard;
import static com.zea.geverytime.common.JdbcTemplate.*;

public class CommonService {
	
	private CommonDao commonDao = new CommonDao();

	public int insertReport(ReportBoard report) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = commonDao.insertReport(conn, report);
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

	public int checkReported(String user, String reportCode) {
		Connection conn = getConnection();
		int result = commonDao.checkReported(conn, user, reportCode);
		close(conn);
		
		return result;
	}

}
