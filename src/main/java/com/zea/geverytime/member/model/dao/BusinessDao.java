package com.zea.geverytime.member.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public int insertBmember(Connection conn, Business business) {
		String sql = prop.getProperty("insertBMember");
		PreparedStatement pstmt = null;
		int result = 0;
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,business.getId());
				pstmt.setString(2,business.getPassword());
				pstmt.setString(3,business.getName());
				pstmt.setString(4,"");
				pstmt.setString(5,"");
				pstmt.setString(6,business.getEmail());
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

		pstmt.setString(1,business.getId());
		pstmt.setString(2,business.getPassword());
		pstmt.setString(3,business.getName());
		pstmt.setString(4,business.getEmail());
		pstmt.setString(5,business.getBusinessNo());
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
