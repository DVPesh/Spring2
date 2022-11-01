package ru.peshekhonov.product.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.dto.ProductDto;
import ru.peshekhonov.product.service.exceptions.ResourceNotFoundException;
import ru.peshekhonov.product.service.mappers.ProductDtoMapper;
import ru.peshekhonov.product.service.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productDtoMapper.map(productService.findAll());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
