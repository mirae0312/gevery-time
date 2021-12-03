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
<% if(loginMember != null && MemberService.BUSINESS_TYPE.equals(loginMember.getMemberType())){ %>
	<button class="info-write-btn" onclick="infoEnroll()">게시글 작성</button>
<% } %>
	<div class="pop-contents">
<% if(popList != null && !popList.isEmpty()){ %>
	<% for(Info popInfo : popList){ %>
		<div class="info-wrap">
			<div class="business-name"><%= popInfo.getBusinessName() %></div>
			<div class="head-content"><%= popInfo.getHeadContent() %></div>
			<img class="list-thumbnail" src="<%= request.getContextPath() %>/upload/info/<%= popInfo.getAttachments().get(0).getRenamedFilename() %>" alt="" />
			<div class="recommend-count"><%= popInfo.getRecommend() %></div>
			<div class="view-count"><%= popInfo.getViewCount() %></div>	
			<div class="hidden-code"><%= popInfo.getCode() %></div>	
		</div>
	<% } %>
<% } %>
	</div>
	<div class="select-contents">
		<select name="location" id="location">
			<option value="">지역</option>
		</select>
	</div>
	<div class="all-contents">
		<div class="info-content">
<% if(list != null && !list.isEmpty()){ %>
	<% for(Info info : list){ %>
			<div class="info-wrap">
				<div class="business-name"><%= info.getBusinessName() %></div>
				<div class="head-content"><%= info.getHeadContent() %></div>
				<img class="list-thumbnail" src="<%= request.getContextPath() %>/upload/info/<%= info.getAttachments().get(0).getRenamedFilename() %>" alt="" />
				<div class="recommend-count"><%= info.getRecommend() %></div>
				<div class="view-count"><%= info.getViewCount() %></div>
				<div class="hidden-code"><%= info.getCode() %></div>
			</div>
	<% } %>
<% } %>
			<hr />
		</div>

	</div>
</div>
<script>
$(".info-wrap").click((e) => {
	const $code = $(e.currentTarget).find('div.hidden-code').text();
	console.log($code);
	
	location.href=`<%= request.getContextPath() %>/info/view?code=\${$code}`;
});

const infoEnroll = () => {
	location.href="<%= request.getContextPath() %>/info/Enroll";
};


var loading = false;
var page = 2;
var pageCheck = "<%= check %>";

const scrollPage = () => {
	$.ajax({
		url: "<%= request.getContextPath() %>/info/scrollList",
		data: {'page':page, 'pageCheck':pageCheck},
		dataType: "json",
		success(data){
			const $data = $(data);
			console.log(data);
			
			const $div = $(".info-content");
			
			$data.each((i, {code, businessName, headContent, attachments, recommend, viewCount}) => {
				
				const $contents = `<div class="info-wrap">
				<div class="business-name">\${businessName}</div>
				<div class="head-content">\${headContent}</div>				
				<img class="list-thumbnail" src="<%= request.getContextPath() %>/upload/info/\${attachments[0].renamedFilename}" alt="" />
				<div class="recommend-count">\${recommend}</div>
				<div class="view-count">\${viewCount}</div>	
				<div class="hidden-code">\${code}</div>
				</div>
				`;
				$div.append($contents);
				$(".info-wrap").click((e) => {
					const $code = $(e.currentTarget).find('div.hidden-code').text();
					console.log($code);
					
					location.href=`<%= request.getContextPath() %>/info/view?code=\${$code}`;
				});
				
				
			});
			$div.append(`<hr />`);
			
			page++;
			
			loading = false;
			if($data.length === 0){
				loading = true;
			}
			console.log("page : " + page);
			console.log(pageCheck);
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