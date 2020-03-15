package com.pisces.spring.boot.learning.common.exception;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 16:31 2020/3/3
 * @Modified By:
 */
public interface ErrorType {
    ErrorType SUCCESS = new ErrorType.Default(200, "响应成功");
    ErrorType UNAUTHORIZED = new ErrorType.Default(401, "请求未授权");
    ErrorType SYSTEM_ERROR = new ErrorType.Default(500, "系统内部错误");
    ErrorType PARAM_REQUIRED = new ErrorType.Default(600, "缺少必要参数[%s]");
    ErrorType INVALID_PARAM = new ErrorType.Default(602, "参数错误[%s]");
    ErrorType ILLEGAL_PAGE_SIZE = new ErrorType.Default(610, "分页参数非法");

    int getCode();

    String getMsg();

    default boolean is(ErrorType other) {
        if (other == null) {
            return false;
        } else {
            return this.getCode() == other.getCode();
        }
    }

    default ErrorType params(Object... params) {
        Object context = null;
        if (this instanceof ErrorType.Default) {
            context = ((ErrorType.Default) this).getContext();
        }

        return new ErrorType.Default(this.getCode(), String.format(this.getMsg(), params), context);
    }

    default ErrorType context(Object context) {
        return new ErrorType.Default(this.getCode(), this.getMsg(), context);
    }

    public static class Default implements ErrorType {
        private final int code;
        private final String msg;
        private Object context;

        public Default(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Default(int code, String msg, Object context) {
            this.code = code;
            this.msg = msg;
            this.context = context;
        }

        public ErrorType.Default setContext(Object context) {
            this.context = context;
            return this;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getMsg() {
            return this.msg;
        }

        public Object getContext() {
            return this.context;
        }

        @Override
        public String toString() {
            return "ErrorType.Default(code=" + this.getCode() + ", msg=" + this.getMsg() + ", context=" + this.getContext() + ")";
        }
    }
}

