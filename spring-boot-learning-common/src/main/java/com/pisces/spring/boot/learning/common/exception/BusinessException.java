package com.pisces.spring.boot.learning.common.exception;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 16:31 2020/3/3
 * @Modified By:
 */
public class BusinessException extends RuntimeException {

    private final ErrorType errorType;

    public BusinessException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    @Override
    public String getMessage() {
        return "code=" + this.errorType.getCode() + ", msg=" + this.errorType.getMsg();
    }

    @Override
    public String toString() {
        return "BusinessException [" + this.getMessage() + "]";
    }
}
