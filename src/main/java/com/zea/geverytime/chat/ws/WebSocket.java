package com.zea.geverytime.chat.ws;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(value="/chat/websocket", configurator = WebSocketConfig.class)
public class WebSocket {
	
	public static Map<String, Session> clients = new HashMap<>();
	
	public void clientsLog() {
		System.out.println("현재접속자수(" + clients.size() + ")" + clients.keySet());
	}
	
	public String msgToJson(String type, String msg, String sender) {
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("msg", msg);
		map.put("sender", sender);
		map.put("time", System.currentTimeMillis());
		return new Gson().toJson(map);
	}
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		System.out.print("[open] ");
		Map<String, Object> userProp = config.getUserProperties();
		String memberId = (String)userProp.get("memberId");
		clients.put(memberId, session);
		clientsLog();
		
		// close msg 
		Map<String, Object> sessionUserProp = session.getUserProperties();
		sessionUserProp.put("memberId", memberId);
		
		// 입장메세지 전송	
		String msg = msgToJson("welcome", "님이 입장했습니다.", memberId);
		onMessage(msg, session);
	}
	
	@OnMessage
	public void onMessage(String msg, Session session) {
		System.out.print("[message] ");
		System.out.println(msg);
		
		// 메세지전송
		synchronized(clients) {
			Collection<Session> sessionList = clients.values();
			for(Session ses : sessionList) {
				Basic basic = ses.getBasicRemote();
				try {
					basic.sendText(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	
	// 사용자가 페이지이동, 새로고침, 종료를 하면 채팅종료
	@OnClose
	public void onClose(Session session) {
		System.out.print("[close] ");
		// clients에서 사용자 session제거
		Map<String, Object> sessionUserProp = session.getUserProperties();
		String memberId = (String) sessionUserProp.get("memberId");
		clients.remove(memberId);
		clientsLog();
		
		// 다른 사용자에게 나간 사용자 알림
		String msg = msgToJson("bye", "님이 퇴장했습니다.", memberId);
		onMessage(msg, session);
	}
	
}
