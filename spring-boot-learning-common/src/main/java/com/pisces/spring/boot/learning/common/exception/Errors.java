package com.pisces.spring.boot.learning.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 11:19 2020/3/4
 * @Modified By:
 */
@Getter
@RequiredArgsConstructor
public enum Errors implements ErrorType {

    RESPONSE_ERROR(3000, "%s");

    private final int code;
    private final String msg;

}
