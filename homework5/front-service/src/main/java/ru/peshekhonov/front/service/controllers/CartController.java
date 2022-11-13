package ru.peshekhonov.front.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.peshekhonov.dto.CartDto;
import ru.peshekhonov.front.service.utils.ProductServiceEurekaClient;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final ProductServiceEurekaClient productServiceEurekaClient;

    @GetMapping
    public CartDto loadCart() {
        return productServiceEurekaClient.getCurrentCart();
    }

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable Long productId) {
        productServiceEurekaClient.addProductToCart(productId);
    }

    @GetMapping("/subtract/{productId}")
    public void subtractFromCart(@PathVariable Long productId) {
        productServiceEurekaClient.subtractProductFromCart(productId);
    }

    @GetMapping("/remove/{productId}")
    public void removeFromCart(@PathVariable Long productId) {
        productServiceEurekaClient.removeProductFromCart(productId);
    }

    @GetMapping("/clear")
    public void clearCart() {
        productServiceEurekaClient.clear();
    }
}
