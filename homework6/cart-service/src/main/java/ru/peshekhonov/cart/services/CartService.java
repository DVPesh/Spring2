package ru.peshekhonov.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.peshekhonov.api.dto.ProductDto;
import ru.peshekhonov.cart.integrations.ProductServiceIntegration;
import ru.peshekhonov.cart.utils.Cart;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductServiceIntegration productService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public Cart getCurrentCart(String cartId) {
        String id = cartPrefix + cartId;
        if (Boolean.FALSE.equals(redisTemplate.hasKey(id))) {
            redisTemplate.opsForValue().set(id, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(id);
    }

    public void addToCart(String cartId, Long productId) {
        ProductDto p = productService.findById(productId);
        execute(cartId, cart -> cart.add(p));
    }

    public void subtractFromCart(String cartId, Long productId) {
        ProductDto p = productService.findById(productId);
        execute(cartId, cart -> cart.subtract(p));
    }

    public void removeFromCart(String cartId, Long productId) {
        execute(cartId, cart -> cart.removeItem(productId));
    }

    public void clear(String cartId) {
        execute(cartId, Cart::clear);
    }

    private void execute(String cartId, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(cartId);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + cartId, cart);
    }

    public void merge(String userCartId, String guestCartId) {
        Cart userCart = getCurrentCart(userCartId);
        execute(guestCartId, userCart::merge);
        redisTemplate.opsForValue().set(cartPrefix + userCartId, userCart);
    }
}
