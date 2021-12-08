package com.zea.geverytime.member.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zea.geverytime.member.model.exception.MemberException;
import com.zea.geverytime.member.model.service.MemberService;
import com.zea.geverytime.member.model.vo.Business;

public class BusinessDao {
	private Properties prop = new Properties();// /build/classes 하위에서 파일을 조회
	
	public BusinessDao(){
	String filepath = MemberDao.class.getResource("/member-query.properties").getPath();
	System.out.println(filepath);
	try {
		prop.load(new FileReader(filepath));
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	
	public Business selectOneMember(Connection conn, String businessId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBoneMember");
		ResultSet rset = null;
		Business business = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,businessId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				System.out.println("business:" + businessId);
				business = new Business();
                business.setMemberId(rset.getString("business_id"));
                business.setPassword(rset.getString("password"));
                business.setMemberName(rset.getString("name"));
                business.setEmail(rset.getString("email"));
                business.setbName(rset.getString("business_name"));
                business.setbAddress(rset.getString("business_address"));
                business.setBusinessNo(rset.getString("business_no"));
                business.setbTel(rset.getString("business_tel"));
                business.setLocation(rset.getString("location"));
                business.setBusinessType(rset.getString("business_type"));
				}
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return business;
	}
	
	public int insertBmember(Connection conn, Business business) {
		String sql = prop.getProperty("insertBMember");
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,business.getMemberId());
			pstmt.setString(2,business.getPassword());
			pstmt.setString(3,business.getMemberName());
			pstmt.setString(4,business.getEmail());
			pstmt.setString(5,"");
			pstmt.setString(6,"");
			pstmt.setString(7,MemberService.USER_ROLE);
			pstmt.setString(8,business.getBusinessType());
			pstmt.setString(9,"");
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new MemberException("회원가입 오류!", e);
		} finally {
			close(pstmt);
		}

		sql = prop.getProperty("insertBusiness");
		result = 0;
		
		
		try {
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1,business.getMemberId());
		pstmt.setString(2,business.getPassword());
		pstmt.setString(3,business.getMemberName());
		System.out.println(business.getMemberName());
		pstmt.setString(4,business.getEmail());
		System.out.println(business.getEmail());
		pstmt.setString(5,business.getBusinessNo());
		System.out.println(business.getBusinessNo());
		pstmt.setString(6,business.getbName());
		pstmt.setString(7,business.getbAddress());
		pstmt.setString(8,business.getbTel());
		pstmt.setString(9,business.getLocation());
		pstmt.setString(10,business.getBusinessType());
		
		result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			throw new MemberException("회원가입 오류!", e);
		} finally {
			close(pstmt);
		}
	
		return result;
	}
}
