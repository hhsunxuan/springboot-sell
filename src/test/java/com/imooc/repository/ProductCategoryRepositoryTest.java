package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author xuan
 * @date 18/5/19 下午4:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

//    @Test
//    public void findOneTest(){
//        ProductCategory productCategory =   repository.findOne(1);
//        System.out.println(productCategory.toString());
//    }
    @Test
     //事务回滚，清楚测试方法产生的数据
    @Transactional
    public void saveTest(){
        ProductCategory productCategory =   new ProductCategory("男生最爱",4);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
        //Assert.assertNotEquals(null,result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> result =  repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }

}