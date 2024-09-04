package com.webbanhangnongsan.vn.webbanhangnongsan.controller;

import com.webbanhangnongsan.vn.webbanhangnongsan.entity.Product;
import com.webbanhangnongsan.vn.webbanhangnongsan.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DecimalFormat;
import java.util.List;

@Controller
public class ShopGridController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/shop-grid")
    public String shopGrid(Model model) {
        getData(model);
        return "web/shop-grid";
    }

    public void getData(Model model) {
        List<Product> getAllProducts = productRepository.findAll();
        model.addAttribute("getAllProducts", getAllProducts);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        for (Product product : getAllProducts) {
            product.setFormattedPrice(decimalFormat.format(product.getPrice()));
        }
        int numberOfProducts = getAllProducts.size();
        model.addAttribute("numberOfProducts", numberOfProducts);
    }
}
