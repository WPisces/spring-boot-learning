package com.pisces.spring.boot.learning.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 16:38 2020/3/3
 * @Modified By:
 */
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long price;
}
