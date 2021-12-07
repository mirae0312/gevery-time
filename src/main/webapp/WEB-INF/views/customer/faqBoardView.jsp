<%@page import="com.zea.geverytime.customer.model.vo.FaqBoard"%>
<%@page import="java.util.List"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/faqView.css" />
<%
		FaqBoard faqBoard = (FaqBoard) request.getAttribute("faqBoard");
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
                <caption>FAQ</caption>
                <thead>
                    <tr>
                        <th>제목</th>
                        <th><%=faqBoard.getTitle() %></th>
                    </tr>
                </thead>
                <tbody>
                     <tr>
                        <th>분류</th>
                        <th><%= faqBoard.getCategory() %></th>
                    </tr>
                    <tr id="content">
                        <th>내용</th>
                        <th><%=faqBoard.getContent() %></th>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="button2">
        <input type="button" class="buttonlist " value="목록" onclick="showFaqBoardList()"/>
<% 	if(
				loginMember != null && 
				(MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()))
			){ %>
        <input type="button" class="buttonlist" value="삭제" onclick="deleteFaqBoard()"/>
<%} %>       
        </div>
 </div>
 </section>
<!--  게시글 삭제-->
<form
	name="boardDelFrm"
	method="POST" 
	action="<%= request.getContextPath() %>/faqBoard/faqBoardDelete" >
	<input type="hidden" name="no" value="<%= faqBoard.getNo() %>" />
</form>


 <script>
//목록으로 가기 
	const showFaqBoardList = () =>{
		location.href = "<%= request.getContextPath() %>/customer/faqBoardList";
	};
 
 	//게시글 수정
 	const updateFaqBoard = () =>{
 		location.href = "<%= request.getContextPath() %>/customer/qnaBoardUpdate?no=<%= faqBoard.getNo() %>";
 	};
 	
 	//게시글 삭제
 	const deleteFaqBoard = () => {
 		if(confirm("게시물을 삭제하시겠습니까?")){
 			$(document.boardDelFrm).submit();		
 		}
 	};
 	
 </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>