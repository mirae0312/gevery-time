package com.zea.geverytime.member.model.dao;
import static com.zea.geverytime.common.JdbcTemplate.close;
import static com.zea.geverytime.common.JdbcTemplate.commit;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;
import static com.zea.geverytime.common.JdbcTemplate.rollback;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zea.geverytime.member.model.exception.MemberException;
import com.zea.geverytime.member.model.vo.Member;

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
	public Member selectOneMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOneMember");
		ResultSet rset = null;
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberId);
			rset = pstmt.executeQuery();

			while(rset.next()) {

				member = new Member(rset.getString("member_id"),rset.getString("password"),
						rset.getString("member_name"),rset.getString("phone"),rset.getString("address"),rset.getString("email"),
						rset.getString("member_role"),rset.getString("member_type")
						,rset.getDate("birthday"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		int result = 0;
		
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,member.getMemberId());
		pstmt.setString(2,member.getPassword());
		pstmt.setString(3,member.getMemberName());
		pstmt.setString(4,member.getEmail());
		pstmt.setString(5,member.getPhone());
		pstmt.setString(6,member.getAddress());
		pstmt.setString(7,member.getMemberRole());
		pstmt.setString(8,member.getMemberType());
		pstmt.setDate(9,member.getBirthday());


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
	//아이디 찾기(분실시)
		public Member searchId(Connection conn, String memberName, String email) {
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectSerchId");
			ResultSet rset = null;
			Member member = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberName);
				System.out.println("memberName :" + memberName);
				pstmt.setString(2, email);
				System.out.println("email :" + email);						
				rset = pstmt.executeQuery();
				while(rset.next()){
					member= new Member(rset.getString("member_id"),
							rset.getString("password"),
							rset.getString("member_name"),
							rset.getString("phone"),
							rset.getString("address"),
							rset.getString("email"),
							rset.getString("member_role"),
							rset.getString("member_type"),
							rset.getDate("birthday"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
		
			return member;
		}
		public int insertPassword(Connection conn, Member m) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("clearPassword");
			
			try {
				// 미완성 쿼리문 객체생성
				pstmt = conn.prepareStatement(sql);
				// 쿼리문 미완성
				pstmt.setString(1, m.getPassword());
				pstmt.setString(2, m.getMemberId());
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
			



}
