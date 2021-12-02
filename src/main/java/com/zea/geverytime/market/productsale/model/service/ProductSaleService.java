package com.zea.geverytime.market.productsale.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.commit;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.market.productsale.model.dao.ProductSaleDao;
import com.zea.geverytime.market.productsale.model.vo.Product;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

public class ProductSaleService {
	private ProductSaleDao pdtDao = new ProductSaleDao();

	public int productSaleBoardEnroll(ProductBoard pdtBoard, List<Attachment> attachments) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			// 게시글 등록
			result = pdtDao.productSaleBoardEnroll(conn, pdtBoard);
			
			// boardNo 조회
			int boardNo = pdtDao.getLastBoard(conn);
			// boardNo set
			pdtBoard.setBoardNo(boardNo);
			
			// er_code 조회
			String orCode = pdtDao.getBoardOrCode(conn, boardNo);
			System.out.println("service@orCode : "+orCode);
			
			// Attachment 등록
			for(Attachment attach : attachments) {
				result = pdtDao.productSaleBoardAttachmentEnroll(conn, attach, orCode);
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

	public int attachmentEnroll(List<Attachment> attachments, int boardNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			// er_code 조회
			String orCode = pdtDao.getBoardOrCode(conn, boardNo);
			System.out.println("service@orCode : "+orCode);
			
			// Attachment 등록
			for(Attachment attach : attachments) {
				pdtDao.productSaleBoardAttachmentEnroll(conn, attach, orCode);
			}
			
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	public List<Product> getSellerProduct(String sellerId, String state) {
		Connection conn = getConnection();
		List<Product> list = pdtDao.getSellerProduct(conn, sellerId, state);
		close(conn);
		
		return list;
	}

	public List<ProductBoard> getProductSaleBoardAll(int startNum, int endNum, String div, String state) {
		Connection conn = getConnection();
		List<ProductBoard> list = pdtDao.getProductSaleBoardAll(conn, startNum, endNum, div, state);
		close(conn);
		return list;
	}
	
	public int getProductSaleBoardCount(String div, String state) {
		Connection conn = getConnection();
		int count = pdtDao.getProductSaleBoardCount(conn, div, state);
		close(conn);
		return count;
	}
	
	public List<Attachment> getproductSaleBoardAttachment(String orCode) {
		Connection conn = getConnection();
		List<Attachment> attachments = pdtDao.getProductSaleBoardAttachment(conn, orCode);
		close(conn);
		return attachments;
	}

	public ProductBoard getProductSaleBoard(int no) {
		Connection conn = getConnection();
		ProductBoard board = pdtDao.getProductSaleBoard(conn, no);
		// orCode 조회해서 첨부파일 담아오기
		String orCode = board.getOrCode();
		List<Attachment> attachments = pdtDao.getProductSaleBoardAttachment(conn, orCode);
		// 첨부파일 set
		board.setAttachments(attachments);
		
		close(conn);
		return board;
	}

	public int productEnroll(Product pdt) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = pdtDao.productEnroll(conn, pdt);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public int productSaleBoardQuestionEnroll(Map<String, Object> map) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = pdtDao.productSaleBoardQuestionEnroll(conn, map);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}
	
	public int productSaleBoardAnswerEnroll(Map<String, Object> map) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = pdtDao.productSaleBoardAnswerEnroll(conn, map);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace(); 
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Map<String, Object>> getProductSaleBoardQuestion(int no) {
		Connection conn = getConnection();
		List<Map<String, Object>> questions = pdtDao.getProductSaleBoardQuestion(conn, no);
		close(conn);
		return questions;
	}

	public int productSaleBoardQaDelete(int commentNo) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = pdtDao.productSaleBoardQaDelete(conn, commentNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public List<ProductBoard> getSelectedDivBoardList(String div) {
		Connection conn = getConnection();
		List<ProductBoard> list = pdtDao.getSelectedDivBoardList(conn, div);
		close(conn);
		return list;
	}

	public int productOptionChange(String colname, String val, int pdtNo) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = pdtDao.productOptionChange(conn, colname, val, pdtNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public int productSaleBoardUpdate(ProductBoard board) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = pdtDao.productSaleBoardUpdate(conn, board);
			
			String orCode = board.getOrCode();
			
			List<Attachment> attachments = board.getAttachments();
			for(Attachment attachment : attachments) {
				pdtDao.productSaleBoardAttachmentEnroll(conn, attachment, orCode);				
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

	public int productBoardDeleteAttachment(String orCode) {
		Connection conn = getConnection();
		int result = 0;
		try {
			 result = pdtDao.productBoardDeleteAttachment(conn, orCode);
			 commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);			
		}
		
		return result;
	}

	public int productBoardDelete(int boardNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = pdtDao.productBoardDelete(conn, boardNo);
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
