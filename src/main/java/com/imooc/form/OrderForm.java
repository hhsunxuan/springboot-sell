package com.imooc.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author xuan
 * @date 18/5/26 下午6:50
 */
@Data
public class OrderForm {


    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;


    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "买家地址必填")
    private String address;


    /**
     * 买家openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 买家购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;




}
