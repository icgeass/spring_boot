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

    /**
     * 查找顺序：
     * 1，视图路径
     * 2，uri mapping
     * 3，/error
     * 4，错误页面
     * @param req
     * @param model
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest req, Model model) {
        // xss
        String name = req.getParameter("name");
        helloService.sayHello(name);
        model.addAttribute("name", name);
        return "welcome";
    }
}
