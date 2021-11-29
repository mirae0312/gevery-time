<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Info> list = (List<Info>) request.getAttribute("list");
	List<Info> popList = (List<Info>) request.getAttribute("popList");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info.css" />
<div class="info-wrapper">
<%-- if(loginMember != null && "B".equals(loginMember.memberType())) --%>
	<button id="info-write" onclick="infoEnroll()">게시글 작성</button>
<%-- } --%>
	<div class="pop-contents">
<% for(Info popInfo : popList){ %>
			<div class="business-name"><%= popInfo.getBusinessName() %></div>
			<div class="head-content"><%= popInfo.getHeadContent() %></div>
			<div class="list-thumbnail"><%= popInfo.getAttachments().get(0).getRenamedFilename() %></div>
			<div class="recommend-count"><%= popInfo.getRecommend() %></div>
			<div class="view-count"><%= popInfo.getViewCount() %></div>
<% } %>
	</div>
	<div class="select-contents">
		<select name="location" id="location">
			<option value="">지역</option>
		</select>
	</div>
	<div class="all-contents">
		<div class="info-content">
<% for(Info info : list){ %>
			<div class="business-name"><%= info.getBusinessName() %></div>
			<div class="head-content"><%= info.getHeadContent() %></div>
	<%-- if(info.getAttachments() != null && !info.getAttachments().isEmpty()){ --%>
			<div class="list-thumbnail"><%= info.getAttachments().get(0).getRenamedFilename() %></div>
	<%-- } --%>
			<div class="recommend-count"><%= info.getRecommend() %></div>
			<div class="view-count"><%= info.getViewCount() %></div>
<% } %>
			<hr />
		</div>

	</div>
</div>
<script>
const infoEnroll = () => {
	location.href="<%= request.getContextPath() %>/info/Enroll";
};


var loading = false;
var page = 1;

const scrollPage = () => {
	$.ajax({
		url: "<%= request.getContextPath() %>/info/scrollList",
		data: {'page':page},
		dataType: "json",
		success(data){
			const $data = $(data);
			console.log(data);
			
			const $div = $(".info-content");
			
			$data.each((i, {businessName, headContent, attachments, recommend, viewCount}) =>{
				console.log(i,businessName, headContent);
				const $contents = `<div class="head">\${businessName}</div>
				<div class="head-content">\${headContent}</div>
				<div class="list-thumbnail">\${attachments}</div>
				<div class="recommend-count">\${recommend}</div>
				<div class="view-count">\${viewCount}</div>				
				`;
				
				$div.append($contents);
			});
			$div.append(`<hr />`);
			
			page++;
			
			loading = false;
			if($data.length === 0){
				loading = true;
			}
			console.log("page : " + page);
		},
		error: console.log
	});
}

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