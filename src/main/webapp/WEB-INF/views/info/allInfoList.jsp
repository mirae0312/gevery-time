<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info.css" />
<div class="wrapper">
	<div class="pop-content">추천순</div>
	<div class="select-content">
		<select name="location" id="location">
			<option value="">지역</option>
		</select>
	</div>
	<div class="all-content">모든 게시물</div>
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
			
			$(data).each((i, {businessName, headContent, renamedFilename}) =>{
				
			});
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