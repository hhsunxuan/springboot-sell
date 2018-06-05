package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 * @author xuan
 * @date 18/5/31 下午5:09
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);



}
