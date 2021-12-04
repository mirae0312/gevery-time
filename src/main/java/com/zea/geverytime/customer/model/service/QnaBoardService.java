package com.zea.geverytime.customer.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.commit;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.zea.geverytime.board.model.vo.Board;
import com.zea.geverytime.customer.model.dao.QnaBoardDao;
import com.zea.geverytime.customer.model.vo.FaqBoard;
import com.zea.geverytime.customer.model.vo.QnaBoard;
import com.zea.geverytime.customer.model.vo.ReportBoard;

 

public class QnaBoardService {
	
	private QnaBoardDao qnaBoardDao = new QnaBoardDao();
	
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
	//faq게시판 전체 조회
	public List<FaqBoard> selectAllFaqBoard(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<FaqBoard> list = qnaBoardDao.selectAllFaqBoard(conn, param);
		close(conn);
		return list;
	}

	
	//faq페이징하기 위해 총 게시물 수 
	public int selectTotalFaqBoardCount() {
		Connection conn = getConnection();
		int totalCount = qnaBoardDao.selectTotalFaqBoardCount(conn);
		close(conn);
		return totalCount;
	}

	//faq 상세보기
	public FaqBoard selectOneFaqBoard(int no) {
		 Connection conn = getConnection();
		 FaqBoard faqBoard = qnaBoardDao.selectOneFaqBoard(conn, no);
		 close(conn);
		 return faqBoard;
		 
	}

	//serch faq
	public List<FaqBoard> searchFaq(Map<String, Object> paramS) {
			 Connection conn = getConnection();
			 List<FaqBoard> listS = qnaBoardDao.searchFaq(conn, paramS);
			 close(conn);
			 return listS;
			
		 
		}

	//faq 삭제 
	public int deleteFaqBoard(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = qnaBoardDao.deleteFaqBoard(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	//faq 등록
	public int insertFaqBoard(FaqBoard faqBoard) {
		Connection conn = null;
		int result = 0;
		try {
		conn = getConnection();
		result = qnaBoardDao.insertFaqBoard(conn, faqBoard);
		commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}
	
	 
	//[답변완료]표시 
	public int addQnaBoardState(int replyRef) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = qnaBoardDao.addQnaBoardState(conn, replyRef);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	
	//신고게시판 전체조회
	public List<ReportBoard> selectAllReportBoard() {
		Connection conn = getConnection();
		List<ReportBoard> list = qnaBoardDao.selectAllReportBoard(conn);
		close(conn);
		return list;
	}

	//신고게시판 상세보기
	public ReportBoard selectOneReportBoard(int no) {
		 Connection conn = getConnection();
		 ReportBoard reportBoard = qnaBoardDao.selectOneReportBoard(conn, no);
		 close(conn);
		 return reportBoard;
		 
	}

	//비밀번호 체크 
	public int passwordCheck(int no, String password) {
		Connection conn = getConnection();
		int cnt  = qnaBoardDao.passwordCheck(no,password,conn);
		close(conn);
		return cnt;
	}

	//답글상세보기
	public QnaBoard selectQnaBoardReply(int no) {
		 Connection conn = getConnection();
		 QnaBoard qnaBoard2 = qnaBoardDao.selectQnaBoardReply(conn, no);
		 close(conn);
		 return qnaBoard2;
		 
	}
 
	}
 
 