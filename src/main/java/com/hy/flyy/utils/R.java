package com.hy.flyy.utils;

import com.hy.flyy.enums.ResponseCodeEnums;
import lombok.Data;

/**
 * @author 黄勇
 * @since 2023/3/1
 */
@Data
public class R<T> {
    //状态码
    protected int code;

    //响应信息
    protected String message;

    //返回数据
    private T data;

    public R() {
        this.code = ResponseCodeEnums.SUCCESS.getCode();
        this.message = ResponseCodeEnums.SUCCESS.getMessage();
    }

    public R(ResponseCodeEnums statusEnums) {
        this.code = statusEnums.getCode();
        this.message = statusEnums.getMessage();
    }

    /**
     * 若没有数据返回，可以认为指定状态码和提示信息
     *
     * @param code
     * @param msg
     */
    public R(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    /**
     * 有数据返回时，状态码为200，默认提示信息为“操作成功！”
     *
     * @param data
     */
    public R(T data) {
        this.code = ResponseCodeEnums.SUCCESS.getCode();
        this.message = ResponseCodeEnums.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * 有数据返回，状态码为200，人为指定提示信息
     *
     * @param data
     * @param msg
     */
    public R(T data, String msg) {
        this.code = ResponseCodeEnums.SUCCESS.getCode();
        this.data = data;
        this.message = msg;
    }

    public static <T> R<T> success() {
        return new R<>();
    }

    public static <T> R<T> success(T data) {
        return new R<>(data);
    }

    public static <T> R<T> success(T data, String message) {
        return new R<>(data, message);
    }

    /**
     * 默认自定义状态码4000
     * @param message
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(String message) {
        return new R<>(ResponseCodeEnums.CUSTOM.getCode(), message);
    }

    public static <T> R<T> fail(ResponseCodeEnums responseCodeEnums) {
        return new R<>(responseCodeEnums);
    }
}
