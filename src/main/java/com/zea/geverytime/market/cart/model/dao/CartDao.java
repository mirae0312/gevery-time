package com.zea.geverytime.market.cart.model.dao;

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

import com.zea.geverytime.market.cart.model.vo.Cart;
import com.zea.geverytime.market.cart.model.vo.WishList;
import com.zea.geverytime.market.productsale.model.dao.ProductSaleDao;
import com.zea.geverytime.market.productsale.model.vo.Product;
import com.zea.geverytime.market.productsale.model.vo.ProductBoard;

public class CartDao {
	
	Properties prop = new Properties();
	
	public CartDao() {
		String filepath = ProductSaleDao.class.getResource("/cart-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int insertIntoCart(Connection conn, Cart cart) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertIntoCart");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cart.getMemberId());
			pstmt.setInt(2, cart.getProductboardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int checkDupPdt(Connection conn, Cart cart) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkDupPdt");
		ResultSet rset = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cart.getMemberId());
			pstmt.setInt(2, cart.getProductboardNo());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Cart> getCartList(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getCartList");
		ResultSet rset = null;
		List<Cart> cartList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Cart cart = new Cart();
				cart.setMemberId(rset.getString("member_id"));
				cart.setProductboardNo(rset.getInt("no"));
				
				ProductBoard board = new ProductBoard();
				board.setTitle(rset.getString("title"));
				board.setSellerId(rset.getString("seller_id"));
				board.setOrCode(rset.getString("or_code"));
				
				Product pdt = new Product();
				pdt.setPdtName(rset.getString("name"));
				pdt.setPdtPrice(rset.getInt("price"));
				pdt.setPdtNo(rset.getInt("product_no"));
				pdt.setState(rset.getString("state"));
				
				board.setProduct(pdt);
				cart.setPdtBoard(board);
				
				cartList.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return cartList;
	}

	public int deleteCart(Connection conn, Integer num, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCart");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int addWishList(Connection conn, int boardNo, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("addWishList");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int wishListCheck(Connection conn, String memberId, int boardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkWishList");
		ResultSet rset = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(!rset.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<WishList> getWishList(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getWishList");
		ResultSet rset = null;
		List<WishList> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				WishList wish = new WishList();
				wish.setBoardNo(rset.getInt("board_no"));
				wish.setMemberId(rset.getString("member_id"));
				list.add(wish);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return list;
	}

	public int deleteWishList(Connection conn, String memberId, int boardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteWishList");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
