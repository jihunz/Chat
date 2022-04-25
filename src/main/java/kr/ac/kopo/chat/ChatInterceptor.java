package kr.ac.kopo.chat;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class ChatInterceptor extends HttpSessionHandshakeInterceptor {

	// handshake 이전만 처리
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		// 아래의 request는 ServerHttpRequest -> IP
		String host = request.getRemoteAddress().getHostName();
		
		attributes.put("user", host);
		
		return true;
		
		// return super.beforeHandshake(request, response, wsHandler, attributes);
	}
}