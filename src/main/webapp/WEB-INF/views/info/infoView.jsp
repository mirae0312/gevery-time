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
	String recommend = (String) request.getAttribute("recommend");
	List<InfoReview> ir = (List<InfoReview>) request.getAttribute("ir");
	
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
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info/info.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info/infoView.css" />
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4247f28f0dc06c5cc8486ac837d411ff&libraries=services,clusterer,drawing"></script>
<div class="info-view-wrapper">
<% if(loginMember != null && info.getMemberId().equals(loginMember.getMemberId())){ %>
	<form enctype="multipart/form-data" name="infoBoardModifyFrm" >
		<input type="hidden" name="code" value="<%= info.getCode() %>" />
		<input type="hidden" name="codeN" value="<%= codeN %>" />
		<input type="hidden" name="id" value="<%= info.getMemberId() %>" />
		<input type="button" class="btn" value="수정" onclick="modifyInfoMain();" />
		<input type="button" class="btn" value="삭제" onclick="deleteInfoMain();" />
	</form>
<% } %>
<% if(loginMember != null && !info.getMemberId().equals(loginMember.getMemberId())){ %>
	<input type="button" value="신고" class="reportInfoMain btn" />
<% } %>
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
		<div id="map" style="width:500px;height:400px;"></div><br />
		<%-- 길안내 --%>
		<div class="way-content">
			<h1>오시는 길</h1>
			<p><%= info.getRoadGuide() %></p>
		</div>
	</div>
	<%-- 좋아요 --%>
	<input type="checkbox" name="like" id="info-like" <%= "G".equals(recommend) ? "checked" : "" %> />
	<label for="info-like">좋아요</label><hr />
	<div class="info-review-wrapper">
<%-- 리뷰 그리고 신고 --%>
<% if(ir != null && !ir.isEmpty()){ %>
	<% for(int i = 0; i < ir.size(); i++){ %>
		<div class="view-control-review">
			<form action="" class="review" name="infoBoardReviewFrm" method="POST" enctype="multipart/form-data">
				<div class="info-review">
					<input type="hidden" name="pCode" value="<%= info.getCode() %>" />
					<input type="hidden" name="reviewCode" class="reviewCode" value="<%= ir.get(i).getrCode() %>" />
					<div class="review-writer"><%= ir.get(i).getMemberId() %></div>
					<div class="review-head"><%= ir.get(i).getHeadContent() %></div>
		<% if(ir.get(i).getAttachments() != null && !ir.get(i).getAttachments().isEmpty()){ %>
			<% for(int j = 0; j < ir.get(i).getAttachments().size(); j++){ %>
				<% if(ir.get(i).getrCode().equals(ir.get(i).getAttachments().get(j).getCode())){ %>
					<input type="hidden" name="attachName<%= i %>" value="<%= ir.get(i).getAttachments().get(j).getRenamedFilename() %>" />
					<img class="reviewPictures" src="<%= request.getContextPath() %>/upload/info/<%= ir.get(i).getAttachments().get(j).getRenamedFilename() %>" alt="" />
				<% } %>
			<% } %>
		<% } %>
					<div class="review-content"><%= ir.get(i).getContent() %></div>
					<div class="review-reg-date"><%= ir.get(i).getRegDate() %></div>
					<table id= reBox></table>
				<%-- 리뷰 수정: info-review클릭 --%>
				</div>
		<% if(loginMember != null && loginMember.getMemberId().equals(ir.get(i).getMemberId())){ %>
			<% if(!loginMember.getMemberId().equals(ir.get(i).getMemberId())){ %>
					<input type="button" value="신고" class="reivew-report review-btn btn" onclick="reportReview();" />
			<% }else{ %>
					<input type="button" value="삭제" class="delete-review review-btn btn" onclick="deleteReview();" />
			<% } %>
		<% } %>	
			</form>
		</div>				
	<% } %>
<% } %>
<%-- 리뷰 작성 --%>
<% if(loginMember != null && MemberService.USER_ROLE.equals(loginMember.getMemberRole()) && !info.getMemberId().equals(loginMember.getMemberId())){ %>
		<form action="<%= request.getContextPath() %>/info/insertReview"
			name="reviewEnrollFrm"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="categoryNo" value="<%= codeN %>" />
			<input type="hidden" name="infoWriter" value="<%= loginMember.getMemberId() %>" />
			<input type="hidden" name="infoCode" value="<%= info.getCode() %>" />
			<input type="text" name="headContent" required />
			<div class="reviewPreviewBox">
				<img class="reviewPicPre rPic1" />
				<img class="reviewPicPre rPic2" />
			</div>
			<input type="file" name="reviewPic1" accept="image/*" onchange="previewF1();"/>
			<input type="file" name="reviewPic2" accept="image/*" onchange="previewF2();"/>
			<textarea name="bodyContent" id="writeReview" cols="30" rows="10" required></textarea>
			<button class="review-enroll-btn btn">등록</button>
		</form>
<% } %>
	</div>
