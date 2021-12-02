<%@page import = "com.zea.geverytime.customer.model.vo.QnaBoard" %>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/qnaList.css" /> 
 <section id="qnaBoard-container">
 <div id="total">
        <h3>Q&A</h3>
 <div id="leftbox">
            <span>Q&A</span>
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
                        <th>No</th>
                         <th>분류</th>
                        <th colspan="5">제목</th>
                        <th>작성자</th>
                        <th>날짜</th>
                    </tr>
                </thead>
                <tbody>
 <%
	List<QnaBoard> list = (List<QnaBoard>) request.getAttribute("list");
	for(QnaBoard qnaBoard : list){
		if(qnaBoard.getReplyLevel() == 1){
%>
 
                    <tr>
                        <td><%= qnaBoard.getNo() %></td>
                        <td style="font-size:12px";><%= qnaBoard.getCategory() %></td>
                        <td class="tit" colspan="5">
                          <a href="<%= request.getContextPath() %>/customer/qnaBoardView?no=<%= qnaBoard.getNo() %>"><%= qnaBoard.getTitle() %></a>
                        </td>
                        <td><%= qnaBoard.getWriter() %></td>
                        <td><%=qnaBoard.getRegDate() %></td>
                    </tr>
                     
                </tbody>
  
<%
	}else{
		 
%>

  <tr>
  
                        <td><%= qnaBoard.getNo() %></td>
                        <td style="font-size:12px;color:red"><%= qnaBoard.getCategory() %></td>
                        <td class="tit" colspan="5">
                          <a href="<%= request.getContextPath() %>/customer/qnaBoardView?no=<%= qnaBoard.getNo() %>">ㄴ>RE: <%= qnaBoard.getTitle() %></a>
                          
                        </td>
                        <td><%= qnaBoard.getWriter() %></td>
                        <td><%=qnaBoard.getRegDate() %></td>
                    </tr>	

<%
	}
		 
%>
<%
	}
		 
%>
 
            </table>
          
        </div>
        <div id="inputBox"><input type="button" value="글쓰기" id="btn-add"
        	onclick="location.href='<%= request.getContextPath() %>/customer/qnaBoardForm'"/></div>
	<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
 </div> 
</section>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>