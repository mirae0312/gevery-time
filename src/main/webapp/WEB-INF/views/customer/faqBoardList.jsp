<%@page import = "com.zea.geverytime.customer.model.vo.FaqBoard" %>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/faqList.css" /> 
 <%	List<FaqBoard> list = (List<FaqBoard>) request.getAttribute("list"); %>
 <section id="faqBoard-container">
 <div id="total">
        <h3>FAQ</h3>
 <div id="leftbox">
            <span><a href="<%= request.getContextPath() %>/customer/qnaBoardList">Q&A</a></span>
            <br>
            <span><a href="<%= request.getContextPath() %>/customer/faqBoardList">FAQ</a></span>
            <br>
            <span>신고내역</span>
 </div>
 <%String searchKeyword = request.getParameter("searchKeyword"); %><!-- 이번 요청의 searchKeyword -->
 <div id="search" class="search">
            <form action="<%=request.getContextPath()%>/customer/categoryFinder">
                <input type="radio" name="searchKeyword" value="[회원가입관련]" <%= "[회원가입관련]".equals(searchKeyword) ? "checked" : "" %>> 회원가입관련
                <input type="radio" name="searchKeyword" value="결제" <%= "결제".equals(searchKeyword) ? "checked" : "" %>> 결제
                <input type="radio" name="searchKeyword" value="포인트" <%= "포인트".equals(searchKeyword) ? "checked" : "" %>> 포인트
                <button type="submit">검색</button>
            </form>
    </div>
 
        <div class="board_list_wrap">
            <table class="board_list">
                <caption>게시판 목록</caption>
                <thead>
                    <tr>
                        <th>No</th>
                        <!--  <th>분류</th>-->
                        <th colspan="5">제목</th>
                    </tr>
                </thead>
                <tbody>
 <%

 	
	for(FaqBoard faqBoard : list){
	 
%>
 		 
                    <tr>
                        <td><%= faqBoard.getNo() %></td>
                        <td class="tit" colspan="10">
                          <a href="<%= request.getContextPath() %>/customer/faqBoardView?no=<%= faqBoard.getNo() %>"><%= faqBoard.getCategory() %>&nbsp;<%= faqBoard.getTitle() %></a>
                        </td>
                    </tr>
                     
                </tbody>
  
<%
	}
		 
%>
 
            </table>
          
        </div>
        <div id="inputBox"><input type="button" value="글쓰기" id="btn-add"
        	onclick="location.href='<%= request.getContextPath() %>/customer/faqBoardForm'"/></div>
	<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
 </div> 
</section>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>