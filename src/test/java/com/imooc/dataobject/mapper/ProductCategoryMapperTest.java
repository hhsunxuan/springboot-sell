package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author xuan
 * @date 18/6/4 下午5:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {

        Map<String,Object> map = new HashMap<>();
        map.put("category_name","师兄最不爱");
        map.put("category_type",101);


        int result = mapper.insertByMap(map);

        Assert.assertEquals(1,result);

    }
    @Test
    public void insertByObject() throws Exception{
        ProductCategory productCategory = new ProductCategory();

        productCategory.setCategoryName("师兄最不爱");
        productCategory.setCategoryType(102);

        int result = mapper.insertByObject(productCategory);

        Assert.assertEquals(1,result);

    }

    @Test
    public void findByCategoryType() throws Exception{
        ProductCategory result = mapper.findByCategoryType(102);

        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryName() throws Exception{
        List<ProductCategory> result = mapper.findByCategoryName("师兄最不爱");

        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void updateByCategoryType() throws Exception{

        int result = mapper.updateByCategoryType("师兄最不爱的分类",102);
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateByObject() throws Exception{

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("师兄最不爱");
        productCategory.setCategoryType(102);

        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteByCategoryType() throws Exception{

        int result = mapper.deleteByCategoryType(102);
        Assert.assertEquals(1,result);

    }


    @Test
    public void selectByCategoryType(){
        ProductCategory productCategory = mapper.selectByCategoryType(101);
        Assert.assertNotNull(productCategory);




    }
}