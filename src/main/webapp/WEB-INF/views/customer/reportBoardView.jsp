<%@page import="com.zea.geverytime.customer.model.vo.ReportBoard"%>
<%@page import="java.util.List"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/faqView.css" />
<%
		ReportBoard reportBoard = (ReportBoard) request.getAttribute("reportBoard");
%>
 
<section id="board-container">
<div id="total">
       <div id="leftbox">
             <span><a href="<%= request.getContextPath() %>/customer/qnaBoardList">Q&A</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/faqBoardList">FAQ</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/reportBoardList">신고내역</a></span>
        </div>
        <div class="board_list_wrap">
            <table class="board_list">
                <thead>
                    <tr>
                        <th>제목</th>
                        <th><%=reportBoard.getTitle() %></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>날짜</th>
                        <th><%=reportBoard.getRegDate() %></th>
                    </tr>
                    <tr id="content">
                        <th>내용</th>
                        <th><%=reportBoard.getContent() %></th>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="button2">
        <input type="button" class="buttonlist" value="목록" onclick="showFaqBoardList()"/>  
        </div>
 </div>
 </section>
 
  
 <script>
//목록으로 가기 
	const showFaqBoardList = () =>{
		location.href = "<%= request.getContextPath() %>/customer/reportBoardList";
	};
 </script>
 
<%@ include file="/WEB-INF/views/common/footer.jsp" %>