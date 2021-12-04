package com.zea.geverytime.customer.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.zea.geverytime.customer.model.exception.CustomerBoardException;
import com.zea.geverytime.customer.model.vo.FaqBoard;
import com.zea.geverytime.customer.model.vo.QnaBoard;
import com.zea.geverytime.customer.model.vo.ReportBoard;

public class QnaBoardDao {
	
	private Properties prop = new Properties();
	
	public 	QnaBoardDao() {
		File file = new File(QnaBoardDao.class.getResource("/qnaBoard-query.properties").getPath());
		try {
			prop.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
 

	//게시판 목록 조회//
		public List<QnaBoard> selectAllQnaBoard(Connection conn, Map<String, Integer> param ) {
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectAllQnaBoard");
			ResultSet rset = null;
			List<QnaBoard> list = new ArrayList<>();
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			 
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				QnaBoard qnaBoard = new QnaBoard();
				
				qnaBoard.setNo(rset.getInt("no"));
				qnaBoard.setTitle(rset.getString("title"));
				qnaBoard.setWriter(rset.getString("writer"));
				qnaBoard.setPassword(rset.getString("password"));
				qnaBoard.setContent(rset.getString("content"));
				qnaBoard.setReplyLevel(rset.getInt("reply_level"));
				qnaBoard.setReplyRef(rset.getInt("reply_ref"));
				qnaBoard.setCategory(rset.getString("category_a"));
				qnaBoard.setRegDate(rset.getDate("reg_date"));
				list.add(qnaBoard);
		}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
			 
			
			return list;
		}

	//페이징하기 위해 총 게시물수 구하기
	public int selectTotalQnaBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalQnaBoardCount");
		ResultSet rset = null;
		int totalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalCount;
	}

	//게시글 상세보기
	public  QnaBoard selectOneQnaBoard(Connection conn, int no) {
		QnaBoard qnaBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOneQnaBoard");
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rset = pstmt.executeQuery();
		
		if(rset.next()){
			qnaBoard = new QnaBoard();
			qnaBoard.setNo(rset.getInt("no"));
			qnaBoard.setTitle(rset.getString("title"));
			qnaBoard.setWriter(rset.getString("writer"));
			qnaBoard.setPassword(rset.getString("password"));
			qnaBoard.setContent(rset.getString("content"));
		 
			qnaBoard.setReplyLevel(rset.getInt("reply_level"));
			qnaBoard.setReplyRef(rset.getInt("reply_ref"));
			qnaBoard.setCategory (rset.getString("category_a"));
		 
			qnaBoard.setRegDate(rset.getDate("reg_date"));
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		close(rset);
		close(pstmt);
	}
	return qnaBoard;
 
	 
	}
	
//게시글 등록
	public int insertQnaBoard(Connection conn, QnaBoard qnaBoard) {
		 
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertQnaBoard");
		int result = 0;
		
		//insertQnaBoard = insert into qna_board(no,title,writer,content,password,category_a) values (seq_qna_board_no.nextval, ?,?,?,?,?)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaBoard.getTitle());
			pstmt.setString(2, qnaBoard.getWriter());
			pstmt.setString(3, qnaBoard.getContent());
			pstmt.setString(4, qnaBoard.getPassword());
			pstmt.setString(5, qnaBoard.getCategory());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomerBoardException("게시물 등록 오류",e);
		} finally {
			close(pstmt);
		}
		
