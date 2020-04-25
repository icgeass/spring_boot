package com.zeroq6.spring_boot.configuration;


import com.zeroq6.spring_boot.configuration.filter.XssFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FilterConfiguration {

    @Value("${xss.enabled}")
    private String enabled;

    @Value("${xss.excludes}")
    private String excludes;

    @Value("${xss.urlPatterns}")
    private String urlPatterns;

    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST));
        registrationBean.setFilter(new XssFilter());
        registrationBean.addUrlPatterns(StringUtils.split(urlPatterns, ","));
        registrationBean.setName("XssFilter");
        registrationBean.setOrder(-1);
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes", excludes);
        initParameters.put("enabled", enabled);
        registrationBean.setInitParameters(initParameters);
        return registrationBean;
    }
}
