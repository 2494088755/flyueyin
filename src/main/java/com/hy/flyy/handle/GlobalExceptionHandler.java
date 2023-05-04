package com.hy.flyy.handle;

import com.hy.flyy.enums.ResponseCodeEnums;
import com.hy.flyy.exception.ServerException;
import com.hy.flyy.utils.R;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 黄勇
 * @since 2023/4/26
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public R nullPointExceptionHandle(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.fail("空指针异常，请检查参数是否完整");
    }

    @ExceptionHandler(IOException.class)
    public R IOExceptionHandle(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.fail("服务器异常");
    }

    @ExceptionHandler(ServerException.class)
    public R serverExceptionHandle(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.fail("服务器异常");
    }

    @ExceptionHandler(SignatureException.class)
    public R signatureExceptionHandle(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.fail(ResponseCodeEnums.UNAUTHORIZED);
    }

}
