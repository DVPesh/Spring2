package ru.peshekhonov.front.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.dto.ProductDto;
import ru.peshekhonov.front.service.utils.ProductServiceEurekaClient;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceEurekaClient productServiceEurekaClient;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productServiceEurekaClient.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productServiceEurekaClient.deleteProductById(id);
    }

    @PostMapping
    public void createNewProduct(@RequestBody ProductDto productDto) {
        productServiceEurekaClient.createNewProduct(productDto);
    }
}
