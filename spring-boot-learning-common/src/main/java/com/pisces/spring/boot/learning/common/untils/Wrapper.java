package com.pisces.spring.boot.learning.common.untils;

import com.pisces.spring.boot.learning.common.exception.BusinessException;
import com.pisces.spring.boot.learning.common.exception.ErrorType;
import com.pisces.spring.boot.learning.common.response.Response;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 16:52 2020/3/3
 * @Modified By:
 */
public class Wrapper {

    public Wrapper() {
    }

    public static <T> T unwrap(Response<T> response) {
        if (response == null) {
            throw new BusinessException(ErrorType.SYSTEM_ERROR);
        } else if (response.getCode() != 0 && response.getCode() != 200) {
            throw new BusinessException(new ErrorType.Default(response.getCode(), response.getMsg()));
        } else {
            return response.getData();
        }
    }

    public static <T> T unwrapSafety(Response<T> response) {
        try {
            return unwrap(response);
        } catch (Throwable var2) {
            return null;
        }
    }
}