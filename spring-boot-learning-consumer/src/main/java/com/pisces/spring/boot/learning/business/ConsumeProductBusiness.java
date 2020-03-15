package com.pisces.spring.boot.learning.business;

import com.pisces.spring.boot.learning.common.dto.Product;
import org.springframework.stereotype.Service;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 15:54 2020/3/3
 * @Modified By:
 */
public interface ConsumeProductBusiness {
    Product consumeProduct();

    void testSpringRetry();
}
