package com.java.servicebase.hanler;

import com.java.commonutils.api.APICODE;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局自定义异常数据统一格式返回
 */
@ControllerAdvice//通知
public class GlobalExceptionHandler {

    @ResponseBody//转换为Json
    @ExceptionHandler(Exception.class)//只要遇到Exception就去找Exception.class里响应的方法
    public APICODE errorHandler(Exception e){
        return APICODE.ERROR().message("Exception:服务器错误");//返回错误信息
    }


    @ResponseBody
    @ExceptionHandler(HctfException.class)
    public APICODE errorHandler(HctfException e){
        e.printStackTrace();
        return APICODE.ERROR().message(e.getMsg());
    }

}
