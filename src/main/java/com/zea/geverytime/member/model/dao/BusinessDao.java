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
	
public Business selectOneMember(Connection conn, String memberId) {
	PreparedStatement pstmt = null;
	String sql = prop.getProperty("selectOneMember");
	ResultSet rset = null;
	Business business = null;
	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, businessId);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			business = new business();
			business.setbusinessId(rset.getString("business_id"));
			business.setPassword(rset.getString("password"));
			business.setName(rset.getString("name"));
			m.setEmail(rset.getString("email"));
			member.setPhone(rset.getString("business_no"));
			member.setAddress(rset.getString("business_name"));
			member.setMemberRole(rset.getString("business_address"));
			member.setMemberRole(rset.getString("business_tel"));
			member.setMemberType(rset.getString("location"));
			member.setBirthday(rset.getDate("business_type"));
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
	
	return member;
}
	
	public int insertMember(Connection conn, Business business) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		int result = 0;
		
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,business.getbusinessId());
		pstmt.setString(2,member.getPassword());
		pstmt.setString(4,member.getMemberName());
		pstmt.setString(5,member.getEmail());
		pstmt.setString(6,member.getPhone());
		pstmt.setString(7,member.getAddress());
		pstmt.setString(8,member.getMemberRole());
		pstmt.setString(9,member.getMemberType());
		pstmt.setDate(10,member.getBirthday());


		//2. 실행 - executeUpdate
		result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			throw new MemberException("회원가입 오류!", e);
		} finally {
			// 3.자원반납
			close(pstmt);
		}
		
		
		return result;
	}
}
