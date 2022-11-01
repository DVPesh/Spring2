package ru.peshekhonov.front.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.peshekhonov.front.service.utils.ProductServiceEurekaClient;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductServiceEurekaClient productServiceEurekaClient;

    @GetMapping
    public List<String> getCategories() {
        return productServiceEurekaClient.getAllCategoryTitles();
    }
}
