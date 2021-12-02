package com.zea.geverytime.myPage.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.commit;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.rollback;

import java.sql.Connection;

import com.zea.geverytime.member.model.vo.Member;
import com.zea.geverytime.myPage.model.dao.MyPageDao;

public class MyPageService {
	
	private MyPageDao myPageDao = new MyPageDao();

	// mypage 비밀번호 수정
		public int updatePassword(Member member) {
			Connection conn = null;
			int result = 0;
			try {
				conn = getConnection();
				result = myPageDao.updatePassword(conn, member);
				commit(conn);
			} catch(Exception e) {
				rollback(conn);
				throw e;
			} finally {
				close(conn);
			}
			
			return result;
		}

		public int updateMember(Member member) {
			Connection conn = null;
			int result = 0;
			try {
				conn = getConnection();
				result = myPageDao.updateMember(conn, member);
				commit(conn);
			} catch(Exception e) {
				rollback(conn);
				throw e;
			} finally {
				close(conn);
			}
			
			return result;
		}
}
