package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * mybatis 方式的使用，注意要在启动类中加入mybatis的注解配置
 * 注解配置如：@MapperScan(basePackages = "com.imooc.dataobject.mapper")
 * @author xuan
 * @date 18/6/4 下午5:15
 */
public interface ProductCategoryMapper {

    /**
     *
     * 在注解中使用SQL
     */

    @Insert("insert into product_category(category_name, category_type) values (#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    @Insert("insert into product_category(category_name, category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);


    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);


    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);


    /**
     * 通过字段更新时需要在字段前面加上@Param("字段名称")
     * @param categoryName
     * @param categoryType
     * @return
     */
    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);


    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);


    /**
     * 在xml中使用SQL
     * @param categoryType
     * @return
     */
    ProductCategory selectByCategoryType(Integer categoryType);



}
