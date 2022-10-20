package ru.peshekhonov.online.store.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.peshekhonov.online.store.entities.Product;
import ru.peshekhonov.online.store.exceptions.ResourceNotFoundException;
import ru.peshekhonov.online.store.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
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
        Product p = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + productId + " не найден"));
        cart.add(p);
    }

    public void subtractFromCart(Long productId) {
        Product p = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + productId + " не найден"));
        cart.subtract(p);
    }

    public void removeFromCart(Long productId) {
        Product p = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + productId + " не найден"));
        cart.removeItem(productId);
    }

    public void clear() {
        cart.clear();
    }
}
