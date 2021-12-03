package com.zea.geverytime.member.model.vo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.zea.geverytime.common.JdbcTemplate;

public class DbTest {

    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver 등록");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        DbTest db = new DbTest();
        int result = db.searchTable();
        System.out.println("조회결과 : "+result);

    }


    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@khmclass.iptime.org:1521:xe", "geverytime", "geverytime");
            //
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public int searchTable() {
        PreparedStatement pstmt = null;
        Connection conn = getConnection();
        ResultSet rset = null;
        int result = 0;

        try {
            pstmt = conn.prepareStatement("select * from test");
            rset = pstmt.executeQuery();

            while(rset.next()) {
                result = rset.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


}