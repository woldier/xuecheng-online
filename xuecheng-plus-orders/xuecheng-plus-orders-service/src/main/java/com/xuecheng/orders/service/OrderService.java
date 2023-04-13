package com.xuecheng.orders.service;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.orders.model.dto.AddOrderDto;
import com.xuecheng.orders.model.dto.PayRecordDto;
import com.xuecheng.orders.model.dto.PayStatusDto;
import com.xuecheng.orders.model.po.XcPayRecord;

/**
 * @author woldier
 * @version 1.0
 * @description 订单业务
 * @date 2023/4/13 13:47
 **/
public interface OrderService {
    /**
     * @description 创建商品订单
     * @param addOrderDto 订单信息
     * @return PayRecordDto 支付交易记录(包括二维码)
     * @author Mr.M
     * @date 2022/10/4 11:02
     */
     PayRecordDto createOrder(String userId, AddOrderDto addOrderDto) throws XueChengPlusException;

     /**
     *
     * description 查询支付记录
     *        
     * @param payNo  
     * @return com.xuecheng.orders.model.po.XcPayRecord
     * @author: woldier 
     * @date: 2023/4/13 14:15
     */
    XcPayRecord getPayRecordByPayno(String payNo);

    /**
     * @description 保存支付宝支付结果
     * @param payStatusDto  支付结果信息
     * @return void
     * @author Mr.M
     * @date 2022/10/4 16:52
     */
    public void saveAliPayStatus(PayStatusDto payStatusDto);
}
