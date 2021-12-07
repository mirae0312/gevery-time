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
            <span><a href="<%= request.getContextPath() %>/customer/qnaBoardList">Q&A</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/faqBoardList">FAQ</a></span>
            <br><br>
            <span><a href="<%= request.getContextPath() %>/customer/reportBoardList">신고내역</a></span>
 </div>
        <div class="board_list_wrap">
            <table class="board_list">
                <caption>Q&A</caption>
                <thead>
                    <tr>
                        <th class="header">제목</th>
                        <th><%=qnaBoard.getTitle() %></th>
                        <th></th><th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th class="header">작성자</th>
                        <th><%=qnaBoard.getWriter() %></th>
                         <th class="header">날짜</th>
                        <th><%= qnaBoard.getRegDate() %></th>
                   
                    </tr>
                     <tr>
                        <th class="header">분류</th>
                        <th><%= qnaBoard.getCategory() %></th>
                    </tr>
                    <tr id="contents">
                        <th class="header">내용</th>
                        <th><%=qnaBoard.getContent() %></th>
                    </tr>
                </tbody>
            </table>
            <br> 
        </div>             
        <br><br><br><br><br>
        <div id="ifReply"></div> 
     
</div>
         
        <div class="button">
        <input type="button" class="buttonlist" value="목록" onclick="showQnaBoardList()"/>
        
	<% 	if(
				loginMember != null && 
				(
				  loginMember.getMemberId().equals(qnaBoard.getWriter())
				  || MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())
				)
			){ %>
        <input type="button"  class="buttonlist" value="수정" onclick="updateQnaBoard()"/>
        <input type="button" class="buttonlist" value="삭제" onclick="deleteQnaBoard()"/>
         <% 	} %>
        <% 	if(
				loginMember != null && 
				( MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole()))
			){ %>
        <input type="button" class="buttonlist" value="답변" onclick="replyQnaBoard()"/>
        </div>
  <% 	} %>
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
 	

   $(() => {
         // ajax
         $.ajax({
             url: "<%= request.getContextPath() %>/customer/getAnswer",
             method: "GET",
             data:{
                 no: <%= qnaBoard.getNo() %>
             },
             success(data){
                 console.log(data);
               
                 $(data).each((i, {title, writer, content}) => {
                 const reply = 
                	
                 `
                 <div class="board_list_wrap1">
                 <table class="board_list";>
                 <br>
                 <span style="font-size:20px; margin-left:50px; font-weight: 800; "> [↳답변입니다.]  </span>
                  <br><br>
                
                 <tr>
                 	<th class="header">작성자</th>
                     <td>\${writer}</td>
                 </tr>
                  <tr>
                     <td class="header" id="replycontent">내용</td>
                     <td>\${content}</td>
                </tr>
                </table>
                </div>
                `;
                 $("#ifReply").append(reply);
                });
    			},
             error:console.log
         });
            	});
 </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
		