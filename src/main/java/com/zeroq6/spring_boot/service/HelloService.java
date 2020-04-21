package com.zeroq6.spring_boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String sayHello(String name){
        String result = new StringBuilder("hello ").append(name).toString();
        String line = "sayHello " + result;
        logger.debug(line);
        logger.info(line);
        logger.warn(line);
        logger.error(line);
        return result;
    }
}
