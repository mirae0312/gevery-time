<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Info info = (Info) request.getAttribute("info");
%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4247f28f0dc06c5cc8486ac837d411ff"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
<div class="enroll-wrapper">
	<form name="infoEnrollFrm" action="<%= request.getContextPath() %>/info/Enroll" 
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="writer" value="<%= info.getMemberId() %>" />
		<input type="hidden" name="businessNo" value="<%= info.getBusinessNo() %>" />
		<div class="left-head">
			상호명
			<input type="text" name="businessName" id="business-name" value="<%= info.getBusinessName() %>" required/>
			썸네일
			<div class="thumb"><img src="#" alt="" id="thumbnail" style="width:200px;height:180px;"/></div>
			<input type="file" name="headFile" accept="image/*" onchange="setThumbnail();" id="head-file" />
			인사말
			<input type="text" name="headContent" id="head-content" />		
		</div><br />
		<div class="right-head">
			<label for="tel">전화번호</label>
			<input type="text" name="tel" id="tel" value="<%= info.getBusinessTel() %>" required/><br />
			<label for="addr">주소</label>
			<input type="text" name="addr" id="addr" value="<%= info.getBusinessAddress() %>" /><br />
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
			<div id="site-wrap">
				<label for="site">홈페이지</label>
				<input type="text" name="site1" class="site" />
				<button type="button" class="add-btn add-site" onclick="addSite();">추가</button>			
			</div><br />
			
			<div id="service-wrap">
				<label for="service">가격표</label>
				<input type="text" name="service1" class="service" />:			
				<input type="text" name="price1" class="service" />원	<br />		
			</div><br />
			<button type="button" class="add-btn add-service" onclick="addService();">추가</button>
		</div>
		<div id="body-description">
			<div class="description-wrapper">
				body
				<div class="thumb"><img src="#" alt="" class="body-thumb" style="width:500px;height:250px;"/></div>
				<input type="file" name="file1" class="file" accept="image/*" onchange="bodyThumb();" />
				<textarea name="bodyContent" id="bodyContent" cols="30" rows="10"></textarea><br />				
			</div>
			<%--<button type="button" class="add-btn add-description" onclick="addDescription();">추가</button>--%>
		</div><br />
		<div class="the-way">
			<div id="map" style="width:500px;height:400px;"></div><br />
			오시는길
			<div class="way-guide">
				<textarea name="way" class="way" cols="30" rows="10" required></textarea><br />			
			</div>
		</div>
		<input type="submit" />
	</form>
</div>
<script>
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

	const addSite = (e) => {
		const site2 = `
			<input type="text" name="site2" class="site" />
		`;
		$("#site-wrap").append(site2);
		$("#site-wrap .add-site").attr("disabled", "disabled");
		
	};
	var i = 2;
	const addService = () => {
		var addSer = `
		,<input type="text" name="service\${i}" class="service" />:
		<input type="text" name="price\${i}" class="service" />원	<br />
		`;
		$("#service-wrap").append(addSer);
		if(i === 7){
			$(".right-head .add-service").attr("disabled", "disabled");			
		}
		i++;
	};
	<%--
	var a = 2;
	const addDescription = () => {
		var addDes = `
		<div class="thumb"><img src="#" alt="" class="thumb\${a}" style="width:500px;height:250px;"/></div>
		<input type="file" name="file\${a}" class="file" accept="image/*" onchange="contentImg(thumb\${a});" />
		head<input type="text" name="bodyHead\${a}" class="body-head" placeholder="" />
		body<input type="text" name="bodyContent\${a}" class="body-content" placeholder="" /><br />	
		`;
		$("#body-description").append(addDes);
		if(a === 3){
			$("#body-description .add-description").attr("disabled", "disabled");
		}
		a++;
	};
	--%>
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
	geocoder.addressSearch('서울 영등포구 양평로 5 성원빌딩', function(result, status) {
	
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
		} 
	});    
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 