package com.zea.geverytime.market.usedgoods.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.commit;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

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
			
			// 고유코드 가져오기
			String orCode = board.getOrCode();
			
			// 첨부파일 등록하기
			List<Attachment> attachment = ugBoard.getAttachment();
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
}
