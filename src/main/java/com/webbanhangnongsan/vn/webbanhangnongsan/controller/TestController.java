package com.webbanhangnongsan.vn.webbanhangnongsan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test")
    public String about(Model model) {
        return "web/login";
    }

    @GetMapping("/")
    public String indexTest(Model model) {
        return "web/index";
    }
}

