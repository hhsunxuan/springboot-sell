package com.imooc.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * @author xuan
 * @date 18/5/20 下午10:30
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable{

    /**
     * 序列化
     */
    private static final long serialVersionUID = 6399186181668983148L;
    @Id
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
     * 商品状态,0正常,1下架
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();


    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }




}
