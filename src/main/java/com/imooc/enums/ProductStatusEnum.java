package com.imooc.enums;

import lombok.Getter;

/**
 * 商品状态
 * @author xuan
 * @date 18/5/21 上午1:37
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}