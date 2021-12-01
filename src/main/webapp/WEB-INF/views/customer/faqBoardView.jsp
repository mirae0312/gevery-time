<%@page import="com.zea.geverytime.customer.model.vo.FaqBoard"%>
<%@page import="java.util.List"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/qnaView.css" />
<%
		FaqBoard faqBoard = (FaqBoard) request.getAttribute("faqBoard");
%>
 
<section id="board-container">
<div id="total">
       <div id="leftbox">
            <span>Q&A</span>
            <br>
            <span>FAQ</span>
            <br>
            <span>신고내역</span>
        </div>
        <div class="board_list_wrap">
            <table class="board_list">
                <caption>자주묻는질문 상세보기</caption>
                <thead>
                    <tr>
                        <th>제목</th>
                        <th><%=faqBoard.getTitle() %></th>
                    </tr>
                </thead>
                <tbody>
                     <tr>
                        <th>카테고리</th>
                        <th><%= faqBoard.getCategory() %></th>
                    </tr>
                    <tr id="content">
                        <th>내용</th>
                        <th><%=faqBoard.getContent() %></th>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="button">
        <input type="button" value="목록" onclick="showFaqBoardList()"/>
       <!-- <input type="button" value="수정" onclick="updateFaqBoard()"/> --> 
        <input type="button" value="삭제" onclick="deleteFaqBoard()"/>
        
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