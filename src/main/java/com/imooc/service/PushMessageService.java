package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * 消息推送
 * @author xuan
 * @date 18/6/4 下午2:07
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
