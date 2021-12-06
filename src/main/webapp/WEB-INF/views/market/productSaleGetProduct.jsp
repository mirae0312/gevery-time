<%@page import="com.zea.geverytime.member.model.vo.Member"%>
<%@page import="com.zea.geverytime.market.productsale.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	List<Product> list = (List<Product>) request.getAttribute("list");
	String sellerId = (String) request.getAttribute("sellerId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록 상품 조회</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<h1>[<%= sellerId %> ]님이 판매중인 상품 목록입니다.</h1>
	<br/>
	
	<% if(list.isEmpty())  {%>
		<h2>텅 비었어요. 상품을 먼저 등록해주세요</h2>
		<input type="button" value="상품 등록하러가기" onclick="landingProductEnroll();"/>
	<% } else { %>
	<table>
		<thead>
			<tr>
				<th>선택</th>
				<th>상품번호</th>
				<th>이름</th>
				<th>가격</th>
				<th>분류</th>
				<th>판매상태</th>
			</tr>
		</thead>
		<tbody>
		<%
		for(Product pdt : list){
		%>
			<tr>
				<td>
					<input type="checkbox" value="<%= pdt.getPdtNo() %>"/>
					<input type="hidden" id="pdtNo" value="<%= pdt.getPdtNo() %>" />
					<input type="hidden" id="pdtName" value="<%= pdt.getPdtName() %>" />
					<input type="hidden" id="pdtDiv" value="<%= pdt.getPdtDiv() %>" />
					<input type="hidden" id="pdtPrice" value="<%= pdt.getPdtPrice() %>" />
				</td>
				<td><%= pdt.getPdtNo() %></td>
				<td><%= pdt.getPdtName() %></td>
				<td><%= pdt.getPdtPrice() %></td>
				<td><%= pdt.getPdtDiv() %></td>
				<td><%= pdt.getState() %></td>
			</tr>
		<%
		}
		%>
		</tbody>
	</table>
	
	<input type="button" value="선택하기" onclick="popUpClose();" />
	<input type="button" value="다시선택" onclick="reSelect();" />
	
	<% } %>
	<script>
		let selectNo = 0;
		let selectName = "";
		let selectPrice = 0;
		
		// checkbox 선택 시, no, name, price를 변수에 담음
		$("[type=checkbox]").change((e) => {
			console.log($(e.target).val())
			selectNo = $(e.target).val();
			selectName = $(e.target).siblings("[id=pdtName]").val();
			selectPrice = $(e.target).siblings("[id=pdtPrice]").val();
			selectDiv = $(e.target).siblings("[id=pdtDiv]").val();
			$("[type=checkbox]").prop("disabled", "true");
		});
		
		// checkbox 초기화
		const reSelect = () => {
			$("[type=checkbox]").prop("disabled", "");
			$("[type=checkbox]").prop("checked", "");
		}
		
		// popup 닫는 경우, 변수에 담은 정보를 부모 form에 입력함.
		const popUpClose = () => {
			const $frm = $(opener.document.productEnrollFrm);
			$frm.find("[name=pdtNo]").val(selectNo);
			$frm.find("[name=pdtName]").val(selectName);
			$frm.find("[name=pdtPrice]").val(selectPrice);
			$frm.find("[name=pdtDiv]").val(selectDiv);
			close();			
		};
		
		const landingProductEnroll = () => {
			window.opener.location.href="<%= request.getContextPath() %>/product/onsaleProduct?sellerId=<%= loginMember.getMemberId() %>";
			close();
		}
	</script>
	
</body>
</html>