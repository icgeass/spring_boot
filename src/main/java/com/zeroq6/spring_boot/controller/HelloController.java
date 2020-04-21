package com.zeroq6.spring_boot.controller;

import com.zeroq6.spring_boot.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/welcome")
    public String test(HttpServletRequest req, Model model) {
        String name = req.getParameter("name");
        helloService.sayHello(name);
        model.addAttribute("name", name);
        return "welcome";
    }
}
