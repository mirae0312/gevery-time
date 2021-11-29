<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.zea.geverytime.market.productsale.model.vo.ProductBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	  
<%
	ProductBoard board = (ProductBoard) request.getAttribute("board");
	List<Map<String, Object>> questions = (List<Map<String, Object>>) request.getAttribute("questions");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세목록</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>판매게시글</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>제목</th>
				<td><%= board.getTitle() %></td>
			<tr>
				<th>작성자</th>
				<td><%= board.getSellerId() %></td>
			</tr>
			<tr>
				<th>판매상태</th>
				<td><%= board.getProduct().getState() %></td>
			</tr>
			<tr>
				<th colspan=2>내용</th>
			</tr>
			<tr>
				<td colspan=2><%= board.getContent() %></td>
			</tr>
		</tbody>	
	</table>
	
	<table>
		<thead>
			<tr>
				<th colspan=3>문의글목록</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>답변하기</th>
			</tr>
		<%
			int listNum = 1;
			for(Map<String, Object> question : questions) {
				if((int)question.get("qaLevel") == 1) {
		%>
		
			<tr>
				<td><%= listNum %>번</td>
				<td><%= question.get("title") %></td>
				<td><%= question.get("content") %></td>
				<td><%= question.get("writer") %></td>
				<td>
					<form action="<%= request.getContextPath() %>/product/qaDelete" method="POST">
						<input type="hidden" name="delCommentNo" value="<%= question.get("no") %>" />	
						<input type="hidden" name="delCommentBoardNo" value="<%= board.getBoardNo() %>" />				
						<input type="submit" value="삭제하기" />
					</form>
				</td>
					
		<%
					int checkNum = 0;
					for(Map<String, Object> answer : questions) {
						if((int) answer.get("refNo") == (int)question.get("no")){
							checkNum = -1;
		%>
				<td></td>
			</tr>
			<tr>
							<td>(답글)</td>
							<td><%= answer.get("title") %></td>
							<td><%= answer.get("content") %></td>
							<td><%= answer.get("writer") %></td>
							<td>
								<form action="<%= request.getContextPath() %>/product/qaDelete" method="POST">
									<input type="hidden" name="delCommentNo" value="<%= answer.get("no") %>" />
									<input type="hidden" name="delCommentBoardNo" value="<%= board.getBoardNo() %>" />					
									<input type="submit" value="삭제하기" />
								</form>
							</td>
			</tr>
		<%
							break;
						}
					}
					listNum++;
					if(checkNum == 0){
		%>
				<td><input type="button" value="답글달기" class="reply"/></td>
			</tr>
			<tr class="replyTr" style="display:none;">
				<th>답글달기</th>
				<td colspan=2>
					<form action="<%= request.getContextPath() %>/product/answerEnroll" method="POST">
						<label for="awriter">작성자</label><input type="text" name="awriter"/><br />
						<label for="acontent">내용</label><input type="text" name="acontent"/><br />
						<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
						<input type="hidden" name="commentNo" value="<%= (int)question.get("no") %>"/>
						<input type="submit" value="등록" />
					</form>
				</td>
			</tr>
		<%	
					}
				}
			}
		%>
			<tr>
				<th>입력하기</th>
				<td colspan=2>
					<form action="<%= request.getContextPath() %>/product/questionEnroll" method="POST">
						<label for="writer">작성자</label><input type="text" name="writer"/><br />
						<label for="qtitle">제목</label><input type="text" name="qtitle"/>
						<label for="qcontent">내용</label><input type="text" name="qcontent"/><br />
						<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
						<input type="submit" value="등록" />
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	
	<script>
		$(".reply").click((e) => {
			$(e.target).parent().parent().next().toggle(300, function(){
			});
		});
	
	</script>
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 
