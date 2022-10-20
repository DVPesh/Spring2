package ru.peshekhonov.online.store.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.peshekhonov.online.store.services.CartService;
import ru.peshekhonov.online.store.utils.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
    }

    @GetMapping("/subtract/{productId}")
    public void subtractProductFromCart(@PathVariable Long productId) {
        cartService.subtractFromCart(productId);
    }

    @GetMapping("/remove/{productId}")
    public void removeProductFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
    }

    @GetMapping("/clear")
    public void clear() {
        cartService.clear();
    }
}
