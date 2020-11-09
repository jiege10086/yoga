package com.woniu.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult implements Serializable {
    private int code;//200表示正常 非200表示异常
    private String msg;
    private Object data;

    public static JsonResult success(Object data) {
        return success(200,"操作成功",data);
    }

    public static JsonResult success(int code, String msg, Object data) {
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static JsonResult fail(String msg) {
        return fail(400,msg,null);
    }

    public static JsonResult fail(String msg, Object data) {
        return fail(400,msg,data);
    }

    public static JsonResult fail(int code, String msg, Object data) {
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
