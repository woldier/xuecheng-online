package com.xuecheng.orders.api;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.xuecheng.orders.config.AlipayConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Mr.M
 * @version 1.0
 * @description 支付宝查询接口
 * @date 2022/10/4 17:18
 */
@SpringBootTest
public class AliPayTest {

    @Value("${pay.alipay.APP_ID}")
    String APP_ID;
    @Value("${pay.alipay.APP_PRIVATE_KEY}")
    String APP_PRIVATE_KEY;
    
    @Value("${pay.alipay.ALIPAY_PUBLIC_KEY}")
    String ALIPAY_PUBLIC_KEY;

@Test
public void queryPayResult() throws AlipayApiException {
    AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, APP_ID, APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE); //获得初始化的AlipayClient
    AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
    JSONObject bizContent = new JSONObject();
    bizContent.put("out_trade_no", "202210100010101002");
    //bizContent.put("trade_no", "2014112611001004680073956707");
    request.setBizContent(bizContent.toString());
    AlipayTradeQueryResponse response = alipayClient.execute(request);
    if (response.isSuccess()) {
        System.out.println("调用成功");
        System.out.println(response.getBody());
    } else {
        System.out.println("调用失败");
    }
}
}
