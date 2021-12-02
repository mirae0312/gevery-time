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
	String codeN = (String) request.getAttribute("codeN");
	String recommend = (String) request.getAttribute("recommend");
	List<InfoReview> ir = (List<InfoReview>) request.getAttribute("ir");
%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4247f28f0dc06c5cc8486ac837d411ff"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4247f28f0dc06c5cc8486ac837d411ff&libraries=services,clusterer,drawing"></script>
<div class="info-view-wrapper">
	<div class="info-head-wrapper">
		<div class="left-side">
			<h1><%= info.getBusinessName() %></h1>
			<img style="width:150px; height:150px;" src="<%= request.getContextPath() %>/upload/info/<%= info.getAttachments().get(0).getRenamedFilename() %>" alt="" />
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
					<td><%= info.getStartHour() %>~</td>
					<td><%= info.getEndHour() %></td>
					<td><%= info.getStartLaunch() %>~</td>
					<td><%= info.getEndLaunch() %></td>
					<td><%= info.getStartDinner() %>~</td>
					<td><%= info.getEndDinner() %></td>
				</tr>
			</table>
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
					<td><%= p.getRoom() %></td>
					<td><%= p.getPrice1() %></td>
					<td><%= p.getPrice2() %></td>
					<td><%= p.getPrice3() %></td>
					<td><%= p.getPrice4() %></td>
					<td><%= p.getPrice5() %></td>
					<td><%= p.getPrice6() %></td>
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
					<td><%= s.getSmallBath() %></td>
					<td><%= s.getMiddleBath() %></td>
					<td><%= s.getSpecialBath() %></td>
					<td><%= s.getSmallBathAnd() %></td>
					<td><%= s.getMiddleBathAnd() %></td>
					<td><%= s.getSpecialBathAnd() %></td>
					<td><%= s.getSmallMachine() %></td>
					<td><%= s.getMiddleMachine() %></td>
					<td><%= s.getSpecialMachine() %></td>
					<td><%= s.getSmallSpotting() %></td>
					<td><%= s.getMiddleSpotting() %></td>
					<td><%= s.getSpecialSpotting() %></td>
					<td><%= s.getSmallScissors() %></td>
					<td><%= s.getMiddleScissors() %></td>
					<td><%= s.getSpecialScissors() %></td>
				</tr>
		<% } %>
	<% } %>
			</table>
<% } %>
			<p>홈페이지 : <%= info.getSite() %></p>
		</div>	<br />	
	</div><br />
	<div class="info-body-wrapper">
<% if(info.getAttachments().get(1) != null){ %>
		<img style="width:300px; height:200px;" src="<%= request.getContextPath() %>/upload/info/<%= info.getAttachments().get(1).getRenamedFilename() %>" alt="" />
<% } %>
		<p><%= info.getBodyContents() %></p>
		
		<div id="map" style="width:500px;height:400px;"></div><br />
		<div class="way-content">
			<h1>오시는 길</h1>
			<p><%= info.getRoadGuide() %></p>
		</div>
	</div>
	
	<input type="checkbox" name="like" id="info-like" <%= "G".equals(recommend) ? "checked" : "" %> />
	<label for="info-like">좋아요</label>
	<div class="info-review-wrapper">
<% if(ir != null && !ir.isEmpty()){ %>
	<% for(InfoReview re : ir){ %>
		<div class="info-review">
			<form action="" class="review" method="POST">
				<input type="hidden" value="<%= re.getrCode() %>" />
				<div class="review-writer"><%= re.getMemberId() %></div>
				<div class="review-head"><%= re.getHeadContent() %></div>
				<div class="review-content"><%= re.getContent() %></div>
				<div class="review-reg-date"><%= re.getRegDate() %></div>
				<input type="button" class="reivew-report" />
			</form>
		</div>
	<% } %>
<% } %>
		<form action="<%= request.getContextPath() %>/info/insertReview"
			id="reviewEnrollFrm"
			method="post" enctype="multipart/form-data">
			<div id="writeReview"></div><br />
<% if(loginMember != null && MemberService.USER_ROLE.equals(loginMember.getMemberRole())){ %>
			<button>등록</button>
<% } %>
		</form>
	</div>
</div>
<script>
// 리뷰신고
$(".review-report").click((e) => {
	
});

// 좋아요
$("#info-like").change((e) => {
<% if(loginMember != null && MemberService.USER_ROLE.equals(loginMember.getMemberRole())){ %>
	if($("#info-like").is(":checked") == true){
		console.log("체크된 상태");
		$.ajax({
			url: "<%= request.getContextPath() %>/info/likeCount",
			data: {
				code: "<%= info.getCode() %>",
				memberId: "<%= loginMember.getMemberId() %>"
			},
			success(data){
				console.log(data);
			},
			error: console.log
		});
	}
	if($("#info-like").is(":checked") == false){
		console.log("체크 안됨");
		$.ajax({
			url: "<%= request.getContextPath() %>/info/likeCount?code=<%= info.getCode() %>&&memberId=<%= loginMember.getMemberId() %>",
			success(data){
				console.log(data);
			},
			error: console.log
		});
	}		
<% }else{ %>
	alert("로그인 후 이용해 주세요");
	$("#info-like").attr("disabled", "disabled");
<% } %>
});

// summernote
$(document).ready(function() {
	$('#writeReview').summernote({
		height: 300,
		focus: false,
		disableResizeEditor: true,
		toolbar: [
			['style', ['bold', 'italic', 'underline', 'clear']],
			['font', ['strikethrough', 'superscript', 'subscript']],
			['fontsize', ['fontsize']],
			['color', ['color']],
			['para', ['ul', 'ol', 'paragraph']],
			['height', ['height']],
		]
	});
	
});


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

<%@ include file="/WEB-INF/views/common/footer.jsp" %>