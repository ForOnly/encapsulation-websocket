package com.foronly.common.websocket.config;

import com.foronly.common.websocket.socket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <p>
 *
 * </p>
 *
 * @author li_cang_long
 * @since 2023/11/15 13:40
 */
@Slf4j
@Configuration
public class WebSocketConfig {


	/**
	 * ServerEndpointExporter 作用
	 * <p>
	 * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
	 *
	 * @return ServerEndpointExporter
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		log.info("加载 ServerEndpointExporter");
		return new ServerEndpointExporter();
	}

	@Bean
	public WebSocketServer webSocketServer() {
		return new WebSocketServer();
	}
}
