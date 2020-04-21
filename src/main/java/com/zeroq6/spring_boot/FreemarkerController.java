package com.zeroq6.spring_boot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {

    @RequestMapping("/welcome")
    public String test(HttpServletRequest req, Model model) {
        model.addAttribute("name", req.getParameter("name"));
        return "welcome";
    }
}
