<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="body-wrapper">
	
	<%-- 사진영역 --%>
	<h1>사진</h1>
	<div class="photo-wrapper">
		
	</div><hr />
	
	<%-- 게시글영역 --%>
	<h1>게시글</h1>
	<div class="board-wrapper">
		<div class="board-left-wrap">
		
		</div>
		<div class="board-right-wrap">
		
		</div>
	</div><hr />
	
	<%-- 인포영역 --%>
	<h1>정보</h1>
	<div class="info-board-wrapper">
		<table id="info-board-table">
			<tr>
				<td><h1>한 주간의 인기 SPOT</h1></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div><hr />
	
	<%-- 마켓영역 --%>
	<h1>마켓</h1>
	<div class="market-wrapper">
		<table id="market-board-table">
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div><br />
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>