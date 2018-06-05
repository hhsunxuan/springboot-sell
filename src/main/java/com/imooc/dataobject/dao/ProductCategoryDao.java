package com.imooc.dataobject.dao;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author xuan
 * @date 18/6/4 下午7:24
 */
public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    public  int insertByMap(Map<String,Object> map){
        return productCategoryMapper.insertByMap(map);

    }

    public List<ProductCategory> findByCategoryName(String categoryName){
        return productCategoryMapper.findByCategoryName(categoryName);
    }
}
