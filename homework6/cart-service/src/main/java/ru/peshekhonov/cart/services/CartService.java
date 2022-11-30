package ru.peshekhonov.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.peshekhonov.api.dto.ProductDto;
import ru.peshekhonov.cart.integrations.ProductServiceIntegration;
import ru.peshekhonov.cart.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductServiceIntegration productService;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String cartId) {
        String id = cartPrefix + cartId;
        if (!carts.containsKey(id)) {
            carts.put(id, new Cart());
        }
        return carts.get(id);
    }

    public void addToCart(String cartId, Long productId) {
        ProductDto p = productService.findById(productId);
        getCurrentCart(cartId).add(p);
    }

    public void subtractFromCart(String cartId, Long productId) {
        ProductDto p = productService.findById(productId);
        getCurrentCart(cartId).subtract(p);
    }

    public void removeFromCart(String cartId, Long productId) {
        getCurrentCart(cartId).removeItem(productId);
    }

    public void clear(String cartId) {
        getCurrentCart(cartId).clear();
    }
}
