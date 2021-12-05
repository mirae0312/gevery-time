package com.zea.geverytime.market.productsale.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.zea.geverytime.common.model.vo.Attachment;
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
	
	public int getLastBoard(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getLastBoard");
		int boardNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				boardNo = rset.getInt("currval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return boardNo;
	}

	public List<Product> getSellerProduct(Connection conn, String sellerId, String state) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getSellerProduct");
		ResultSet rset = null;
		List<Product> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sellerId);
			pstmt.setString(2, state);
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

	public List<ProductBoard> getProductSaleBoardAll(Connection conn, int startNum, int endNum, String div, String state) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getProductSaleBoardAll");
		List<ProductBoard> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, div);
			pstmt.setString(2, state);
			pstmt.setInt(3, startNum);
			pstmt.setInt(4, endNum);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// productBoard객체에 담기
				ProductBoard pb = new ProductBoard();
				pb.setBoardNo(rset.getInt("no"));
				pb.setTitle(rset.getString("title"));
				pb.setRegDate(rset.getDate("reg_date"));
				pb.setSellerId(rset.getString("seller_id"));
				pb.setOrCode(rset.getString("or_code"));
				// product 객체 생성
				Product pdt = new Product();
				pdt.setPdtNo(rset.getInt("product_no"));
				pdt.setPdtName(rset.getString("name"));
				pdt.setPdtPrice(rset.getInt("price"));
				pdt.setPdtDiv(rset.getString("div"));
				pdt.setSellerId(rset.getString("seller_id"));
				pdt.setState(rset.getString("state"));
				// product를 productBoard객체에 담기
				pb.setProduct(pdt);
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

	// 게시글 상세보기(view)
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
				// product 객체 생성
				Product pdt = new Product();
				pdt.setPdtNo(rset.getInt("product_no"));
				pdt.setPdtName(rset.getString("name"));
				pdt.setPdtPrice(rset.getInt("price"));
				pdt.setPdtDiv(rset.getString("div"));
				pdt.setSellerId(rset.getString("pdtSellerId"));
				pdt.setState(rset.getString("state"));
				// product를 productBoard객체에 담기
				board.setProduct(pdt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}

	public int productEnroll(Connection conn, Product pdt) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("productEnroll");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pdt.getPdtName());
			pstmt.setInt(2, pdt.getPdtPrice());
			pstmt.setString(3, pdt.getPdtDiv());
			pstmt.setString(4, pdt.getSellerId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int productSaleBoardQuestionEnroll(Connection conn, Map<String, Object> map) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("productSaleBoardQuestionEnroll");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)map.get("title"));
			pstmt.setString(2, (String)map.get("qcontent"));
			pstmt.setInt(3, (int)map.get("boardNo"));
			pstmt.setString(4, (String)map.get("writer"));
			System.out.println("pdtDao@map : "+ map);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int productSaleBoardAnswerEnroll(Connection conn, Map<String, Object> map) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("productSaleBoardAnswerEnroll");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)map.get("acontent"));
			pstmt.setInt(2, (int)map.get("commentNo"));
			pstmt.setInt(3, (int)map.get("boardNo"));
			pstmt.setString(4, (String)map.get("awriter"));
			System.out.println("pdtDao@AnswerMap : "+ map);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Map<String, Object>> getProductSaleBoardQuestion(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getProductSaleBoardQuestion");
		ResultSet rset = null;
		List<Map<String, Object>> questions = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Map<String, Object> question = new HashMap<>();
				question.put("no", rset.getInt("no"));
				question.put("title", rset.getString("title"));
				question.put("content", rset.getString("content"));
				question.put("writer", rset.getString("writer"));
				question.put("qaLevel", rset.getInt("qlevel"));
				question.put("refNo", rset.getInt("ref_no"));
				question.put("orCode", rset.getString("or_code"));
				questions.add(question);
				System.out.println("dao@question : "+question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return questions;
	}

	public int productSaleBoardQaDelete(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("productSaleBoardQaDelete");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<ProductBoard> getSelectedDivBoardList(Connection conn, String div) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getSelectedDivBoardList");
		ResultSet rset = null;
		List<ProductBoard> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, div);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// productBoard객체에 담기
				ProductBoard pb = new ProductBoard();
				pb.setBoardNo(rset.getInt("no"));
				pb.setTitle(rset.getString("title"));
				pb.setRegDate(rset.getDate("reg_date"));
				pb.setSellerId(rset.getString("pdtSellerId"));
				// product 객체 생성
				Product pdt = new Product();
				pdt.setPdtNo(rset.getInt("pdtNo"));
				pdt.setPdtName(rset.getString("name"));
				pdt.setPdtPrice(rset.getInt("price"));
				pdt.setPdtDiv(rset.getString("div"));
				pdt.setSellerId(rset.getString("pdtSellerId"));
				pdt.setState(rset.getString("state"));
				// product를 productBoard객체에 담기
				pb.setProduct(pdt);
				list.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int productOptionChange(Connection conn, String colname, String val, int pdtNo) {
		PreparedStatement pstmt = null;
		String sql = "";
		switch(colname) {
		case "div" :
			sql = prop.getProperty("productDivOptionChange");
			break;
		case "state" : 
			sql = prop.getProperty("productStateOptionChange"); 
			break;
		}
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, val);				
			pstmt.setInt(2, pdtNo);
			
			result = pstmt.executeUpdate();
			System.out.println("pdtDao@optionChange : "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getProductSaleBoardCount(Connection conn, String div, String state) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getProductSaleBoardCount");
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+div+"%");
			pstmt.setString(2, state);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt(1);
				System.out.println("pdtDao@count: "+ count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return count;
	}

	public int productSaleBoardAttachmentEnroll(Connection conn, Attachment attach, String orCode) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("productSaleBoardAttachmentEnroll");
		int result = 0;
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			String ofn = attach.getOriginalFilename();
			String rfn = attach.getRenamedFilename();

			pstmt.setString(1, orCode);
			pstmt.setString(2, ofn);
			pstmt.setString(3, rfn);
			
			result = pstmt.executeUpdate();
			System.out.println("pdtDao@attachmentEnrollREsult : "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String getBoardOrCode(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getBoardOrcode");
		ResultSet rset = null;
		String orCode = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				orCode = rset.getString(1);
			}
			System.out.println("pdtDao@orCode : "+orCode);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return orCode;
	}

	public List<Attachment> getProductSaleBoardAttachment(Connection conn, String orCode) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getBoardAttachment");
		ResultSet rset = null;
		List<Attachment> attachments = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orCode);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment attach = new Attachment();
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attachments.add(attach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return attachments;
	}

	public int productSaleBoardUpdate(Connection conn, ProductBoard board) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("productSaleBoardUpdate");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getOrCode());
			
			result = pstmt.executeUpdate();
			System.out.println("updateResult(DAO) : "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int productBoardDeleteAttachment(Connection conn, String orCode) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("productBoardDeleteAttachment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orCode);
			
			result = pstmt.executeUpdate();
			System.out.println("deleteAttachment : "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int productBoardDelete(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("productBoardDelete");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			System.out.println("deleteBoard : "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}




}
