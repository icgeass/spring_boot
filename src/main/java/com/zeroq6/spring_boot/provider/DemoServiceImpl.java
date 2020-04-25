package com.zeroq6.spring_boot.provider;

import com.zeroq6.spring_boot.api.DemoServiceApi;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Service
@Component
public class DemoServiceImpl implements DemoServiceApi {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String sayHello(String name) {
        String result = "null";
        try {
            logger.info("sayHello传入: " + name);
            result = getClass().getCanonicalName() + ".sayHello(): " + name;
        } catch (Exception e) {
            logger.error("sayHello 异常", e);
        }
        logger.info("sayHello返回: " + result);
        return result;
    }
}
