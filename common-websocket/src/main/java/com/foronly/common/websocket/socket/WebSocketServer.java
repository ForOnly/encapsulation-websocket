package com.foronly.common.websocket.socket;

import com.foronly.common.websocket.WSMessage;
import com.foronly.common.websocket.WsDecoder;
import com.foronly.common.websocket.WsEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author li_cang_long
 * @since 2023/11/15 11:45
 */
@Slf4j
@Component
@ServerEndpoint(value = "/ws/{endpointId}", decoders = WsDecoder.class, encoders = WsEncoder.class)
public class WebSocketServer {

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Map，用来存放每个客户端对应的MyWebSocket对象。
	private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<String, WebSocketServer>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 建立连接
	 *
	 * @param session
	 * @param endpointId
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("endpointId") String endpointId) {
		this.session = session;
		webSocketMap.put(endpointId, this);
		addOnlineCount(); // 在线数加1
		log.info("有新连接加入,endpointId:{}！当前在线人数为:{}", endpointId, getOnlineCount());
		try {
			WSMessage wsMessage = new WSMessage();
			wsMessage.setEndpointId(endpointId);
			wsMessage.setRemark("连接成功");
			sendMessage(wsMessage);
		} catch (Exception e) {
			log.error("websocket IO异常");
		}
	}

	/**
	 * 断开连接
	 *
	 * @param endpointId
	 */
	@OnClose
	public void onClose(@PathParam("endpointId") String endpointId) {

		webSocketMap.remove(endpointId); // 从set中删除
		subOnlineCount(); // 在线数减1
		log.info("websocket关闭，endpointId：{},当前在线人数为:{}", endpointId, getOnlineCount());
	}

	/**
	 * @param error
	 * @param endpointId
	 */
	@OnError
	public void onError(Throwable error, @PathParam("endpointId") String endpointId) {
		log.error("webSocket发生错误！endpointId：{}", endpointId);
		error.printStackTrace();
	}


	/**
	 * 接收客户端的消息
	 *
	 * @param endpointId
	 */
	@OnMessage
	public void onMessage(@PathParam("endpointId") String endpointId, WSMessage wsMessage) {
		this.handleMessage(wsMessage);
	}


	public void sendMessage(String message) throws EncodeException, IOException {
		// log.info("服务端给指定客户端：{}，连接会话：{}，发送消息{}", message.getClientId(), this.session.getId(), message);
		synchronized (this.session) {
			this.session.getBasicRemote().sendObject(message);
		}
	}

	public void sendMessage(WSMessage message) throws EncodeException, IOException {
		// log.info("服务端给指定客户端：{}，连接会话：{}，发送消息{}", message.getClientId(), this.session.getId(), message);
		synchronized (this.session) {
			this.session.getBasicRemote().sendObject(message);
		}
	}

	private void handleMessage(WSMessage wsMessage) {

	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}

	public static ConcurrentHashMap<String, WebSocketServer> getWebSocketMap() {
		return webSocketMap;
	}
}
