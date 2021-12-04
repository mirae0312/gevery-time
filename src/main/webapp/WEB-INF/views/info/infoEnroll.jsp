<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Info info = (Info) request.getAttribute("info");
	String bno = info.getBusinessNo();
	String no = bno.substring(bno.length() - 1);
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info/info.css" />
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4247f28f0dc06c5cc8486ac837d411ff&libraries=services,clusterer,drawing"></script>
<div class="enroll-wrapper">
	<%-- 전체 등록 폼 --%>
	<form name="infoEnrollFrm" action="<%= request.getContextPath() %>/info/Enroll" 
		method="post" enctype="multipart/form-data">
		<%-- 아이디 사업자 번호 --%>
		<input type="hidden" name="writer" value="<%= info.getMemberId() %>" />
		<input type="hidden" name="businessNo" value="<%= info.getBusinessNo() %>" />
		<input type="hidden" name="selectNo" value="<%= no %>" />
		<%-- 왼쪽 상단 위치 --%>
		<div class="left-head">
			상호명
			<input type="text" name="businessName" id="business-name" value="<%= info.getBusinessName() %>" readonly/>
			썸네일
			<div class="thumb"><img src="#" alt="" id="thumbnail" style="width:200px;height:180px;"/></div>
			<input type="file" name="headFile" accept="image/*" onchange="setThumbnail();" id="head-file" required />
			인사말
			<input type="text" name="headContent" id="head-content" />		
		</div><br />
		<%-- 오른쪽 상단 위치 --%>
		<div class="right-head">
			<label for="tel">전화번호</label>
			<input type="text" name="tel" id="tel" value="<%= info.getBusinessTel() %>" readonly/><br />
			<label for="addr">주소</label>
			<input type="text" name="addr" id="addr" value="<%= info.getBusinessAddress() %>" readonly /><br />
			영업시간
			<input type="time" name="startHour" class="business-hours" required/>~<input type="time" name="endHour" class="business-hours" required/><br />
			점심시간
			<input type="time" name="startLaunch" class="launch" />~<input type="time" name="endLaunch" class="launch" /><br />
			저녁시간
			<input type="time" name="startDinner" class="dinner" />~<input type="time" name="endDinner" class="dinner" /><br />
			휴일
			<input type="checkbox" name="holiday" value="월" id="mon" /><label for="mon">월</label>
			<input type="checkbox" name="holiday" value="화" id="tue" /><label for="tue">화</label>
			<input type="checkbox" name="holiday" value="수" id="wed" /><label for="wed">수</label>
			<input type="checkbox" name="holiday" value="목" id="thu" /><label for="thu">목</label>
			<input type="checkbox" name="holiday" value="금" id="fri" /><label for="fri">금</label>
			<input type="checkbox" name="holiday" value="토" id="sat" /><label for="sat">토</label>
			<input type="checkbox" name="holiday" value="일" id="sun" /><label for="sun">일</label>
			<br />
			<%-- 홈페이지 입력 2개 까지 가능 --%>
			<div id="site-wrap">
				<label for="site">홈페이지</label>
				<input type="text" name="site1" class="site" />
				<button type="button" class="add-btn add-site btn" onclick="addSite();">추가</button>			
			</div><br />
