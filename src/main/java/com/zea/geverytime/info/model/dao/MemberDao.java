package com.zea.geverytime.info.model.dao;

import static com.zea.geverytime.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.zea.geverytime.info.model.vo.Member;

public class MemberDao {
private Properties prop = new Properties();
	
	public MemberDao(){
		// /build/classes 하위에서 파일을 조회
		String filepath = MemberDao.class.getResource("/member-query.properties").getPath();
		System.out.println(filepath);
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		int result = 0;
		
		try {
		//1.PreparedStatement객체 준비- sql값대입
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,member.getMemberId());
		pstmt.setString(2,member.getPassword());
		pstmt.setString(3,member.getMemberName());
		pstmt.setDate(4,member.getBirthday());
		pstmt.setString(5,member.getEmail());
		pstmt.setString(6,member.getPhone());
		pstmt.setString(7,member.getAddress());
		pstmt.setString(8,member.getMemberRole());
		pstmt.setString(9,member.getMemberType());


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
