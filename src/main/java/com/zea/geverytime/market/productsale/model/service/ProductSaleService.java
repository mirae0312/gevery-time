package com.zea.geverytime.market.productsale.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.commit;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.zea.geverytime.market.productsale.model.dao.ProductSaleDao;
import com.zea.geverytime.market.productsale.model.vo.Product;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

public class ProductSaleService {
	private ProductSaleDao pdtDao = new ProductSaleDao();

	public int productSaleBoardEnroll(ProductBoard pdtBoard) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = pdtDao.productSaleBoardEnroll(conn, pdtBoard);
			
			// 게시글 등록 후 boardNo set
			int boardNo = pdtDao.getLastBoard(conn);
			pdtBoard.setBoardNo(boardNo);
			
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return result;
	}

	public List<Product> getSellerProduct(String sellerId) {
		Connection conn = getConnection();
		List<Product> list = pdtDao.getSellerProduct(conn, sellerId);
		close(conn);
		
		return list;
	}

	public List<ProductBoard> getProductSaleBoardAll() {
		Connection conn = getConnection();
		List<ProductBoard> list = pdtDao.getProductSaleBoardAll(conn);
		close(conn);
		return list;
	}

	public ProductBoard getProductSaleBoard(int no) {
		Connection conn = getConnection();
		ProductBoard board = pdtDao.getProductSaleBoard(conn, no);
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



	
	
}
