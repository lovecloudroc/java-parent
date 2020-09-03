package com.java.commonutils.api;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class APICODE {
//    状态码
    private Integer code;

//    状态中文解释
    private String message;

//    布尔  成功/失败
    private Boolean success;

//    查询数据
    private Map<String, Object> data = new HashMap<String, Object>();

    private APICODE(){

    }

    public static APICODE OK(){
        APICODE apicode = new APICODE();
        apicode.setCode(ResultCode.SUCCESS);
        apicode.setMessage("调用成功");
        apicode.setSuccess(true);
        return apicode;
    }

    public static APICODE ERROR(){
        APICODE apicode = new APICODE();
        apicode.setCode(ResultCode.ERROR);
        apicode.setMessage("服务不可用");
        apicode.setSuccess(false);
        return apicode;
    }

//    成功的信息
    public APICODE success(Boolean success){
        this.setSuccess(success);
        return this;
    }

//    具体的信息
    public APICODE message(String message){
        this.setMessage(message);
        return this;
    }

//    状态的信息
    public APICODE code(Integer code){
        this.setCode(code);
        return this;
    }

//    状态的时间
    public APICODE data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public APICODE data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
