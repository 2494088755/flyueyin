package com.hy.flyy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 黄勇
 * @since 2023/3/1
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnums {
    // 信息,表示请求已被实现
    SUCCESS(200, "success"),

    // 客户端错误,请求包含语法错误或无法完成
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),

    // 服务端错误,服务器无法实现合法的请求
    CUSTOM(4000, "自定义状态码"),
    FAIL(500, "server error");


    private final int code;
    private final String message;
}