<% if("1".equals(no)){ // 병원1 %>
			<div class="service-wrap hservice">
				진료과목
				<input type="text" name="hservice1" class="hservice" />
			</div><br />
			<button type="button" class="add-btn add-hservice btn" onclick="addhservice();">추가</button>
<% } %>
<% if("2".equals(no) || "3".equals(no)){ // 카페2, 음식점3 %>
			<div class="service-wrap">
				<label for="service">가격표</label>
				<input type="text" name="service1" class="service" />:			
				<input type="text" name="price1" class="service" />(단위 천원)	<br />		
			</div><br />
			<button type="button" class="add-btn add-service btn" onclick="addService();">추가</button>
<% } %>
<% if("4".equals(no)){ // 펜션4 %>
			<div class="service-wrap">
				<table>
					<thead>
						<tr>
							<th rowspan="2">객실 (단위 만원)</th>
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
					</thead>
					<tbody>
						<tr>
							<td><input type="text" name="room1" /></td>
							<td><input type="text" name="price11" /></td>
							<td><input type="text" name="price12" /></td>
							<td><input type="text" name="price13" /></td>
							<td><input type="text" name="price14" /></td>
							<td><input type="text" name="price15" /></td>
							<td><input type="text" name="price16" /></td>
						</tr>					
						<tr>
							<td><input type="text" name="room2" /></td>
							<td><input type="text" name="price21" /></td>
							<td><input type="text" name="price22" /></td>
							<td><input type="text" name="price23" /></td>
							<td><input type="text" name="price24" /></td>
							<td><input type="text" name="price25" /></td>
							<td><input type="text" name="price26" /></td>
						</tr>					
						<tr>
							<td><input type="text" name="room3" /></td>
							<td><input type="text" name="price31" /></td>
							<td><input type="text" name="price32" /></td>
							<td><input type="text" name="price33" /></td>
							<td><input type="text" name="price34" /></td>
							<td><input type="text" name="price35" /></td>
							<td><input type="text" name="price36" /></td>
						</tr>					
					</tbody>
				</table>
			</div>
<% } %>
<% if("5".equals(no)){ // 미용실5 %>
			<div class="service-wrap">
				<table>
					<thead>
						<tr>
							<th rowspan="2">무게 (단위 만원)</th>
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
					</thead>
					<tbody>
						<tr>
							<td>5kg미만</td>
							<td><input type="text" name="smallBath1" /></td>
							<td><input type="text" name="middleBath1" /></td>
							<td><input type="text" name="specialBath1" /></td>
							<td><input type="text" name="smallBathAnd1" /></td>
							<td><input type="text" name="middleBathAnd1" /></td>
							<td><input type="text" name="specialBathAnd1" /></td>
							<td><input type="text" name="smallMachine1" /></td>
							<td><input type="text" name="middleMachine1" /></td>
							<td><input type="text" name="specialMachione1" /></td>
							<td><input type="text" name="smallSpotting1" /></td>
							<td><input type="text" name="middleSpotting1" /></td>
							<td><input type="text" name="specialSpotting1" /></td>
							<td><input type="text" name="smallScissors1" /></td>
							<td><input type="text" name="middleScissors1" /></td>
							<td><input type="text" name="specialScissors1" /></td>
						</tr>
						<tr>
							<td>7kg미만</td>
							<td><input type="text" name="smallBath2" /></td>
							<td><input type="text" name="middleBath2" /></td>
							<td><input type="text" name="specialBath2" /></td>
							<td><input type="text" name="smallBathAnd2" /></td>
							<td><input type="text" name="middleBathAnd2" /></td>
							<td><input type="text" name="specialBathAnd2" /></td>
							<td><input type="text" name="smallMachine2" /></td>
							<td><input type="text" name="middleMachine2" /></td>
							<td><input type="text" name="specialMachione2" /></td>
							<td><input type="text" name="smallSpotting2" /></td>
							<td><input type="text" name="middleSpotting2" /></td>
							<td><input type="text" name="specialSpotting2" /></td>
							<td><input type="text" name="smallScissors2" /></td>
							<td><input type="text" name="middleScissors2" /></td>
							<td><input type="text" name="specialScissors2" /></td>
						</tr>
						<tr>
							<td>9kg미만</td>
							<td><input type="text" name="smallBath3" /></td>
							<td><input type="text" name="middleBath3" /></td>
							<td><input type="text" name="specialBath3" /></td>
							<td><input type="text" name="smallBathAnd3" /></td>
							<td><input type="text" name="middleBathAnd3" /></td>
							<td><input type="text" name="specialBathAnd3" /></td>
							<td><input type="text" name="smallMachine3" /></td>
							<td><input type="text" name="middleMachine3" /></td>
							<td><input type="text" name="specialMachione3" /></td>
							<td><input type="text" name="smallSpotting3" /></td>
							<td><input type="text" name="middleSpotting3" /></td>
							<td><input type="text" name="specialSpotting3" /></td>
							<td><input type="text" name="smallScissors3" /></td>
							<td><input type="text" name="middleScissors3" /></td>
							<td><input type="text" name="specialScissors3" /></td>
						</tr>
					</tbody>
				</table>
			</div>
<% } %>
		</div>
		<%-- 본문 --%>
		<div id="body-description">
			<div class="description-wrapper">
				body
				<div class="thumb"><img src="#" alt="" class="body-thumb" style="width:500px;height:250px;"/></div>
				<input type="file" name="file1" class="file" accept="image/*" onchange="bodyThumb();" />
				<textarea name="bodyContent" id="bodyContent" cols="30" rows="10"></textarea><br />				
			</div>
			<%--<button type="button" class="add-btn add-description" onclick="addDescription();">추가</button>--%>
		</div><br />
		<%-- 하단 --%>
		<div class="the-way">
			<div id="map" style="width:500px;height:400px;"></div><br />
			오시는길
			<div class="way-guide">
				<textarea name="way" class="way" cols="30" rows="10" required></textarea><br />			
			</div>
		</div>
		<input type="submit" class="btn" />
	</form>
