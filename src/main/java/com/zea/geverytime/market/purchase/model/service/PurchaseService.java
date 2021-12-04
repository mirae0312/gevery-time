package com.zea.geverytime.market.purchase.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.*;

import java.sql.Connection;

import com.zea.geverytime.market.purchase.model.dao.PurchaseDao;
import com.zea.geverytime.market.purchase.model.vo.PurchaseHistory;

public class PurchaseService {
	private PurchaseDao purchaseDao = new PurchaseDao();

	public int insertPurchaseHistory(PurchaseHistory ph) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = purchaseDao.insertPurchaseHistory(conn, ph);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public int getHistoryNo(String uid) {
		Connection conn = getConnection();
		int historyNo = purchaseDao.getHistoryNo(conn, uid);
		close(conn);
		return historyNo;
	}

	public int insertPurchaseDetailHistory(int historyNo, int pdtNo, int pdtCount) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = purchaseDao.insertPurchaseDetailHistory(conn, historyNo, pdtNo, pdtCount);
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
