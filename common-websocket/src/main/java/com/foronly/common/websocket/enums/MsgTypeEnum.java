package com.foronly.common.websocket.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 消息类型枚举
 * </p>
 *
 * @author li_cang_long
 * @since 2023/11/16 16:57
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MsgTypeEnum {
	/**
	 * 1, 心跳检测
	 */
	HEARTBEAT(1, "心跳检测"),
	/**
	 * 2，公告
	 */
	PUB_NOTICE(2, "公告"),
	/**
	 * 3，通用的CRUD操作
	 */
	GENERAL_CRUD(3, "通用的CRUD操作");

	private Integer code;

	private String value;


}
