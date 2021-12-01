package com.zea.geverytime.chat.ws;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.zea.geverytime.member.model.vo.Member;

// httpsession에 저장된 사용자 정보
public class WebSocketConfig extends Configurator{

	// httpsession접근
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		HttpSession httpSession = (HttpSession)request.getHttpSession();
		Member loginMember = (Member) httpSession.getAttribute("loginMember");
		
		Map<String, Object> userProp = sec.getUserProperties();
		userProp.put("memberId", loginMember.getMemberId());
	}
	
	
}
