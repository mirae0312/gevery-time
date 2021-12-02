package com.zea.geverytime.member.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.zea.geverytime.member.model.exception.MemberException;
import com.zea.geverytime.member.model.vo.Business;
import com.zea.geverytime.member.model.vo.Member;

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
	public int insertBmember(Connection conn, Business business,Member member) {
		String sql = prop.getProperty("insertMember");
		PreparedStatement bpstmt = null;
		int bresult = 0;
		try {
				bpstmt = conn.prepareStatement(sql);
				bpstmt.setString(1,member.getMemberId());
				bpstmt.setString(2,member.getPassword());
				bpstmt.setString(3,member.getMemberName());
				bpstmt.setString(4,member.getPhone());
				bpstmt.setString(5,member.getAddress());
				bpstmt.setString(6,member.getEmail());
				bpstmt.setString(7,member.getMemberRole());
				bpstmt.setString(8,member.getMemberType());
				bpstmt.setDate(9,member.getBirthday());
			
			
					bresult = bpstmt.executeUpdate();
			
			} catch (SQLException e) {
				throw new MemberException("회원가입 오류!", e);
			} finally {
				close(bpstmt);
			}

					return bresult;
			}


	
	
	public int insertBusiness(Connection conn, Business business) {
		PreparedStatement pstmt = null;

		
		String Bsql = prop.getProperty("insertBusiness");
		int result = 0;
		
		
		try {
		pstmt = conn.prepareStatement(Bsql);
		pstmt.setString(1,business.getBusinessId());
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
