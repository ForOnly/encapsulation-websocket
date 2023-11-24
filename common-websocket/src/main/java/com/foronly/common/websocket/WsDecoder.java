package com.foronly.common.websocket;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
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
public class WsDecoder implements Decoder.Text<WSMessage> {
	public WSMessage decode(String s) throws DecodeException {
		log.info("解析信息：{}", s);

		return JSON.to(WSMessage.class, JSON.parseObject(s));
	}

	public boolean willDecode(String s) {
		try {
			log.info("解析信息：{}", s);
			JSON.to(WSMessage.class, JSON.parseObject(s));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("客户端发送消息到服务端，数据解析失败！");
			return false;
		}


	}

	public void init(EndpointConfig endpointConfig) {

	}

	public void destroy() {

	}
}
