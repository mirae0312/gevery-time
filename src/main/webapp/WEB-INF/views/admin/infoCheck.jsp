<%@page import="com.zea.geverytime.common.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="com.zea.geverytime.info.model.vo.InfoReview"%>
<%@page import="com.zea.geverytime.info.model.vo.Salon"%>
<%@page import="com.zea.geverytime.info.model.vo.Pension"%>
<%@page import="com.zea.geverytime.info.model.vo.CafeRestaurant"%>
<%@page import="com.zea.geverytime.info.model.vo.Hospital"%>
<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Info info = (Info) request.getAttribute("info"); 
	String site1 = (String) request.getAttribute("site1");
	String site2 = (String) request.getAttribute("site2");
	String codeN = (String) request.getAttribute("codeN");
	
	String pic1 = "";
	String pic2 = "";
	
	if(info.getAttachments() != null && !info.getAttachments().isEmpty()){
		if(info.getAttachments().size() == 1){
			pic1 = info.getAttachments().get(0).getRenamedFilename();
		}else if(info.getAttachments().size() == 2){
			pic1 = info.getAttachments().get(0).getRenamedFilename();
			pic2 = info.getAttachments().get(1).getRenamedFilename();
		}
	}
	
%>
<%@ page import="java.sql.*" %>
<%
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	
%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin/infoCheck.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/summernote/summernote-lite.css">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath() %>/js/summernote/summernote-lite.js"></script>
<script src="<%= request.getContextPath() %>/js/summernote/lang/summernote-ko-KR.js"></script>

</head>
<body>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4247f28f0dc06c5cc8486ac837d411ff&libraries=services"></script>
<div class="info-view-wrapper">
	<div class="info-head-wrapper">
		<div class="left-side">
			<h1><%= info.getBusinessName() %></h1>
			<img style="width:150px; height:150px;" src="<%= request.getContextPath() %>/upload/info/<%= pic1 %>" alt="" />
			<p><%= info.getHeadContent() %></p>
		</div><br />
		<div class="right-side">
			<p>전화번호 : <%= info.getBusinessTel() %></p>
			<p>주소 : <%= info.getBusinessAddress() %></p>
			<table>
				<tr>
					<th colspan="2">영업시간</th>
					<th colspan="2">점심시간</th>
					<th colspan="2">저녁시간</th>
				</tr>
				<tr>
					<td><%= info.getStartHour() == null ? "" : info.getStartHour() %>~</td>
					<td><%= info.getEndHour() == null ? "" : info.getEndHour() %></td>
					<td><%= info.getStartLaunch() == null ? "" : info.getStartLaunch() %>~</td>
					<td><%= info.getEndLaunch() == null ? "" : info.getEndLaunch() %></td>
					<td><%= info.getStartDinner() == null ? "" : info.getStartDinner() %>~</td>
					<td><%= info.getEndDinner() == null ? "" : info.getEndDinner() %></td>
				</tr>
			</table>
