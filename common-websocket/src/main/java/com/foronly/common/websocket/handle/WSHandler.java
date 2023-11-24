package com.foronly.common.websocket.handle;

import com.foronly.common.websocket.WSMessage;
import com.foronly.common.websocket.socket.WebSocketServer;

/**
 * <p>
 * websocket 事件处理
 * </p>
 *
 * @author li_cang_long
 * @since 2023/11/21 11:17
 */
public interface WSHandler {

	void doHandle(WebSocketServer server, WSMessage message);
}
