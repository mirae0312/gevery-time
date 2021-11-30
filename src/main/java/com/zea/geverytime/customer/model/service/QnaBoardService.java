package com.zea.geverytime.customer.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.commit;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.rollback;
import static com.zea.geverytime.common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.zea.geverytime.customer.model.dao.QnaBoardDao;
import com.zea.geverytime.customer.model.vo.QnaBoard;

 

public class QnaBoardService {
	
	private QnaBoardDao qnaBoardDao = new QnaBoardDao();

//////게시판 목록 조회하기///////
//	public List<QnaBoard> selectAllQnaBoard(Map<String, Integer> param) {
//		Connection conn = getConnection();
//		List<QnaBoard> list = qnaBoardDao.selectAllQnaBoard(conn, param);
//		close(conn);
//		return list;
//	}
	
////게시판 목록 조회하기///////
	public List<QnaBoard> selectAllQnaBoard(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<QnaBoard> list = qnaBoardDao.selectAllQnaBoard(conn, param);
		close(conn);
		return list;
	}
	
	//페이징하기 위해 총 게시물수 구하기///
	public int selectTotalQnaBoardCount() {
		Connection conn = getConnection();
		int totalCount = qnaBoardDao.selectTotalQnaBoardCount(conn);
		close(conn);
		return totalCount;
	}

	//게시글 상세보기
	public QnaBoard selectOneQnaBoard(int no) {
		 Connection conn = getConnection();
		 QnaBoard qnaBoard = qnaBoardDao.selectOneQnaBoard(conn, no);
		 close(conn);
		 return qnaBoard;
		 
	}
	
	//게시글 등록 
	public int insertQnaBoard(QnaBoard qnaBoard) {
		Connection conn = null;
		int result = 0;
		try {
		conn = getConnection();
		result = qnaBoardDao.insertQnaBoard(conn, qnaBoard);
		commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}
	
	
	//게시글 수정
	public int updateQnaBoard(QnaBoard qnaBoard) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			//트랜잭션처리할 코드 
			//1.board update
			result = qnaBoardDao.updateQnaBoard(conn, qnaBoard); //게시판 글 수정
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	//게시물 삭제
	public int deleteQnaBoard(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = qnaBoardDao.deleteBoard(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public  int insertQnaBoardReply(QnaBoard qnaBoard) {
		Connection conn = null;
		int result = 0;
		try {
		conn = getConnection();
		result = qnaBoardDao.insertQnaBoardReply(conn, qnaBoard);
		commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}
	}
 
 