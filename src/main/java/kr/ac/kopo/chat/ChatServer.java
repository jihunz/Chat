package kr.ac.kopo.chat;

import java.util.Iterator;
import java.util.Vector;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatServer extends TextWebSocketHandler {
	// Vector 사용 -> 스레드 안정성
	Vector<WebSocketSession> list;
	
	public ChatServer() {
		super();
		
		// Vector 초기화
		if(list == null)
			list = new Vector<WebSocketSession>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		list.add(session);
		
		System.out.println("연결" + session.getRemoteAddress());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		
		// 접속자의 IPv6 주소와 랜덤한 포트번호를 가지고 옴
		String user = (String) session.getAttributes().get("user");
		
		System.out.println("메시지: " + message.getPayload() + ", " + user);
		
		for(WebSocketSession client : list) {
			// 발신자와 관계 없이 모두에게 메시지 전달 -> echo
			client.sendMessage(new TextMessage(message.getPayload()));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		
		list.remove(session);
		
		System.out.println("종료: " + session.getRemoteAddress());
		
	}

}
