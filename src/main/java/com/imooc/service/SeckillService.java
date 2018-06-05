package com.imooc.service;

/**
 * @author xuan
 * @date 18/6/4 下午8:26
 */
public interface SeckillService {

    /**
     * 查询特价商品
     * @param productId
     * @return
     */
    String querySecKillProductInfo(String productId);

    /**
     * 秒杀的逻辑方法
     * @param productId
     */
    void orderProductMockDiffUser(String productId);
}
