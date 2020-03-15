package com.pisces.spring.boot.learning.consumer;

import com.pisces.spring.boot.learning.common.dto.Product;
import com.pisces.spring.boot.learning.common.response.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 15:38 2020/3/3
 * @Modified By:
 */
//name或value 为product项目中application.yml配置文件中的application.name;
//path 为product项目中application.yml配置文件中的context.path;
@FeignClient(value = "product-server", path = "/product", fallback = ProductConsumer.FallBack.class)
public interface ProductConsumer {

    @RequestMapping(value = "get-product")
    Response<Product> getProduct();

    class FallBack implements ProductConsumer {

        @Override
        public Response<Product> getProduct() {

            return Response.error("call basic fallback");
        }
    }
}
