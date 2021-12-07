package com.zea.geverytime.myPage.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.commit;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.zea.geverytime.market.purchase.model.vo.PurchaseHistory;
import com.zea.geverytime.board.model.vo.Board;
import com.zea.geverytime.info.model.vo.Info;
import com.zea.geverytime.member.model.exception.MemberException;
import com.zea.geverytime.member.model.vo.Business;
import com.zea.geverytime.member.model.vo.Member;
import com.zea.geverytime.myPage.model.dao.MyPageDao;
import com.zea.geverytime.myPage.model.vo.Purchase;

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

		public int deleteMember(String memberId) {
			Connection conn = null;
			int result = 0;
			try {
				conn = getConnection();
				result = myPageDao.deleteMember(conn, memberId);
				if(result == 0)
					throw new MemberException("해당하는 회원이 없습니다.");
				commit(conn);
			} catch(Exception e) {
				rollback(conn);
				throw e;
			} finally {
				close(conn);
			}
			return result;
		}

		public int updateBusiness(Business business) {
			Connection conn = null;
			int result = 0;
			try {
				conn = getConnection();
				result = myPageDao.updateBusiness(conn, business);
				commit(conn);
			} catch(Exception e) {
				rollback(conn);
				throw e;
			} finally {
				close(conn);
			}
			
			return result;
		}

		public int deleteBusiness(String businessId) {
			Connection conn = null;
			int result = 0;
			try {
				conn = getConnection();
				result = myPageDao.deleteBusiness(conn, businessId);
				if(result == 0)
					throw new MemberException("해당하는 사업자가 없습니다.");
				commit(conn);
			} catch(Exception e) {
				rollback(conn);
				throw e;
			} finally {
				close(conn);
			}
			return result;
		}


		public List<Purchase> getPurchase(String memberId) {
			Connection conn = null;
			List<Purchase> list = null;
			try {
				conn = getConnection();
				list = myPageDao.getPurchase(conn, memberId);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn);			
			}
			return list;
		}

		public List<Info> selectInfoList(Map<String, Object> param) {
			Connection conn = null;
			List<Info> list = null; 
			try {
				conn = getConnection();
				list = myPageDao.selectInfoList(conn, param);	
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn);	
			}
			return list;
		}

		public int myPageinfoListCount(Map<String, Object> param) {
			Connection conn = null;
			int totalCount = 0;
			try {
				conn = getConnection();
				totalCount = myPageDao.myPageinfoListCount(conn, param);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn);	
			}
			return totalCount;
		}

		

}