<% if(site1 != null && !site1.isEmpty()){ %>
			<p>홈페이지 : <%= site1 %></p>
<% } %>
<% if(site2 != null && !site2.isEmpty()){ %>
			<p>홈페이지 : <%= site2 %></p>
<% } %>
<% if("1".equals(codeN)){ %>
			<p>진료과목</p>
	<% if(info.getHospitals() != null && !info.getHospitals().isEmpty()){ %>
		<% for(Hospital h : info.getHospitals()){ %>
			<p><%= h.getService() %></p>		
		<% } %>
	<% } %>
<% }else if("2".equals(codeN) || "3".equals(codeN)){ %>
			<table>
				
	<% if(info.getCafeRestaurants() != null && !info.getCafeRestaurants().isEmpty()){ %>
		<% for(CafeRestaurant cr : info.getCafeRestaurants()){ %>
				<tr>
					<td><%= cr.getService() %></td>
					<td><%= cr.getPrice() %></td>
				</tr>
		<% } %>
	<% } %>
			</table>
<% }else if("4".equals(codeN)){ %>
			<table>
				<tr>
					<th rowspan="2">방</th>
					<th colspan="2">비수기</th>
					<th colspan="2">성수기</th>
					<th colspan="2">평수기</th>
				</tr>
				<tr>
					<th>평일</th>
					<th>주말</th>
					<th>평일</th>
					<th>주말</th>
					<th>평일</th>
					<th>주말</th>
				</tr>
	<% if(info.getPensions() != null && !info.getPensions().isEmpty()){ %>
		<% for(Pension p : info.getPensions()){ %>
				<tr>
					<td><%= p.getRoom() == null ? "" : p.getRoom() %></td>
					<td><%= p.getPrice1() == null ? "" : p.getPrice1() %></td>
					<td><%= p.getPrice2() == null ? "" : p.getPrice2() %></td>
					<td><%= p.getPrice3() == null ? "" : p.getPrice3() %></td>
					<td><%= p.getPrice4() == null ? "" : p.getPrice4() %></td>
					<td><%= p.getPrice5() == null ? "" : p.getPrice5() %></td>
					<td><%= p.getPrice6() == null ? "" : p.getPrice6() %></td>
				</tr>
		<% } %>
	<% } %>
			</table>
<% }else if("5".equals(codeN)){ %>
			<table>
				<tr>
					<th rowspan="5">무게 별 차등</th>
					<th colspan="3">목욕</th>
					<th colspan="3">목욕+부분</th>
					<th colspan="3">기계컷</th>
					<th colspan="3">스포팅</th>
					<th colspan="3">가위컷</th>
				</tr>
				<tr>
					<th>소형견</th>
					<th>중형견</th>
					<th>특수견</th>
					<th>소형견</th>
					<th>중형견</th>
					<th>특수견</th>
					<th>소형견</th>
					<th>중형견</th>
					<th>특수견</th>
					<th>소형견</th>
					<th>중형견</th>
					<th>특수견</th>
					<th>소형견</th>
					<th>중형견</th>
					<th>특수견</th>
				</tr>
	<%if(info.getSalons() != null && !info.getSalons().isEmpty()){ %>
		<% for(Salon s : info.getSalons()){ %>
				<tr>
					<td><%= s.getSmallBath() == null ? "" : s.getSmallBath() %></td>
					<td><%= s.getMiddleBath() == null ? "" : s.getMiddleBath() %></td>
					<td><%= s.getSpecialBath() == null ? "" :s.getSpecialBath() %></td>
					<td><%= s.getSmallBathAnd() == null ? "" : s.getSmallBathAnd() %></td>
					<td><%= s.getMiddleBathAnd() == null ? "" : s.getMiddleBathAnd() %></td>
					<td><%= s.getSpecialBathAnd() == null ? "" : s.getSpecialBathAnd() %></td>
					<td><%= s.getSmallMachine() == null ? "" : s.getSmallMachine() %></td>
					<td><%= s.getMiddleMachine() == null ? "" : s.getMiddleMachine() %></td>
					<td><%= s.getSpecialMachine() == null ? "" : s.getSpecialMachine() %></td>
					<td><%= s.getSmallSpotting() == null ? "" : s.getSmallSpotting() %></td>
					<td><%= s.getMiddleSpotting() == null ? "" : s.getMiddleSpotting() %></td>
					<td><%= s.getSpecialSpotting() == null ? "" : s.getSpecialSpotting() %></td>
					<td><%= s.getSmallScissors() == null ? "" : s.getSmallScissors() %></td>
					<td><%= s.getMiddleScissors() == null ? "" : s.getMiddleScissors() %></td>
					<td><%= s.getSpecialScissors() == null ? "" : s.getSpecialScissors() %></td>
				</tr>
		<% } %>
	<% } %>
			</table>
<% } %>
		</div>	<br />	
	</div><br />
	<%-- 바디 내용 --%>
	<div class="info-body-wrapper">
		<img style="width:300px; height:200px;" src="<%= request.getContextPath() %>/upload/info/<%= pic2 %>" alt="" />
		<p><%= info.getBodyContents() %></p>
		<%-- 지도 --%>
		<div id="map" style="width:400px;height:300px;"></div><br />
		<%-- 길안내 --%>
		<div class="way-content">
			<h1>오시는 길</h1>
			<p><%= info.getRoadGuide() %></p>
		</div>
	</div>
</div>
	<input type="hidden" id="code" name="code" value="<%= info.getCode() %>" />
	<button id="checkButton" class="approve-btn btn">승인</button>
	<button id="falseButton" class="reject-btn btn">반려</button>
	<button id="deleteButton" class="delete-btn btn">삭제</button>
<script>
// 승인
$("#checkButton").click((e) => {
	$.ajax({
		url: "<%= request.getContextPath() %>/admin/check",
		method: "POST",
		data: {
			code: $("#code").val(),
			output: "I"
		},
		success(data){
			console.log(data);
			opener.location.reload();		
			close();
		},
		error:console.log
	});
});
// 반려
$("#falseButton").click((e) => {
	$.ajax({
		url: "<%= request.getContextPath() %>/admin/check",
		method: "POST",
		data: {
			code: $("#code").val(),
			output: "O"
		},
		success(data){
			console.log(data);
			opener.location.reload();		
			close();
		},
		error:console.log
	});
});
// 삭제
$("#deleteButton").click((e) => {
	$.ajax({
		url: "<%= request.getContextPath() %>/info/deleteMain",
		method: "POST",
		data: {
			code: $("#code").val(),
			output: "A"
		},
		success(data){
			console.log(data);
			opener.location.reload();		
			close();
		},
		error:console.log
	});
});
/* const checkTrue = () => {
	e.preventDefault();
	$(checkTrueFrm).submit();
	opener.parent.location.reload();
	//window.close();
}; */

const checkFalse = () => {
	close();
};

// 카카오 지도 api
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
	    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

//주소로 좌표를 검색합니다
geocoder.addressSearch('<%= info.getBusinessAddress() %>', function(result, status) {

// 정상적으로 검색이 완료됐으면 
	if (status === kakao.maps.services.Status.OK) {
	
		var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		// 결과값으로 받은 위치를 마커로 표시합니다
		var marker = new kakao.maps.Marker({
		    map: map,
		    position: coords
		});
		
		// 인포윈도우로 장소에 대한 설명을 표시합니다
		var infowindow = new kakao.maps.InfoWindow({
		    content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
		});
		infowindow.open(map, marker);
		
		// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		map.setCenter(coords);
		console.log(coords);
	} 
});   


</script>
</body>
</html>