		return result;
 
 
	}

	
	//게시글 수정
	public int updateQnaBoard(Connection conn, QnaBoard qnaBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateQnaBoard"); 
		//updateQnaBoard = update qna_board set title = ?, writer=?, content = ?,password=?,category=? where no = ?
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qnaBoard.getTitle());
			pstmt.setString(2, qnaBoard.getWriter());
			pstmt.setString(3, qnaBoard.getContent());
			pstmt.setString(4, qnaBoard.getPassword());
			pstmt.setString(5, qnaBoard.getCategory());
			pstmt.setInt(6, qnaBoard.getNo());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	 
	}

	//게시글 삭제
	public int deleteBoard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteQnaBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertQnaBoardReply(Connection conn, QnaBoard qnaBoard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertQnaBoardReply");
		int result = 0;
		
		//insertQnaBoardReply = insert into qna_board(no,title,writer,content,password,category_a,reply_level,reply_ref) values (seq_qna_board_no.nextval, ?,?,?,?,?,?,?)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaBoard.getTitle());
			pstmt.setString(2, qnaBoard.getWriter());
			pstmt.setString(3, qnaBoard.getContent());
			pstmt.setString(4, qnaBoard.getPassword());
			pstmt.setString(5, qnaBoard.getCategory());
			pstmt.setInt(6, qnaBoard.getReplyLevel());
			pstmt.setInt(7, qnaBoard.getReplyRef());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomerBoardException("게시물 등록 오류",e);
		} finally {
			close(pstmt);
		}
		
		return result;
 
 
	}

	//faq전체 조회
	public List<FaqBoard> selectAllFaqBoard(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllFaqBoard");
		ResultSet rset = null;
		List<FaqBoard> list = new ArrayList<>();
		
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, param.get("start"));
		pstmt.setInt(2, param.get("end"));
		 
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			FaqBoard faqBoard = new FaqBoard();
			
			faqBoard.setNo(rset.getInt("no"));
			faqBoard.setTitle(rset.getString("title"));
			faqBoard.setWriter(rset.getString("writer"));
			faqBoard.setContent(rset.getString("content"));
			faqBoard.setCategory(rset.getString("category_a"));
			faqBoard.setRegDate(rset.getDate("reg_date"));
			list.add(faqBoard);
	}
		}catch(SQLException e) {
			throw new CustomerBoardException("게시글 목록 조회 오류!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		 
		
		return list;
	}


	//faq 페이징하기 위해 총 게시물수 구하기
	public int selectTotalFaqBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalFaqBoardCount");
		ResultSet rset = null;
		int totalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalCount;
	}

	//faq 상세보기
	public FaqBoard selectOneFaqBoard(Connection conn, int no) {
		FaqBoard faqBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOneFaqBoard");
		//selectOneFaqBoard = select * from faq_board where no =?
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rset = pstmt.executeQuery();
		
		if(rset.next()){
			faqBoard = new FaqBoard();
			faqBoard.setNo(rset.getInt("no"));
			faqBoard.setTitle(rset.getString("title"));
			faqBoard.setWriter(rset.getString("writer"));
			faqBoard.setContent(rset.getString("content"));
			faqBoard.setCategory(rset.getString("category_a"));
			faqBoard.setRegDate(rset.getDate("reg_date"));
			 
		 
			
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		close(rset);
		close(pstmt);
	}
	return faqBoard;
 
	 
	}

	//search faq  //   
	public List<FaqBoard> searchFaq(Connection conn, Map<String, Object> paramS) {
		 PreparedStatement pstmt = null;
		 String sql = prop.getProperty("searchFaq");  
		 ResultSet rset = null;
		 List<FaqBoard> listS = new ArrayList<>();
 
		 String searchKeyword = (String) paramS.get("searchKeyword");
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, searchKeyword);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FaqBoard faqBoard = new FaqBoard();
				faqBoard.setNo(rset.getInt("no"));
				faqBoard.setTitle(rset.getString("title"));
				faqBoard.setWriter(rset.getString("writer"));
				faqBoard.setContent(rset.getString("content"));
				faqBoard.setCategory(rset.getString("category_a"));
				faqBoard.setRegDate(rset.getDate("reg_date"));
				 
				
				listS.add(faqBoard);
			}
			
			 
			 System.out.println("sql@dao = " + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		 
		 return listS;
	  
	}

	//faq 삭제
	public int deleteFaqBoard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteFaqBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//faq등록
	public int insertFaqBoard(Connection conn, FaqBoard faqBoard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertFaqBoard");
		int result = 0;
		
		//insertFaqBoard = insert into faq_board(no,title,content,category_a) values (seq_qna_board_no.nextval, ?,?,? )
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, faqBoard.getTitle());
			pstmt.setString(2, faqBoard.getContent());
			pstmt.setString(3, faqBoard.getCategory());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomerBoardException("게시물 등록 오류",e);
		} finally {
			close(pstmt);
		}
		
		return result;
 
 
	}

	public int addQnaBoardState(Connection conn, int replyRef) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("addQnaBoardState");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyRef);
			pstmt.setInt(2, replyRef);
			
			result = pstmt.executeUpdate();
			System.out.println("qnaDAo@ result :"+result);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	//신고게시판 조회
	public List<ReportBoard> selectAllReportBoard(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllReportBoard");
		ResultSet rset = null;
		List<ReportBoard> list = new ArrayList<>();
		
	try {
		pstmt = conn.prepareStatement(sql);
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			ReportBoard reportBoard = new ReportBoard();
			reportBoard.setReportNo(rset.getInt("report_no"));
			reportBoard.setTitle(rset.getString("title"));
			reportBoard.setContent(rset.getString("content"));
			reportBoard.setReportCode(rset.getString("report_code"));
			reportBoard.setReportCheck(rset.getString("report_check"));
			reportBoard.setMemberId(rset.getString("member_id"));
			reportBoard.setRegDate(rset.getDate("reg_date"));
			
			list.add(reportBoard);
	}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		 
		
		return list;
	}

	//신고게시판 상세보기
	public ReportBoard selectOneReportBoard(Connection conn, int no) {
		ReportBoard reportBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOneReportBoard");
		//selectOneReportBoard = select * from client_report where report_no =?
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rset = pstmt.executeQuery();
		
		if(rset.next()){
			reportBoard = new ReportBoard();
			reportBoard.setReportNo(rset.getInt("report_no"));
			reportBoard.setTitle(rset.getString("title"));
			reportBoard.setContent(rset.getString("content"));
			reportBoard.setReportCode(rset.getString("report_code"));
			reportBoard.setReportCheck(rset.getString("report_check"));
			reportBoard.setMemberId(rset.getString("member_id"));
			reportBoard.setRegDate(rset.getDate("reg_date"));
			 
		 
			
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		close(rset);
		close(pstmt);
	}
	return reportBoard;
 
	 
	}

	//비밀번호 체크 
	public int passwordCheck(int no, String password, Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("passwordCheck");
		ResultSet rset = null;
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			pstmt.setString(2, password);
			 
			rset = pstmt.executeQuery();
			if(rset.next()) {
				cnt = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return cnt;
	}


	//답글상세
	public QnaBoard selectQnaBoardReply(Connection conn, int no) {
		QnaBoard qnaBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectQnaBoardReply");
		//selectQnaBoardReply = select * from qna_board where reply_ref =?
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rset = pstmt.executeQuery();
		
		if(rset.next()){
			qnaBoard = new QnaBoard();
			qnaBoard.setNo(rset.getInt("no"));
			qnaBoard.setTitle(rset.getString("title"));
			qnaBoard.setWriter(rset.getString("writer"));
			qnaBoard.setContent(rset.getString("content"));
			 
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		close(rset);
		close(pstmt);
	}
	return qnaBoard;
 
	 
	}

	 
}