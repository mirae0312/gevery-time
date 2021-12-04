<%@page import = "com.zea.geverytime.customer.model.vo.FaqBoard" %>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/faqList.css" /> 
 <%	List<FaqBoard> list = (List<FaqBoard>) request.getAttribute("list"); %>
 <section id="faqBoard-container">
 <div id="total">
        <h3>자주 묻는 질문</h3>
 <div id="leftbox">
            <span><a href="<%= request.getContextPath() %>/customer/qnaBoardList">Q&A</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/faqBoardList">FAQ</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/reportBoardList">신고내역</a></span>
 </div>
 <%String searchKeyword = request.getParameter("searchKeyword"); %><!-- 이번 요청의 searchKeyword -->
 <div id="search" class="search">
            <form action="<%=request.getContextPath()%>/customer/categoryFinder">
                <input type="radio" name="searchKeyword" value="[결제문의]" <%= "[결제문의]".equals(searchKeyword) ? "checked" : "" %>> 결제문의
                <input type="radio" name="searchKeyword" value="[신고문의]" <%= "[신고문의]".equals(searchKeyword) ? "checked" : "" %>> 신고문의
                <input type="radio" name="searchKeyword" value="[회원정보문의]" <%= "[회원정보문의]".equals(searchKeyword) ? "checked" : "" %>> 회원정보문의
                <input type="radio" name="searchKeyword" value="[포인트문의]" <%= "[포인트문의]".equals(searchKeyword) ? "checked" : "" %>> 포인트문의
                <input type="radio" name="searchKeyword" value="[기타문의]" <%= "[기타문의]".equals(searchKeyword) ? "checked" : "" %>> 기타문의
                <button type="submit" id="radioSubmit">검색</button>
            </form>
    </div>
 
        <div class="board_list_wrap">
            <table class="board_list">
                <caption>게시판 목록</caption>
                <thead>
                </thead>
                <tbody>
 <%

 	
	for(FaqBoard faqBoard : list){
	 
%>
 		 
                    <tr>
                       <!--  <td><%= faqBoard.getNo() %></td> --> 
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
<% 	if(
				loginMember != null && 
				(MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()))
			){ %>
        <div id="inputBox"><input type="button" value="글쓰기" id="btn-add"
        	onclick="location.href='<%= request.getContextPath() %>/customer/faqBoardForm'"/></div>
 <%
	}
		 
%>
 </div> 
	<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
</section>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>