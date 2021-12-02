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
	
	public int insertBmember(Business business,Member member) {
		Connection conn = null;
		int bresult = 0;
		try {
			// 1.Connection객체 생성
			conn = getConnection();
			
			// 2.Dao요청
			bresult = businessDao.insertBmember(conn, business, member);
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



	public int insertBusiness(Business business) {
		Connection conn = null;
		int result = 0;
		try {
			// 1.Connection객체 생성
			conn = getConnection();
			
			// 2.Dao요청
			result = businessDao.insertBusiness(conn, business);
			// 3.트랜잭션처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			// 4.Connection 자원반납
			close(conn);
		}
		return result;
	}

	

		
}
