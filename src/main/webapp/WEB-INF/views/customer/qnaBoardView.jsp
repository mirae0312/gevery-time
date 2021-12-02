<%@page import="com.zea.geverytime.customer.model.vo.QnaBoard"%>
<%@page import="java.util.List"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer/qnaView.css" />
<%
		QnaBoard qnaBoard = (QnaBoard) request.getAttribute("qnaBoard");
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
                <caption>게시판 상세보기</caption>
                <thead>
                    <tr>
                        <th>제목</th>
                        <th><%=qnaBoard.getTitle() %></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>작성자</th>
                        <th><%=qnaBoard.getWriter() %></th>
                    </tr>
                    <tr>
                        <th>날짜</th>
                        <th><%= qnaBoard.getRegDate() %></th>
                    </tr>
                     <tr>
                        <th>카테고리</th>
                        <th><%= qnaBoard.getCategory() %></th>
                    </tr>
                    <tr id="content">
                        <th>내용</th>
                        <th><%=qnaBoard.getContent() %></th>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="button">
        <input type="button" value="목록" onclick="showQnaBoardList()"/>
        <input type="button" value="수정" onclick="updateQnaBoard()"/>
        <input type="button" value="삭제" onclick="deleteQnaBoard()"/>
        <input type="button" value="답변" onclick="replyQnaBoard()"/>
        </div>
 </div>
 </section>
<!--  게시글 삭제-->
<form
	name="boardDelFrm"
	method="POST" 
	action="<%= request.getContextPath() %>/qnaBoard/qnaBoardDelete" >
	<input type="hidden" name="no" value="<%= qnaBoard.getNo() %>" />
</form>


 <script>
//목록으로 가기 
	const showQnaBoardList = () =>{
		location.href = "<%= request.getContextPath() %>/customer/qnaBoardList";
	};
	//답변글 작성
	const replyQnaBoard = () =>{
		location.href = "<%= request.getContextPath() %>/customer/qnaBoardReplyForm?no=<%= qnaBoard.getNo() %>";
	};
 	//게시글 수정
 	const updateQnaBoard = () =>{
 		location.href = "<%= request.getContextPath() %>/customer/qnaBoardUpdate?no=<%= qnaBoard.getNo() %>";
 	};
 	
 	//게시글 삭제
 	const deleteQnaBoard = () => {
 		if(confirm("게시물을 삭제하시겠습니까?")){
 			$(document.boardDelFrm).submit();		
 		}
 	};
 	
 </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
		