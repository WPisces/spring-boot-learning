package com.pisces.spring.boot.learning.controller;

import com.pisces.spring.boot.learning.business.ConsumeProductBusiness;
import com.pisces.spring.boot.learning.common.dto.Product;
import com.pisces.spring.boot.learning.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 15:52 2020/3/3
 * @Modified By:
 */
@RestController
@Slf4j
public class ConsumeProductController {


    @Autowired
    private ConsumeProductBusiness consumeProductBusiness;

    @RequestMapping(value = "consume-product")
    public Response<Product> consumeProduct() {
        log.info("controller consumeProduct");
        return Response.success(consumeProductBusiness.consumeProduct());
    }

    @RequestMapping(value = "test-retry")
    public Response testSpringRetry() {
        log.info("controller testSpringRetry");
        consumeProductBusiness.testSpringRetry();
        return Response.success();
    }


}


