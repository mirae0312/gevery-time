<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Info> list = (List<Info>) request.getAttribute("list");
	List<Info> popList = (List<Info>) request.getAttribute("popList");
	String check = (String) request.getAttribute("check");	
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info/info.css" />
<div class="info-wrapper">
	<div class="go-up-btn btn">위로</div>
<% if(loginMember != null && MemberService.BUSINESS_TYPE.equals(loginMember.getMemberType())){ %>
	<button class="info-write-btn btn" onclick="infoEnroll()">게시글<br />작성</button>
<% } %>
	<div class="pop-contents">
<% if(popList != null && !popList.isEmpty()){ %>
	<% for(Info popInfo : popList){ %>
		<div class="info-wrap">
			<div class="business-name"><%= popInfo.getBusinessName() %></div>
			<img class="list-thumbnail" src="<%= request.getContextPath() %>/upload/info/<%= popInfo.getAttachments().get(0).getRenamedFilename() %>" alt="" />
			<div class="head-content"><%= popInfo.getHeadContent() %></div><br />
			<div class="recommend-count">추천수 : <%= popInfo.getRecommend() %></div>
			<div class="view-count">조회수 : <%= popInfo.getViewCount() %></div>	
			<div class="popInfo-comment">리뷰 : <%= popInfo.getCommentCount() %></div>
			<div class="hidden-code"><%= popInfo.getCode() %></div>	
		</div>
	<% } %>
<% } %>
	</div>
	<div class="select-contents">
		<div class="select-wrapper">
			<select name="location" id="location">
				<option value="">지역</option>
			</select>
			<select name="lining" class="lining">
				<option value="new">정렬</option>
				<option value="old">등록순</option>
				<option value="new">최신순</option>
				<option value="view">방문순</option>
				<option value="like">추천순</option>
			</select>
		</div>
	</div>
	<div class="all-contents">
		<div class="info-content">
		</div>

	</div>
</div>
<form action=""></form>
<script>
// 시작시 ajax실행
$(() => {
	scrollPage();
});

$(".go-up-btn").click((e) => {
	$('html, body').animate({scrollTop:0}, 300);
});

// 게시물 상세보기 용
$(".info-wrap").click((e) => {
	const $code = $(e.currentTarget).find('div.hidden-code').text();
	console.log($code);
	
	location.href=`<%= request.getContextPath() %>/info/view?code=\${$code}`;
});

// 게시물 등록
const infoEnroll = () => {
	location.href="<%= request.getContextPath() %>/info/Enroll";
};

// ajax data
var loading = false;
var page = 1;
var pageCheck = "<%= check %>";
var n = "new";

// select 값이 변하면 페이지 비우고 ajax 재시작
$(".lining").change((e) => {
	$(".info-content").empty();	
	page = 1;
	n = $(".lining").val();
	scrollPage();
});


const scrollPage = () => {	
	
	$.ajax({
		url: "<%= request.getContextPath() %>/info/scrollList",
		data: {'page':page, 'pageCheck':pageCheck, 'n':n},
		dataType: "json",
		success(data){
			const $data = $(data);
			//console.log(data);
			
			const $div = $(".info-content");
			
			$data.each((i, {code, businessName, headContent, attachments, recommend, viewCount, regDate, commentCount}) => {
				
				let rd = new Date(regDate);
				let value = `\${rd.getFullYear()}.\${(rd.getMonth() + 1)}.\${(rd.getDate())}`;
				//console.log(value);
				const $contents = `<div class="info-wrap">
				<div class="business-name">\${businessName}</div>
				<div class="head-content">\${headContent}</div>				
				<img class="list-thumbnail" src="<%= request.getContextPath() %>/upload/info/\${attachments[0].renamedFilename}" alt="" /><br />
				<div class="recommend-count">추천수 : \${recommend}</div>
				<div class="view-count">조회수 : \${viewCount}</div>	
				<div class="info-comment-count">리뷰 : \${commentCount}</div>
				<div class="info-reg-date">\${value}</div>
				<div class="hidden-code">\${code}</div>
				</div><hr />
				`;
				$div.append($contents);
				$(".info-wrap").click((e) => {
					const $code = $(e.currentTarget).find('div.hidden-code').text();
					//console.log($code);
					
					location.href=`<%= request.getContextPath() %>/info/view?code=\${$code}`;
				});				
				
			});
			
			page++;
			
			loading = false;
			if($data.length === 0){
				loading = true;
			}
			console.log("page : " + page);
			//console.log(pageCheck);
		},
		error: console.log
	});		

}

// scroll 위치지정 및 ajax실행
$(window).scroll(function(){
	if($(window).scrollTop() + 10 >= $(document).height() - $(window).height()){
		if(!loading){
			loading = true;
			scrollPage();
		}
	}
});


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 