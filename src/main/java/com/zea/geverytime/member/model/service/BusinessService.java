package com.zea.geverytime.member.model.service;
import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.commit;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.rollback;

import java.sql.Connection;

import com.zea.geverytime.member.model.dao.BusinessDao;
import com.zea.geverytime.member.model.vo.Business;
import com.zea.geverytime.member.model.vo.Member;


public class BusinessService {
public static final String BUSINESSTYPE = "B";

	private BusinessDao businessDao = new BusinessDao();
	
	public int insertBmember(Business business) {
		Connection conn = null;
		int bresult = 0;
		try {
			// 1.Connection객체 생성
			conn = getConnection();
			
			// 2.Dao요청
			bresult = businessDao.insertBmember(conn, business);
			// 3.트랜잭션처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			// 4.Connection 자원반납
			close(conn);
		}
		return bresult;
	}

	public Business selectOneMember(String businessId) {
		// 1. Connection객체 생성
		Connection conn = getConnection();
		
		// 2. Dao에 쿼리실행 요청
	Business business = businessDao.selectOneMember(conn, businessId);
		
		// 3. Connection자원반납
		close(conn);
		
		return business;
	}


	

		
}
