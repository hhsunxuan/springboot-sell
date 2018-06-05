package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.imooc.utils.JsonUtil;
import com.imooc.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.math.BigDecimal;

/**
 * @author xuan
 * @date 18/5/28 下午5:46
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService{

    private static final String ORDER_NAME = "微信点餐订单";


    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    /**
     * 下单发起支付
     * @param orderDTO
     * @return
     */
    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);

        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("｛［微信支付］发起支付，request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("［微信支付］发起支付，response={}",JsonUtil.toJson(payResponse));

        return payResponse;

    }

    /**
     * 异步通知，修改订单状态
     * @param notifyData
     * @return
     */
    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付的状态
        //3.支付金额
        //4.支付人（下单人 == 支付人）


        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("［微信支付］异步通知，payResponse＝{}",JsonUtil.toJson(payResponse));

        //查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        //判断订单是否存在
        if (orderDTO == null){
            log.error("［微信支付］异步通知，订单不存在，orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);

        }
        //判断金额是否一致
        /**
         * 比较0.1与0.10
         * 让两个数相减，结果小于某个精度，则判断为两个数相等
         */
//        if (orderDTO.getOrderAmount().compareTo(new BigDecimal(payResponse.getOrderAmount())) != 0){
//            log.error("［微信支付］异步通知，订单金额不一致，orderId={},微信通知金额={},系统金额={}",
//                    payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderAmount()
//                    );
//            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
//        }

        if (MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
            log.error("［微信支付］异步通知，订单金额不一致，orderId={},微信通知金额={},系统金额={}",
                    payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderAmount()
            );
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        //修改订单的支付状态
        orderService.paid(orderDTO);

        return payResponse;

    }

    /**
     * 退款
     * @param orderDTO
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信退款] request={}",JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);

        log.info("［微信退款] response={}",JsonUtil.toJson(refundResponse));
        return refundResponse;




    }
}
