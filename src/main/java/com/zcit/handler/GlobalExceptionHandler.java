package com.zcit.handler;

import com.zcit.common.base.BaseReturn;
import com.zcit.common.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author hifeng
 * @date 2018/8/1 17:47
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseReturn error(Exception e) {
        log.error(e.getMessage(), e);
        return BaseReturn.error("系统维护");
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public BaseReturn error(MyException e) {
        log.error(e.getMessage(), e);
        return BaseReturn.error(e.getCode(), e.getMsg());
    }
}
