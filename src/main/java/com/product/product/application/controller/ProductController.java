package com.product.product.application.controller;

import com.product.category.application.service.contract.CategoryService;
import com.product.product.application.dto.req.ProductFilterRequest;
import com.product.product.application.service.contract.BrandService;
import com.product.product.application.service.contract.ProductService;
import com.product.product.application.service.contract.ProductTypeService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductTypeService productTypeService;

    @GetMapping
    public String findAllBy(
        Model model,
        ProductFilterRequest filters,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "createdAt=desc") String[] sort
    ) {
        model.addAttribute(
            "listProducts",
            productService.findAllBy(filters, buildPageable(page, size, sort))
        );
        model.addAttribute("categories", categoryService.findAllShortCategories());
        model.addAttribute("brands", brandService.findAllShortBrands());
        model.addAttribute("productTypes", productTypeService.findAllShortProductTypes());
        return "products";
    }

    @GetMapping("/{slug}")
    public String getBy(Model model, @PathVariable String slug) {
        model.addAttribute("product", productService.findBySlug(slug));
        return "product-details";
    }

    private Pageable buildPageable(int page, int size, String[] sort) {
        return PageRequest.of(page, size, Sort.by(parseSort(sort)));
    }

    private static List<Order> parseSort(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String sortParam : sort) {
            String[] parts = sortParam.split("=");
            Sort.Direction direction =
                parts.length > 1 && parts[1].equals("asc") ? Direction.ASC : Direction.DESC;
            orders.add(new Sort.Order(direction, parts[0]));
        }
        return orders;
    }
}
