package com.hy.flyy.exception;

/**
 * @author 黄勇
 * @since 2023/4/27
 */
public class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }
}
