<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/slide.css" />
<div class="body-wrapper">
	
	<%-- 사진영역 --%>
	<h1>사진</h1>
	<div class="photo-wrapper">
		<div class="slide">
			<div class="pics"><img src="<%= request.getContextPath() %>/images/info/고양이.jpg" alt="" /></div>
			<div class="pics"><img src="<%= request.getContextPath() %>/images/info/강아지.png" alt="" /></div>
			<div class="pics"><img src="<%= request.getContextPath() %>/images/info/고양이.jpg" alt="" /></div>
		</div>
	</div><hr />
	
	<%-- 게시글영역 --%>
	<h1>게시글</h1>
	<div class="board-wrapper">
		<div class="board-left-wrap">
		  <table>
		  <thead>
		  	<tr>
		  		<th colspan="2">자유게시판</th>
		  	</tr>
		  	<tr></tr>
		  </thead>
		  <tbody>
		  </tbody>
		  </table>
		</div>
		<div class="board-right-wrap">
		<table>
		<thead>
		  	<tr>
		  		<th colspan="2">리뷰게시판</th>
		  	</tr>
		  	<tr></tr>
		  </thead>
		  <tbody>
		  </tbody>
		  </table>
		</div>
	</div><hr />
	
	<%-- 인포영역 --%>
	<h1>한 주간의 인기 SPOT</h1>
	<div class="info-board-wrapper">
		<div id="info-board-table">			
		</div>			
	</div><hr />
	
	<%-- 마켓영역 --%>
	<h1>마켓</h1>
	<div class="market-wrapper">
		<table id="market-board-table">
			<tr>
				<td><a href="<%= request.getContextPath() %>/product/main?div=dog">강아지 상품 보러가기</a></td>
				<td><a href="<%= request.getContextPath() %>/product/main?div=cat">고양이 상품 보러가기</a></td>
				<td><a href="<%= request.getContextPath() %>/product/main?div=goose">거위 상품 보러가기</a></td>
			</tr>
		</table>
	</div><br />
</div>
<script>
// slide를 위한 slick
$('.slide').slick({
	infinite: true, // 마지막 슬라이드 다음 처음 슬라이드
	slidesToShow: 1, // 보일 페이지 수
	arrow: false, 
	autoplay: true, // 자동 넘기기
	autoplaySpeed:3000 // 자동넘기기 시간
});
$(()=>{	
	getBoardPopularList();
	console.log("hi");
	mainInfoLists();
});
const f = function(n){
    return n<10 ? `0\${n}`:n; 
}
const getBoardPopularList = () => {
	$.ajax({
		url:"<%=request.getContextPath()%>/common/mainBoardPopularList",
		success(data){
			console.log(data);
			console.log(data.freeList);
			$(".board-left-wrap table tbody").empty();
			$(".board-right-wrap table tbody").empty();
			$(data.freeList).each((i,e)=>{
				const d = new Date(e.regDate);
				const date = `\${f(d.getMonth())}-\${f(d.getDate())}`
				let commentCount = "";
				if(e.commentCount>0){
					console.log(e.commentCount);
					commentCount = "<span class='comment-count'>("+e.commentCount+')</span>';
					console.log(commentCount);
				}
				const tr = `			<tr>
 					<td class='title'><a href="<%=request.getContextPath()%>/board/boardView?no=\${e.no}">\${e.title}</a> \${commentCount}</td>
					<td>\${date}</td>
				</tr>`
				$(".board-left-wrap table tbody").append(tr);
				
			})
			$(data.reviewList).each((i,e)=>{
				const d = new Date(e.regDate);
				const date = `\${f(d.getMonth())}-\${f(d.getDate())}`
				let commentCount = "";
				if(e.commentCount>0){
					console.log(e.commentCount);
					commentCount = "<span class='comment-count'>("+e.commentCount+')</span>';
					console.log(commentCount);
				}
				const tr = `			<tr>
 					<td class='title'><a href="<%=request.getContextPath()%>/board/boardView?no=\${e.no}">\${e.title}</a> \${commentCount}</td>
					<td>\${date}</td>
				</tr>`
				$(".board-right-wrap table tbody").append(tr);
				
			})

		},
		error:console.log
	});
};

const mainInfoLists = () => {
	$.ajax({
		url:"<%= request.getContextPath() %>/main/infoList",
		data:{"board":"info"},
		dataType:"json",
		success(data){
			//console.log(data);
			
			const $box = $("#info-board-table");
			$box.empty();
			$(data).each((i, e) => {
				//console.log(e.businessName);
				const contents = `
				<div class="content">
				<div class="info-img-wrap">
				<img class="info-img" src="<%= request.getContextPath() %>/upload/info/\${e.attachments[0].renamedFilename}" alt="" />
				</div>
				<div class="info-bname">\${e.businessName}</div>
				<div class="hidden-code">\${e.code}</div>	
				</div>
				`;
				$box.append(contents);
			});
			
			$(".content").click((e) => {
				const $code = $(e.currentTarget).find('div.hidden-code').text();
				//console.log($code);
				
				location.href=`<%= request.getContextPath() %>/info/view?code=\${$code}`;
			});
			
		},
		error:console.log
	});
};
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>