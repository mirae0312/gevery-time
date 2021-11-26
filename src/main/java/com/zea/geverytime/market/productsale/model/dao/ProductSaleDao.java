package com.zea.geverytime.market.productsale.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.zea.geverytime.market.productsale.model.vo.Product;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

public class ProductSaleDao {
	
	private Properties prop = new Properties();

	public ProductSaleDao() {
		String filepath = ProductSaleDao.class.getResource("/product-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int productSaleBoardEnroll(Connection conn, ProductBoard pdtBoard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("productSaleBoardEnroll");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pdtBoard.getTitle());
			pstmt.setString(2, pdtBoard.getContent());
			pstmt.setString(3, pdtBoard.getSellerId());
			pstmt.setInt(4, pdtBoard.getProductNo());
			result = pstmt.executeUpdate();
			System.out.println("dao@result : "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Product> getSellerProduct(Connection conn, String sellerId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getSellerProduct");
		ResultSet rset = null;
		List<Product> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sellerId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product pdt = new Product();
				pdt.setPdtNo(rset.getInt("no"));
				pdt.setPdtName(rset.getString("name"));
				pdt.setPdtPrice(rset.getInt("price"));
				pdt.setPdtDiv(rset.getString("div"));
				pdt.setState(rset.getString("state"));
				System.out.println("dao : "+pdt);
				list.add(pdt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}

	public List<ProductBoard> getProductSaleBoardAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getProductSaleBoardAll");
		List<ProductBoard> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductBoard pb = new ProductBoard();
				pb.setBoardNo(rset.getInt("no"));
				pb.setTitle(rset.getString("title"));
				pb.setRegDate(rset.getDate("reg_date"));
				pb.setSellerId(rset.getString("seller_id"));
				pb.setProductState(rset.getString("state"));
				list.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ProductBoard getProductSaleBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getProductSaleBoard");
		ProductBoard board = new ProductBoard();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board.setBoardNo(rset.getInt("no"));
				board.setOrCode(rset.getString("or_code"));
				board.setTitle(rset.getString("title"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setSellerId(rset.getString("seller_id"));
				board.setProductNo(rset.getInt("product_no"));
				board.setProductState(rset.getString("state"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}

}
