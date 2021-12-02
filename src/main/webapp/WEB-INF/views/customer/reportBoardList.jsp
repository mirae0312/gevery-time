<%@page import = "com.zea.geverytime.customer.model.vo.ReportBoard" %>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/qnaList.css" /> 
 <section id="qnaBoard-container">
 <div id="total">
        <h3>신고내역</h3>
 <div id="leftbox">
             <span><a href="<%= request.getContextPath() %>/customer/qnaBoardList">Q&A</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/faqBoardList">FAQ</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/reportBoardList">신고내역</a></span>
        </div>
        <div class="board_list_wrap">
            <table class="board_list">
                <caption>게시판 목록</caption>
                <thead>
                    <tr>
                        <th colspan="7">제목</th>
                        <th>날짜</th>
                    </tr>
                </thead>
                <tbody>
 <%
	List<ReportBoard> list = (List<ReportBoard>) request.getAttribute("list");
	for(ReportBoard reportBoard : list){
%>
 
                    <tr>
                        <td class="tit" colspan="7">
                          <a href="<%= request.getContextPath() %>/customer/reportBoardView?no=<%= reportBoard.getReportNo() %>"><%= reportBoard.getTitle() %></a>
                        </td>
                        <td><%= reportBoard.getRegDate()%></td>
                    </tr>          
                </tbody>
 <%} %>         
            </table>
        </div>
 </div> 
</section>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>