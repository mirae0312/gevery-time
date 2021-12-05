<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="body-wrapper">
	
	<%-- 사진영역 --%>
	<div class="photo-wrapper"></div>
	
	<%-- 게시글영역 --%>
	<div class="board-wrapper"></div>
	
	<%-- 인포영역 --%>
	<div class="info-board-wrapper">
		<table>
			<tr>
				<td></td>
			</tr>
		</table>
	</div>
	
	<%-- 마켓영역 --%>
	<div class="market-wrapper"></div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>