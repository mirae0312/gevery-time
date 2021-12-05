package com.zea.geverytime.market.usedgoods.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.commit;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.market.usedgoods.model.dao.UsedGoodsDao;
import com.zea.geverytime.market.usedgoods.model.vo.UsedGoodsBoard;

public class UsedGoodsService {
	private UsedGoodsDao ugDao = new UsedGoodsDao();

	public int insertUgBoard(UsedGoodsBoard ugBoard, List<Attachment> attachments) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 게시물 등록하기
			result = ugDao.insertUgBoard(conn, ugBoard);
			
			// 게시물 번호 가져오기
			int boardNo = ugDao.getLastBoard(conn);
			
			// 게시물 정보 가져오기
			UsedGoodsBoard board = ugDao.getUgGoodsBoard(conn, boardNo);

			// 요청 ugBoard객체에 no 전달
			ugBoard.setNo(boardNo);
			
			// 고유코드 가져오기
			String orCode = board.getOrCode();
			
			// 첨부파일 등록하기
			List<Attachment> attachment = ugBoard.getAttachments();
			for(Attachment attach : attachment) {
				result = ugDao.insertBoardAttachments(conn, attach, orCode);					
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public UsedGoodsBoard getUgGoodsBoard(int boardNo) {
		Connection conn = getConnection();
		UsedGoodsBoard board = ugDao.getUgGoodsBoard(conn, boardNo);
		close(conn);
		return board;
	}

	public List<UsedGoodsBoard> getProductSaleBoardAll(int startNum, int endNum, String keyword, String type) {
		Connection conn = getConnection();
		List<UsedGoodsBoard> list = ugDao.getProductSaleBoardAll(conn, startNum, endNum, keyword, type);
		close(conn);
		return list;
	}

	public List<Attachment> getUgBoardAttachment(String orCode) {
		Connection conn = getConnection();
		List<Attachment> list = ugDao.getUgBoardAttachment(conn, orCode);
		close(conn);
		return list;
	}

	public int getUgBoardCount() {
		Connection conn = getConnection();
		int count = ugDao.getUgBoardCount(conn);
		close(conn);
		return count;
	}

	public int insertUgBoardState(int boardNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = ugDao.insertUgBoardState(conn, boardNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public String getUgGoodsBoardState(int no) {
		Connection conn = getConnection();
		String state = ugDao.getUgGoodsBoardState(conn, no);
		close(conn);
		return state;
	}

	public int deleteAttachments(String orCode) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = ugDao.deleteAttachments(conn, orCode);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateUgBoard(UsedGoodsBoard ugBoard, List<Attachment> attachments) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = ugDao.updateBoard(conn, ugBoard);
			
			int boardNo = ugBoard.getNo();
			UsedGoodsBoard board = ugDao.getUgGoodsBoard(conn, boardNo);
			String orCode = board.getOrCode();

			for(Attachment attach : attachments) {
				result = ugDao.insertBoardAttachments(conn, attach, orCode);
				System.out.println("파일등록결과 : "+result);
			}
			
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteBoard(int boardNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 삭제 전 orCode 받아오기
			UsedGoodsBoard board = ugDao.getUgGoodsBoard(conn, boardNo);
			String orCode = board.getOrCode();
			
			// DB에서 board 삭제
			result = ugDao.deleteBoard(conn, boardNo);
			
			// Attachment 삭제
			if(result > 0) {
				result = ugDao.deleteAttachments(conn, orCode);				
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public int addBoardRequest(int boardNo, String memberId, String content) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = ugDao.addBoardRequest(conn, boardNo, memberId, content);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Map<String, Object>> getUgBoardReqUsers(int boardNo) {
		Connection conn = getConnection();
		List<Map<String, Object>> reqUsers = ugDao.getUgBoardReqUsers(conn, boardNo);
		close(conn);
		return reqUsers;
	}

	public int tradeRequestAccept(String userId, int boardNo) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = ugDao.tradeRequestAccept(conn, userId, boardNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int changeUgBoardState(int boardNo, String state) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = ugDao.changeUgBoardState(conn, boardNo, state);
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