</div>
<script>
// 사진 미리보기
function setThumbnail(){
	const reader = new FileReader();
	
	reader.onload = function(event){
		$("#thumbnail").attr("src", event.target.result);
	}
	reader.readAsDataURL(event.target.files[0]);
	
}
function bodyThumb(){
	const reader = new FileReader();
	
	reader.onload = function(event){
		$(".body-thumb").attr("src", event.target.result);
	}
	reader.readAsDataURL(event.target.files[0]);
	
}

// 사이트 부분 입력 추가
const addSite = (e) => {
	const site2 = `
		<input type="text" name="site2" class="site" />
	`;
	$("#site-wrap").append(site2);
	$(".site-wrap .add-site").attr("disabled", "disabled");
	
};

// 진료과목 추가
var i = 2;
const addhservice = () => {
	var addSer = `
	,<input type="text" name="hservice\${i}" class="hservice" /><br />
	`;
	$(".hservice").append(addSer);
	if(i === 7){
		$(".right-head .add-hservice").attr("disabled", "disabled");			
	}
	i++;
};

// 가격표 추가
var i = 2;
const addService = () => {
	var addSer = `
	,<input type="text" name="service\${i}" class="service" />:
	<input type="text" name="price\${i}" class="service" />원	<br />
	`;
	$(".service-wrap").append(addSer);
	if(i === 7){
		$(".right-head .add-service").attr("disabled", "disabled");			
	}
	i++;
};

// 썸머노트
$(document).ready(function() {
    $('bodyContent').summernote({
          height: 300,                 // 에디터 높이
          minHeight: null,             // 최소 높이
          maxHeight: null,             // 최대 높이
          focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
          lang: "ko-KR",                    // 한글 설정
          placeholder: '최대 2048자까지 쓸 수 있습니다',    //placeholder 설정
          disableResizeEditor: true
    });
	$('#bodyContent').summernote({
		height: 300,
		focus: false,
		disableResizeEditor: true,
		toolbar: [
			// [groupName, [list of button]]
			['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체']],
			['style', ['bold', 'italic', 'underline', 'clear']],
			['font', ['strikethrough', 'superscript', 'subscript']],
			['fontsize', ['fontsize']],
			['color', ['color']],
			['para', ['ul', 'ol', 'paragraph']],
			['height', ['height']],
		]
	});
	$('.way').summernote({
		height: 300,
		focus: false,
		disableResizeEditor: true,
		toolbar: [
			// [groupName, [list of button]]
			['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체']],
			['style', ['bold', 'italic', 'underline', 'clear']],
			['font', ['strikethrough', 'superscript', 'subscript']],
			['fontsize', ['fontsize']],
			['color', ['color']],
			['para', ['ul', 'ol', 'paragraph']],
			['height', ['height']],
		]
	});
});
</script>
<script>
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
		    content: '<div style="width:150px;text-align:center;padding:6px 0;"><%= info.getBusinessName() %></div>'
		});
		infowindow.open(map, marker);
		
		// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		map.setCenter(coords);
	} 
});    
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 