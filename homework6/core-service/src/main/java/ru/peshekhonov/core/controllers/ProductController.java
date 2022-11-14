package ru.peshekhonov.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.api.dto.ProductDto;
import ru.peshekhonov.api.exceptions.ResourceNotFoundException;
import ru.peshekhonov.core.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.findAllProductDto();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
