<%@page import="com.zea.geverytime.common.model.vo.Attachment"%>
<%@page import="com.zea.geverytime.info.model.vo.InfoEntity"%>
<%@page import="com.zea.geverytime.info.model.vo.Info"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminMain.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/adminInfo.css" />
<h1>관리자 페이지</h1>
<div id="admin-container">
	<ul class="adminBar">
		<li><a href="<%= request.getContextPath() %>/admin/adminList">정보게시물</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/reportList">신고사항</a></li>
	</ul>
</div>
<div id="infoCheck-container">
	<ul class="infoCheck">
		<li>
			<h1>정보게시물 승인여부</h1>
			<table id="state-business-info">
				<thead>
					<tr>
						<th>게시글코드</th>
						<th>게시자</th>
						<th>상호명</th>
						<th>제목</th>
						<th>게시일</th>
						<th>승인상태</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div id="pageBar"></div>
		</li>
	</ul>
</div>
<script>
$(() => {
    infoList(1); // 페이지 로딩했을때 1페이지
}); 
$("#pageBar").click((e) => {
    infoList($(e.target).data('page'));
});
const infoList = (cPage) => {
    console.log(cPage);
    $.ajax({
        url: "<%= request.getContextPath() %>/admin/adminInfoList",
        dataType: "json",
        data: {cPage},
        success(data){
            console.log(data);
            // list 비우기
            $("#state-business-info tbody").empty();
            
            $(data.list).each((i, e) => {                            
                console.log(e.code);
                // 날짜포멧
                const rd = new Date(e.regDate);
                const value = `\${rd.getFullYear()}.\${(rd.getMonth() + 1)}.\${(rd.getDate())}`;
                
                // 상태
                var state = "상태";
                
                if(e.deleteCheck === "A")
                    state = "삭제";
                if(e.regCheck === "O" && e.deleteCheck === "D")
                    state = "보류";
                if(e.regCheck === "I" && e.deleteCheck === "D")
                    state = "게시";             
                
                const tr = `<tr>
                <td>\${e.code}</td>
                <td>\${e.memberId}</td>
                <td><a href="#" target="_self" onclick="window.open('<%= request.getContextPath() %>/admin/check?code=\${e.code}', 
                            '_blank', 'width=500px, height=500px, scrollbars = yes')" >\${e.businessName}</a></td>
                <td>\${e.headContent}</td>
                <td>\${value}</td>
                <td>\${state}</td>
                </tr>`;
                
                console.log($("#state-business-info tbody"));
                
                $("#state-business-info tbody").append(tr);
            }),
            // pagebar
            console.log(data.pagebar);
            $("#pageBar").empty();
            $("#pageBar").append(data.pagebar);
        },
        error:console.log   
    }); 
};
</script> 
<%@ include file="/WEB-INF/views/common/footer.jsp" %>