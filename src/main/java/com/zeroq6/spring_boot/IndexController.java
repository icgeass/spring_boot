package com.zeroq6.spring_boot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest req) {
        return "this is test";
    }

}