</div>
<script>
// 본문,리뷰 수정 삭제 신고용 폼
const $frm = $(document.infoBoardReviewFrm);
const $mFrm = $(document.infoBoardModifyFrm);

//리뷰신고
const reportReview = () => {
	const name = "report-review";
	const spec = "left=500px, top=500px, width=300px, height=250px";
	const popup = open("", name, spec);
	
	const $frm = $(document.reviewReportFrm);
	$frm.find
};

// 본문 수정
const modifyInfoMain = () => {
	$mFrm.attr("action", "<%= request.getContextPath() %>/info/modifyMain")
		.submit();
}
// 본문 삭제
const deleteInfoMain = () => {
	const check = confirm("정말 삭제하시나요?");
	if(check){
		$mFrm.attr("action", "<%= request.getContextPath() %>/info/deleteMain")
			.submit();
	}
};
// 리뷰 삭제
const deleteReview = () => {
	const check = confirm("정말 삭제하시나요?");
	if(check){
		$frm.attr("action", "<%= request.getContextPath() %>/info/deleteReview")
			.submit();
	}	
};
// 리뷰 수정
const modifyReviewBox = () => {
	if($(".mHead").val() != "" && $(".mBody").val() != ""){
		$frm.attr("action", "<%= request.getContextPath() %>/info/reviewModify")
			.submit();		
	}else{
		alert("제목과 내용을 입력하세요.");
	}
};
<% if(loginMember != null){ %>
$(".info-review").one("click", function(event){
	
	const $btn = $(event.currentTarget).find('div.review-writer').text();
	const $reBox = $("#reBox");
	const $box = `
	<tr>
	<td><input type="file" name="mPic1" accept="image/*" onchange="previewF3();"/></td>
	<td><input type="file" name="mPic2" accept="image/*" onchange="previewF4();"/></td>
	</tr>
	<tr>
	<td colspan="2"><input type="text" class="mHead" name="mHead" /></td>
	</tr>
	<tr>
	<td><textarea name="mBody" class="mBody" cols="30" rows="10"></textarea></td>
	</tr>	
	<tr>
	<td><input type="button" value="수정" class="modify-review review-btn btn" onclick="modifyReviewBox();" /></td>
	</tr>
	`;
	
	if($btn == '<%= loginMember.getMemberId() %>'){
		$reBox.append($box);		
	}
	$('.mBody').summernote({
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
<% } %>

// 리뷰등록하기 사진 미리보기
const previewF1 = () => {
	const reader = new FileReader();
	
	reader.onload = function(event){
		$(".rPic1").attr("src", event.target.result);
	}
	reader.readAsDataURL(event.target.files[0]);
};
const previewF2 = () => {
	const reader = new FileReader();
	
	reader.onload = function(event){
		$(".rPic2").attr("src", event.target.result);
	}
	reader.readAsDataURL(event.target.files[0]);
};

// 좋아요
$("#info-like").change((e) => {
<% if(loginMember != null && MemberService.USER_ROLE.equals(loginMember.getMemberRole())){ %>
	if($("#info-like").is(":checked") == true){
		console.log("체크된 상태");
		$.ajax({
			url: "<%= request.getContextPath() %>/info/likeCount",
			data: {
				state : "G",
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
			data:{
				state : "B",
				code: "<%= info.getCode() %>",
				memberId: "<%= loginMember.getMemberId() %>"
			},
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