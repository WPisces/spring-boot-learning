package com.pisces.spring.boot.learining.controller;

import com.pisces.spring.boot.learning.common.dto.Product;
import com.pisces.spring.boot.learning.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 10:43 2020/3/3
 * @Modified By:
 */
@RestController
@Slf4j
public class ProductController {

    @RequestMapping(value = "get-product")
    public Response<Product> getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test product");
        product.setPrice(1000L);
        log.info("getProduct {}", product);
        return Response.success(product);
    }

}
