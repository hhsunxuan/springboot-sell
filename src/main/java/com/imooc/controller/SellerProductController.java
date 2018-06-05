package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.OrderDTO;
import com.imooc.exception.SellException;
import com.imooc.form.ProductForm;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**卖家商品
 * @author xuan
 * @date 18/5/29 下午8:55
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){

        PageRequest request = new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage",productInfoPage);

        map.put("currentPage",page);
        map.put("size",size);

        return new ModelAndView("product/list",map);

    }


    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try{
            productService.onSale(productId);
        }catch (SellException e){

            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return  new ModelAndView("common/success",map);
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try{
            productService.offSale(productId);
        }catch (SellException e){

            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return  new ModelAndView("common/success",map);
    }


    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                      Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);

            map.put("productInfo",productInfo);

        }
        //查询所有的类目
        List<ProductCategory> categoryList =  categoryService.findAll();

        map.put("categoryList",categoryList);

        return new ModelAndView("product/index",map);

    }


    /**
     * 保存／更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     *
     * 说明：
     * 对应更改 @Cacheable(cacheNames = "product", key = "123")的缓存
     *
     * 清除缓存的注解,访问方法之后会将缓存清除，
     * 下次查询时访问controller时由于@Cacheable(cacheNames = "product", key = "123")，缓存为空，
     * 则会进入方法从DB中读取数据，
     * 达到每次修改保存数据时会删除缓存，Cacheable会从DB中读取数据的效果
     */
    @PostMapping("/save")
//    @CachePut(cacheNames = "product", key = "123")  //更新缓存的注解
    @CacheEvict(cacheNames = "product", key = "123") //删除缓存的注解
    public ModelAndView save(@Valid ProductForm form,
                           BindingResult bindingResult,
                             Map<String,Object> map
                             ){

        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        ProductInfo productInfo = new ProductInfo();
        try{
            //若productId为空，说明是新增
            if (!StringUtils.isEmpty(form.getProductId())){
                productInfo =  productService.findOne(form.getProductId());
            }else {
                form.setProductId(KeyUtil.genUniqueKey());
            }

            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);

        }

        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);

    }




}
