package com.foronly.common.websocket;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author li_cang_long
 * @since 2023/11/16 16:47
 */
@Data
public class WSMessage implements Serializable {
	private String endpointId;

	private String message;

	private String remark;


}
