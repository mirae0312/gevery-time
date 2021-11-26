<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
    Connection conn = null;

    try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }catch(ClassNotFoundException cnfe){
        cnfe.printStackTrace();
        System.out.println("드라이버 로딩 실패");
    }
    try{
        String jdbcUrl = "jdbc:oracle:thin:@khmclass.iptime.org:1521:xe";
        String userId = "geverytime";
        String userPass = "geverytime";
        conn = DriverManager.getConnection(jdbcUrl, userId, userPass);
        out.println("접속 성공");
    }catch(SQLException e){
        e.printStackTrace();
        out.println(e);
        out.println("커넥션 설정에 실패");
    }
%>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>