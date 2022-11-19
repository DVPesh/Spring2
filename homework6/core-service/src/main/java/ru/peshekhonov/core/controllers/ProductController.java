package ru.peshekhonov.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.api.dto.ProductDto;
import ru.peshekhonov.api.exceptions.ResourceNotFoundException;
import ru.peshekhonov.core.mappers.ProductDtoMapper;
import ru.peshekhonov.core.services.CategoryService;
import ru.peshekhonov.core.services.ProductService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductDtoMapper productDtoMapper;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "category", required = false) String category
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, title, category, page).map(productDtoMapper::map);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findById(id).map(productDtoMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDtoMapper.map(productDto, categoryService));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
