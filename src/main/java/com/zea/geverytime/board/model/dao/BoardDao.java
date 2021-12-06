package com.zea.geverytime.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.zea.geverytime.board.model.exception.BoardException;
import com.zea.geverytime.board.model.vo.Board;
import com.zea.geverytime.board.model.vo.BoardComment;
import com.zea.geverytime.common.model.vo.Attachment;

import static com.zea.geverytime.common.JdbcTemplate.*;

public class BoardDao {
	Properties prop = new Properties();
	
	public BoardDao(){
		String directory = BoardDao.class.getResource("/board-query.properties").getPath();
		try {
			prop.load(new FileReader(directory));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Board> selectAllContentList(Map<String, Object> map, Connection conn) {
		List<Board> list = new ArrayList<>();
		String key = "selectAllContentList"+(String)map.get("boardSelect")+(String)map.get("sort");
		String sql = prop.getProperty(key);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,(int)map.get("startNum"));
			pstmt.setInt(2,(int)map.get("endNum"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("no");
				String orCode = rset.getString("or_code");
				String title = rset.getString("title");
				String writer = rset.getString("writer");
				String content = rset.getString("content");
				int readCount = rset.getInt("read_count");
				int likeCount = rset.getInt("like_count");
				Date regDate = rset.getDate("reg_date");
				Board board = new Board(no,orCode,title,writer,content,readCount,likeCount,regDate);
				board.setAttachCount(rset.getInt("attach_count"));
				board.setCommentCount(rset.getInt("comment_count"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 불러오기 오류!");
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int getTotalContentCount(Connection conn, Map<String, Object>map) {
		int count = 0;
		String sql = prop.getProperty("getTotalContentCount"+(String)map.get("boardSelect"));
		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 총 개수 가져오기 오류");
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public int enrollBoard(Connection conn, Board board) {
		int result = 0;
		String sql = prop.getProperty("enrollBoard");
		PreparedStatement pstmt = null;
		
		try {
			System.out.println(board.getOrCode());
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getOrCode());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getWriter());
			pstmt.setString(4, board.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 등록 오류!");
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastBoardNo(Connection conn) {
		int no = 0;
		String sql = prop.getProperty("selectLastBoardNo");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				no = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("마지막 게시물 불러오기 오류");
		}finally {
			close(rset);
			close(pstmt);
		}
		return no;
	}

	public int enrollBoardAttachment(Connection conn, Attachment a) {
		int result = 0;
		String sql = prop.getProperty("enrollBoardAttachment");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getCode());
			pstmt.setString(2, a.getOriginalFilename());
			pstmt.setString(3, a.getRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("첨부파일 등록 오류");
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public Board selectOneBoard(Connection conn, int no) {
		Board board = new Board();
		String sql = prop.getProperty("selectOneBoard");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			pstmt.setInt(3, no);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				board.setNo(rset.getInt("no"));
				board.setOrCode(rset.getString("or_code"));
				board.setTitle(rset.getString("title"));
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setReadCount(rset.getInt("read_count"));
				board.setLikeCount(rset.getInt("like_count"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setAttachCount(rset.getInt("attach_count"));
				board.setCommentCount(rset.getInt("comment_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 불러오기 오류");
		}finally {
			close(rset);
			close(pstmt);
		}
		return board;
	}

	public List<Attachment> selectBoardAttachments(Connection conn, String orCode) {
		List<Attachment> list = new ArrayList<>();
		String sql = prop.getProperty("selectBoardAttachments");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orCode);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment a = new Attachment();
				a.setNo(rset.getInt("no"));
				a.setCode(orCode);
				a.setOriginalFilename(rset.getString("original_filename"));
				a.setRenamedFilename(rset.getString("renamed_filename"));
				a.setRegDate(rset.getDate("reg_date"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("첨부파일 불러오기 오류");
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Attachment selectOneAttachment(Connection conn, int no) {
		String sql = prop.getProperty("selectOneAttahment");
		Attachment attach = new Attachment();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				attach.setNo(rset.getInt("no"));
				attach.setCode(rset.getString("or_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("첨부파일 불러오기 오류");
		}finally {
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public int updateBoard(Connection conn, Board board) {
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,board.getTitle());
			pstmt.setString(2,board.getContent());
			pstmt.setInt(3,board.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 수정 오류!");
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteAttachment(Connection conn, int no) {
		int result = 0;
		String sql = prop.getProperty("deleteAttachment");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("첨부파일 삭제 오류");
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBoardAttachments(Connection conn, String orCode) {
		int result = 0;
		String sql = prop.getProperty("deleteBoardAttachments");
		PreparedStatement pstmt = null;
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("첨부파일 삭제 오류!");
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, int no) {
		int result = 0;
		String sql = prop.getProperty("deleteBoard");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 삭제 오류");
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<BoardComment> getBoardCommentList(Connection conn, int no) {
		List<BoardComment> list = new ArrayList<>();
		String sql = prop.getProperty("getBoardCommentList");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BoardComment c = new BoardComment();
				//comment_ref like_count reg_date
				c.setNo(rset.getInt("no"));
				c.setOrCode(rset.getString("or_code"));
				c.setCommentLevel(rset.getInt("comment_level"));
				c.setWriter(rset.getString("writer"));
				c.setContent(rset.getString("content"));
				c.setBoardNo(rset.getInt("board_no"));
				c.setCommentRef(rset.getInt("comment_ref"));
				c.setLikeCount(rset.getInt("like_count"));
				c.setRegDate(rset.getDate("reg_date"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("댓글 불러오기 오류");
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int enrollBoardComment(Connection conn, BoardComment bc) {
		int result = 0;
		String sql = prop.getProperty("enrollBoardComment");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,bc.getOrCode()); 
			pstmt.setInt(2,bc.getCommentLevel()); // 1 or 2
			pstmt.setString(3,bc.getWriter());
			pstmt.setString(4,bc.getContent());
			pstmt.setInt(5,bc.getBoardNo());
			// 참조하는 댓글이 없는 경우 null을 넣어줘야 하는데 setInt로는 불가
			// setObject 사용
//			pstmt.setInt(5,bc.getCommentRef());
			pstmt.setObject(6, bc.getCommentRef() == 0? null : bc.getCommentRef());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("댓글 등록 오류");
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int readCountUp(Connection conn, int boardNo) {
		int result = 0;
		String sql = prop.getProperty("readCountUp");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("조회수 증가 오류");
		}finally {
			close(pstmt); 
		}
		
		return result;
	}

	public List<Board> getFreePopularList(Connection conn, int count) {
		List<Board> list = new ArrayList<>();
		String sql = prop.getProperty("getFreePopularList");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("no");
				String orCode = rset.getString("or_code");
				String title = rset.getString("title");
				String writer = rset.getString("writer");
				String content = rset.getString("content");
				int readCount = rset.getInt("read_count");
				int likeCount = rset.getInt("like_count");
				Date regDate = rset.getDate("reg_date");
				Board board = new Board(no,orCode,title,writer,content,readCount,likeCount,regDate);
				board.setAttachCount(rset.getInt("attach_count"));
				board.setCommentCount(rset.getInt("comment_count"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 불러오기 오류!");
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	public List<Board> getReviewPopularList(Connection conn, int count) {
		List<Board> list = new ArrayList<>();
		String sql = prop.getProperty("getReviewPopularList");
		System.out.println("sql= "+sql);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("no");
				String orCode = rset.getString("or_code");
				String title = rset.getString("title");
				String writer = rset.getString("writer");
				String content = rset.getString("content");
				int readCount = rset.getInt("read_count");
				int likeCount = rset.getInt("like_count");
				Date regDate = rset.getDate("reg_date");
				Board board = new Board(no,orCode,title,writer,content,readCount,likeCount,regDate);
				board.setAttachCount(rset.getInt("attach_count"));
				board.setCommentCount(rset.getInt("comment_count"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 불러오기 오류!");
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public List<Board> selectOtherContentList(Connection conn, Map<String, Object> map) {
		List<Board> list = new ArrayList<>();
		String sql = prop.getProperty("selectOtherContentList");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)map.get("writer"));
			pstmt.setInt(2,(int)map.get("startNum"));
			pstmt.setInt(3,(int)map.get("endNum"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("no");
				String orCode = rset.getString("or_code");
				String title = rset.getString("title");
				String writer = rset.getString("writer");
				String content = rset.getString("content");
				int readCount = rset.getInt("read_count");
				int likeCount = rset.getInt("like_count");
				Date regDate = rset.getDate("reg_date");
				Board board = new Board(no,orCode,title,writer,content,readCount,likeCount,regDate);
				board.setAttachCount(rset.getInt("attach_count"));
				board.setCommentCount(rset.getInt("comment_count"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 불러오기 오류!");
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int getTotalOtherContentCount(Connection conn, Map<String, Object> map) {
		int count = 0;
		String sql = prop.getProperty("getTotalOtherContentCount");
		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)map.get("writer"));
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new BoardException("게시물 총 개수 가져오기 오류");
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}
	
	public int upCommentLike(Connection conn, Map<String, Object> map) {
		int result = 0;
		String data = map.get("no") + "-" + map.get("id");
		String sql = prop.getProperty("upCommentLike");
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			throw new BoardException("좋아요 오류");
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getCommentLike(Connection conn ,int no) {
		String sql = prop.getProperty("getCommentLike");
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("좋아요 불러오기 오류");
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public int checkCommentLike(Connection conn, Map<String, Object> map) {
		int check = 0;
		String data = map.get("no") + "-" + map.get("id");
		String sql = prop.getProperty("checkCommentLike");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				check = rset.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new BoardException("좋아요 여부 확인 오류");
		} finally {
			close(pstmt);
		}
		
		return check;
	}
	public int upBoardLike(Connection conn, Map<String, Object> map) {
		int result = 0;
		String data = map.get("no") + "-" + map.get("id");
		String sql = prop.getProperty("upBoardLike");
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			throw new BoardException("좋아요 오류");
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getBoardLike(Connection conn ,int no) {
		String sql = prop.getProperty("getBoardLike");
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("좋아요 불러오기 오류");
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public int checkBoardLike(Connection conn, Map<String, Object> map) {
		int check = 0;
		String data = map.get("no") + "-" + map.get("id");
		String sql = prop.getProperty("checkBoardLike");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				check = rset.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new BoardException("좋아요 여부 확인 오류");
		} finally {
			close(pstmt);
		}
		
		return check;
	}
	

}
