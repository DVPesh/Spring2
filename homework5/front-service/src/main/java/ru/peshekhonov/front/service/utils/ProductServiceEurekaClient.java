package ru.peshekhonov.front.service.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.dto.CartDto;
import ru.peshekhonov.dto.ProductDto;

import java.util.List;

@FeignClient(name = "product-service", path = "/market/api/v1")
public interface ProductServiceEurekaClient {

    @GetMapping("/products")
    List<ProductDto> getAllProducts();

    @GetMapping("/products/{id}")
    ProductDto getProductById(@PathVariable Long id);

    @PostMapping("/products")
    void createNewProduct(@RequestBody ProductDto productDto);

    @DeleteMapping("/products/{id}")
    void deleteProductById(@PathVariable Long id);

    @GetMapping("/categories")
    List<String> getAllCategoryTitles();

    @GetMapping("/cart")
    CartDto getCurrentCart();

    @GetMapping("/cart/add/{productId}")
    void addProductToCart(@PathVariable Long productId);

    @GetMapping("/cart/subtract/{productId}")
    void subtractProductFromCart(@PathVariable Long productId);

    @GetMapping("/cart/remove/{productId}")
    void removeProductFromCart(@PathVariable Long productId);

    @GetMapping("/cart/clear")
    void clear();
}
