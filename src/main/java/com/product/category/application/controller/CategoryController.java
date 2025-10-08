package com.product.category.application.controller;

import com.product.category.application.service.contract.CategoryService;
import com.product.product.application.service.contract.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("categories", categoryService.findAllShortCategories());
        model.addAttribute("products", productService.findAllProductsByLatestCreatedAt());
        return "index";
    }
}
