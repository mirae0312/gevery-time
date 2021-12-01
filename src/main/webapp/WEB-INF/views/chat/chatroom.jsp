<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
// 접속한 주소
const host = location.host;
const ws = new WebSocket(`ws://\${host}<%= request.getContextPath() %>/chat/websocket`);
// 이벤트 핸들러
ws.onopen = (e) => {
	console.log("open", e);
};
ws.onmessage = (e) => {
	console.log("message", e);
	const {data} = e;
	const {type, msg, sender, time} = JSON.parse(data);
	console.log(type, msg, sender, time);
	
	let html = "";
	switch(type){
	case "welcome":
	case "bye": html = `<li class="center"><span class="badge">\${sender}</span>\${msg}</li>`; break;
	case "msg": html = `<li><span class="badge">\${sender}</span>\${msg}</li>`; break;
	}
	$("#msg-container ul").append(html);
	
	// 스크롤처리
	const $msgContainer = $("#msg-container")
	const scrollHeight = $msgContainer.prop("scrollHeight");
	$msgContainer.scrollTop(scrollHeight);
};
ws.onerror = (e) => {
	console.log("error", e);
};
ws.onclose = (e) => {
	console.log("close", e);
};

// send message 전송
$(() => {
	$(send).click((e) => {
		const o = {
			type: "msg",
			sender: "<%= loginMember.getMemberId() %>",
			msg: $(msg).val(),
			time: Date.now()
		};
		
		// 메세지 전송
		ws.send(JSON.stringify(o));
		
		// #msg 초기화
		$(msg).val('').focus();
		
	});
	
	// enter send
	$(msg).keyup((e) => {
		if(e.key === 'Enter')
			$(send).trigger("click");
	});
	
});
</script>
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/css/chat.css" /> --%>
	<section id="chat-container">	
		<h2>실시간 채팅</h2>
		<button type="button" id="btn-userList">현재사용자확인</button>
		<div id="msg-container">
			<ul></ul>
		</div>
		
		<div id="msg-editor" class="editor">
			<textarea name="" id="msg" cols="30" rows="2"></textarea>
			<button type="button" id="send">Send</button>
		</div>
	</section>
	


<%@ include file="/WEB-INF/views/common/footer.jsp" %>