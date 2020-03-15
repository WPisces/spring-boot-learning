package com.pisces.spring.boot.learning.business.impl;

import com.pisces.spring.boot.learning.business.ConsumeProductBusiness;
import com.pisces.spring.boot.learning.common.dto.Product;
import com.pisces.spring.boot.learning.service.ConsumeProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 15:59 2020/3/3
 * @Modified By:
 */
@Service
@Slf4j
public class ConsumeProductBusinessImpl implements ConsumeProductBusiness {

    @Autowired
    private ConsumeProductService consumeProductService;

    @Override
    public Product consumeProduct() {
        log.info("business consumeProduct");
        return consumeProductService.consumeProduct();
    }

    @Override
    public void testSpringRetry() {
        log.info("business testSpringRetry start·········");
//        try {
        consumeProductService.testSpringRetry();
//        } catch (Exception e) {
//            log.info("business testSpringRetry catch exception········");
//        }
        log.info("business testSpringRetry finish········");
    }
}
