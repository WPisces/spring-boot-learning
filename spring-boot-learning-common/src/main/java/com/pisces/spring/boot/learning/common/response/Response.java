package com.pisces.spring.boot.learning.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pisces.spring.boot.learning.common.exception.ErrorType;

import java.io.Serializable;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 16:28 2020/3/3
 * @Modified By:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public Response() {
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> error() {
        return error((String)null);
    }

    public static <T> Response<T> error(String errorMsg) {
        return new Response(ErrorType.SYSTEM_ERROR.getCode(), errorMsg == null ? ErrorType.SYSTEM_ERROR.getMsg() : errorMsg, (Object)null);
    }

    public static <T> Response error(ErrorType errorType) {
        if (errorType == null) {
            throw new IllegalArgumentException("errorType");
        } else if (errorType.getCode() == ErrorType.SUCCESS.getCode()) {
            throw new IllegalArgumentException("errorCode should != 200 when call error()!");
        } else {
            int errorCode = errorType.getCode();
            String errorMsg = errorType.getMsg();
            Object context = null;
            if (errorType instanceof ErrorType.Default) {
                context = ((ErrorType.Default)errorType).getContext();
            }
            return new Response(errorCode, errorMsg, context);
        }
    }

    public static <T> Response<T> success(T data) {
        return new Response(ErrorType.SUCCESS.getCode(), ErrorType.SUCCESS.getMsg(), data);
    }

    public static <T> Response<T> success() {
        return new Response(ErrorType.SUCCESS.getCode(), ErrorType.SUCCESS.getMsg(), (Object)null);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == ErrorType.SUCCESS.getCode();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "Response(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }
}

