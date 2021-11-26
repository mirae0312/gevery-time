<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Info> list = (List<Info>) request.getAttribute("list");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info.css" />
<div class="info-wrapper">
	<button id="info-write">게시글 작성</button>
	<div class="pop-content">추천순</div>
	<div class="select-content">
		<select name="location" id="location">
			<option value="">지역</option>
		</select>
	</div>
	<div class="all-content">
		<div class="info-content">
<% for(Info info : list){ %>
			<div class="head"><%= info.getBusinessName() %></div>
			<div class="head-content"><%= info.getHeadContent() %></div>
			<div class="body"><%= info.getBodyContents() %></div>
<% } %>
			<hr />
		</div>

	</div>
</div>
<script>



var loading = false;
var page = 1;

const scrollPage = () => {
	$.ajax({
		url: "<%= request.getContextPath() %>/info/scrollList",
		data: {'page':page},
		dataType: "json",
		success(data){
			console.log(data);
			const $div = $(".info-content");
			
			$(data).each((i, {businessName, headContent, bodyContents}) =>{
				console.log(i,businessName, headContent, bodyContents);
				const $contents = `<div class="head">\${businessName}</div>
				<div class="head-content">\${headContent}</div>
				<div class="body">\${bodyContents}</div>
				<hr />
				`;
				
				$div.append($contents);
			});
			
			page++;
			loading = false;
			console.log("page : " + page);
		},
		error: console.log
	});
}

$(window).scroll(function(){
	if($(window).scrollTop() + 200 >= $(document).height() - $(window).height()){
		if(!loading){
			loading = true;
			scrollPage();
		}
	}
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 