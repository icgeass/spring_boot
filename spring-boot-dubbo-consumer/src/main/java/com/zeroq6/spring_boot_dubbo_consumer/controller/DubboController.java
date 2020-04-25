package com.zeroq6.spring_boot_dubbo_consumer.controller;


import com.zeroq6.spring_boot.api.DemoServiceApi;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
public class DubboController implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(interfaceClass = DemoServiceApi.class, version = "${demo.service.version}", check = false)
    private DemoServiceApi demoServiceApi;

    @Value("${dubbo.application.name}")
    private String dubboApplicationName;


    @RequestMapping("/hello")
    private Object hello(String name) {
        String prefix = "调用com.zeroq6.spring_boot.api.DemoServiceApi.sayHello";
        String result = null;
        try {
            logger.info("{}开始, {}", prefix, name);
            result = demoServiceApi.sayHello(name);
            logger.info("{}结束, {}, {}", prefix, name, result);
        } catch (Exception e) {
            logger.error("{}异常, {}", prefix, name, e);
        }
        return result;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        logger.warn("{} start ok!!!", dubboApplicationName);
    }
}
