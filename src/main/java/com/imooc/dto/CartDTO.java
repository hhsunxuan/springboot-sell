package com.imooc.dto;

import lombok.Data;

/**
 * @author xuan
 * @date 18/5/26 上午1:31
 */
@Data
public class CartDTO {
    /**
     * 商品Id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
