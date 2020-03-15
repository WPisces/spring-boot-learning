package com.pisces.spring.boot.learning.service.impl;

import com.pisces.spring.boot.learning.common.dto.Product;
import com.pisces.spring.boot.learning.common.exception.BusinessException;
import com.pisces.spring.boot.learning.common.exception.ErrorType;
import com.pisces.spring.boot.learning.common.exception.Errors;
import com.pisces.spring.boot.learning.common.untils.Wrapper;
import com.pisces.spring.boot.learning.consumer.ProductConsumer;
import com.pisces.spring.boot.learning.service.ConsumeProductService;
import com.pisces.spring.boot.learning.utils.DingTalkUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 16:03 2020/3/3
 * @Modified By:
 */
@Service
@Slf4j
public class ConsumeProductServiceImpl implements ConsumeProductService {

    private static boolean FLG = true;

    @Autowired
    private ProductConsumer productConsumer;
    @Value("${dingtalk.url}")
    private String dingTalkUrl;
    @Value("${dingtalk.errmsg:}")
    private String errMsg;

    /**
     * value 捕获的异常信息 捕获指定异常之后重试
     * maxAttempts 最大重试次数 包括了第一次调用的次数
     * backoff 指定重试时的参数
     * --delay 延迟重试的基数 即第一次与第二次之间间隔的时间
     * --multiplier 时间倍数 从第三次开始 本次重试时间是上一次的几倍
     * --maxDelay 两次重试之间的最大时间间隔 不设置默认为30秒
     */
    @Override
    @Async
    @Retryable(value = BusinessException.class, maxAttempts = 4, backoff = @Backoff(delay = 3000L, multiplier = 1.0, maxDelay = 10 * 60 * 1000))
    public void testSpringRetry() {
        log.info("service consumeProduct time {}", new Date());
        if (FLG) {
//            FLG = false;
            throw new BusinessException(Errors.RESPONSE_ERROR.params("1233445667654321:" + errMsg));
        }
        log.info("service consumeProduct finish·······");
//        int count = 1;
//        int time = 3000;
//        while (count < 6) {
//            log.info("testSpringRetry retry time={}", count);
//            try {
//                Thread.sleep(time);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            time *= 3;
//            count++;
//        }
    }

    /**
     * @param e 抛出的异常信息
     *          "@Recover注解与@Retryable配套使用"
     *          当@Retryable标记的方法最后一次重试也没有成功并抛出异常之后就会调用此方法
     *          如果不指定此方法，会将最后一次重试的异常抛出
     */
    @Recover
    public void finishRetry(BusinessException e) {
        log.info("service consumeProduct finishRetry·······");
        ErrorType errorType = e.getErrorType();
        String msg = errorType == null ? e.getMessage() : errorType.getMsg();
        if (msg == null) {
            return;
        }
        String[] split = msg.split(":");
        String builder = "## 撤销快跑者配送订单失败报警 " + "\n" + "### 快跑者配送订单号：" + split[0] + "\n" +
                "### 快跑者接口返回信息：" + (split.length > 1 ? split[1] : "无返回信息");
        DingTalkUtil.sendMarkdownMsg(dingTalkUrl, "撤销快跑者配送订单失败报警", builder);
    }


    @Override
    public Product consumeProduct() {
        log.info("service consumeProduct");
        return Wrapper.unwrap(productConsumer.getProduct());
    }
}
