package com.foronly.common.websocket;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * <p>
 * 消息解析
 * </p>
 *
 * @author li_cang_long
 * @since 2023/11/16 17:05
 */
@Slf4j
public class WsEncoder implements Encoder.Text<WSMessage> {
	public String encode(WSMessage wsMessage) throws EncodeException {
		return JSON.toJSONString(wsMessage);
	}

	public void init(EndpointConfig endpointConfig) {

	}

	public void destroy() {

	}
}
