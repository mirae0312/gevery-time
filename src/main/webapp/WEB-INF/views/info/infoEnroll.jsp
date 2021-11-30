<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Info info = (Info) request.getAttribute("info");
%>
<div class="enroll-wrapper">
	<form name="infoEnrollFrm" action="<%= request.getContextPath() %>/info/Enroll" method="post">
		
		<div class="left-head">
			<input type="text" name="businessName" id="business-name" />
			<img src="" alt="" />
			<input type="file" name="headFile" id="head-file" />
			<input type="text" name="headContent" id="head-content" />		
		</div>
		<div class="right-head">
			<label for="tel">전화번호</label>
			<input type="text" name="tel1" id="tel" />
			<input type="text" name="tel2" id="tel" />
			<input type="text" name="tel3" id="tel" />
			<label for="addr">주소</label>
			<input type="text" name="addr" id="addr" />
			영업시간
			<input type="text" name="sat" id="business-hours" />
			<input type="text" name="sun" id="business-hours" />
			<input type="text" name="mon" id="business-hours" />
			<input type="text" name="tue" id="business-hours" />
			<input type="text" name="wed" id="business-hours" />
			<input type="text" name="thu" id="business-hours" />
			<input type="text" name="fri" id="business-hours" />
			<input type="text" name="launch" id="business-hours" />
			<input type="text" name="dinner" id="business-hours" />
			<select name="holiday">
				<option value="mon" id="holiday">월</option>
				<option value="tue" id="holiday">화</option>
				<option value="wed" id="holiday">수</option>
				<option value="thu" id="holiday">목</option>
				<option value="fri" id="holiday">금</option>
				<option value="sat" id="holiday">토</option>
				<option value="sun" id="holiday">일</option>
			</select>
			<label for="site">사이트</label>
			<input type="text" name="site1" id="site" />
			<label for="service">가격표</label>
			<input type="text" name="service1" id="service" />
		</div>
		<div class="body-description">
			<input type="file" name="file2" id="file" />
			<input type="text" name="bodyHead1" id="body-head" placeholder="" />
			<input type="text" name="bodyContent1" id="body-content" placeholder="" />
			
		</div>
		<div class="the-way">
			<div id="map" style="width:500px;height:400px;"></div>
			오시는길
			<textarea name="way1" id="way" cols="30" rows="10"></textarea>
		</div>
		<input type="submit" />
	</form>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a98891f9d7d85bc941b2188e046c3bfb"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
<script>
<%--
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3, // 지도의 확대 레벨
	        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
	    }; 
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(<%= info.getBusinessAddress() %>, function(result, status) {

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
--%>	
	<%--
	const readImage = (input) => {
		if(input.files && input.files[0]){
			const reader = new FileReader();
			
			reader.onload = (e) => {
				const previewImage = document.getElementById("head-file");
				previewImage.src = e.target.result;
			}
			reader.readAsDataURL(input.files[0]);
		}
	};
	const inputImage = document.getElementById("in")
	--%>
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 