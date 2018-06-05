package com.imooc.form;

import lombok.Data;

/**
 * @author xuan
 * @date 18/5/31 下午3:28
 */
@Data
public class CategoryForm {
    private Integer categoryId;

    /**
     *类目名称
     */
    private String categoryName;
    /**
     *类目编号
     */
    private Integer categoryType;
}
