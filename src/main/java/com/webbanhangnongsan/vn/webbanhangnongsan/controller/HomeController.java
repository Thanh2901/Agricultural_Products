package com.webbanhangnongsan.vn.webbanhangnongsan.controller;

import com.webbanhangnongsan.vn.webbanhangnongsan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/index")
    public String index() {
        return "web/index";
    }

//    @GetMapping
//    public String home(HttpServletRequest request, Model model, User user) {
//        List<ProductResponse> productList = productRepository.getTop8Products();
//        model.addAttribute("productList", productList);
//
//        // Kiểm tra xem người dùng đã đăng nhập và thêm thông tin vào mô hình
//        if (user != null && user.getName() != null) {
//            model.addAttribute("isLoggedIn", true);
//            model.addAttribute("username", user.getName());
//        } else {
//            model.addAttribute("isLoggedIn", false);
//        }
//        return "web/index";
//    }
}
