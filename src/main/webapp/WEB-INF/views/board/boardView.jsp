<%@page import="com.zea.geverytime.board.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="com.zea.geverytime.common.model.vo.Attachment"%>
<%@page import="com.zea.geverytime.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    
<% Board board = (Board)request.getAttribute("board");%>

	<table>
	<tr>
		<th>제목</th>
		<td><%=board.getTitle() %></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=board.getWriter() %></td>
	
	<%
		List<Attachment> attachments = board.getAttachments();
		if(attachments!= null && !attachments.isEmpty()) {
			int i = 1;
			for(Attachment attach : attachments){
				
	%>
	</tr>
			<tr>
			<th>첨부파일<%= i %></th>
			<td>
				<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
				<a href="<%= request.getContextPath() %>/board/fileDownload?no=<%= attach.getNo() %>"><%= attach.getOriginalFilename() %></a>
				<span>
				</span>
			</td> 
	</tr>
	<% i++;}}%>
	
	<tr>
		<th>내용</th>
		<td><h1><%=board.getContent() %></h1></td>
	</tr>
	<tr>
		<td><input type="button" value="수정하기"/></td>
		<td><input type="button" value="삭제하기" /></td>
	</tr> 
	</table>
		<div class="comment-container">
        <div class="comment-editor">
            <form 
            	action="<%=request.getContextPath()%>/board/boardCommentEnroll" 
            	method="post" 
            	name="boardCommentFrm">
                <input type="hidden" name="boardNo" value="" />
                <input type="hidden" name="writer" value="" />
                <input type="hidden" name="commentLevel" value="1" />
                <input type="hidden" name="commentRef" value="0" />    
				<textarea name="content" cols="60" rows="3" style="resize:none; width:80%;"></textarea>
                <button type="submit" id="btn-comment-enroll1">등록</button>
            </form>
        </div>
		<!--table#tbl-comment-->

		<table id="tbl-comment">
			<tr class="level1">
				<td>
					<sub class="comment-writer">comment-writer</sub>
					<sub class="comment-date">comment-date</sub>
					<br />
					<%-- 댓글내용 --%>
				</td>
				<td>
					<button class="btn-reply" value="">답글</button>
					<!-- 댓글주인에게만 보임 -->
					<button class="btn-deleteComment" value="" style ="float:right"> 삭제</button>
				</td>
			</tr>
			<tr class="level2">
				<td>
					<sub class="comment-writer">comment-writer</sub>
					<sub class="comment-date">comment-date</sub>
					<br />
					<%-- 대댓글내용 --%>
					<button class="btn-deleteComment" value="" style ="float:right"> 삭제</button>
				</td>
				<td></td>
			</tr>
		</table>
		</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>