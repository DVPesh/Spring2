package ru.peshekhonov.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.peshekhonov.api.dto.CartDto;
import ru.peshekhonov.cart.mappers.CartDtoMapper;
import ru.peshekhonov.cart.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartDtoMapper cartDtoMapper;

    @GetMapping
    public CartDto getCurrentCart() {
        return cartDtoMapper.map(cartService.getCurrentCart());
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
