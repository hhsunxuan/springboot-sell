package com.imooc.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author xuan
 * @date 18/5/31 上午12:38
 */
@Data
public class ProductForm {

    private String productId;

    /**
     * 名称
     */
    private String productName;

    /**
     * 单价
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Integer productStock;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 小图(图标)
     */
    private String productIcon;


    /**
     * 类目编号
     */
    private Integer categoryType;
}
