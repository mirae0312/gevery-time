package com.zea.geverytime.myPage.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

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

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.info.model.exception.InfoBoardException;
import com.zea.geverytime.info.model.vo.Info;
import com.zea.geverytime.info.model.vo.InfoEntity;
import com.zea.geverytime.market.productsale.model.vo.Product;
import com.zea.geverytime.market.purchase.model.vo.PurchaseHistory;
import com.zea.geverytime.member.model.exception.MemberException;
import com.zea.geverytime.member.model.vo.Business;
import com.zea.geverytime.member.model.vo.Member;
import com.zea.geverytime.myPage.model.vo.Purchase;

public class MyPageDao {
	
	private Properties prop = new Properties();

	public MyPageDao(){
		// /build/classes 하위에서 파일을 조회
		String filepath = MyPageDao.class.getResource("/myPage-query.properties").getPath();
		System.out.println(filepath);
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int updatePassword(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePassword");
		
		try {
			// 미완성 쿼리문 객체생성
			pstmt = conn.prepareStatement(sql);
			// 쿼리문 미완성
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberId());
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("비밀번호 수정 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,member.getMemberName());
			pstmt.setString(2,member.getEmail());
			pstmt.setString(3,member.getPhone());
			pstmt.setString(4,member.getAddress());
			pstmt.setDate(5,member.getBirthday());
			pstmt.setString(6,member.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원 삭제 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int updateBusiness(Connection conn, Business business) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBusiness");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,business.getEmail());
			pstmt.setString(2,business.getbName());
			pstmt.setString(3,business.getbAddress());
			pstmt.setString(4,business.getLocation());
			pstmt.setString(5,business.getbTel());
			pstmt.setString(6,business.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteBusiness(Connection conn, String businessId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBusiness");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, businessId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("사업자 삭제 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	public List<Purchase> getPurchase(Connection conn, String memberId) {
		PurchaseHistory ph = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getPurchase");
		ArrayList<Purchase> list = new ArrayList<Purchase>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				Purchase pd = new Purchase();
				pd.setUid(rset.getString(1));
				pd.setMuid(rset.getString(2));
				pd.setName(rset.getString(3));
				pd.setPrice(rset.getInt(4));
				pd.setProductCount(rset.getInt(5));
				pd.setRegDate(rset.getDate(6));
					
				list.add(pd);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}
