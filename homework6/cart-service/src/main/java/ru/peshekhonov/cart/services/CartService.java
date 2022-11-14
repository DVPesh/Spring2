package ru.peshekhonov.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.peshekhonov.api.dto.ProductDto;
import ru.peshekhonov.cart.integrations.ProductServiceIntegration;
import ru.peshekhonov.cart.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductServiceIntegration productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addToCart(Long productId) {
        ProductDto p = productService.findById(productId);
        cart.add(p);
    }

    public void subtractFromCart(Long productId) {
        ProductDto p = productService.findById(productId);
        cart.subtract(p);
    }

    public void removeFromCart(Long productId) {
        cart.removeItem(productId);
    }

    public void clear() {
        cart.clear();
    }
}
