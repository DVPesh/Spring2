package ru.peshekhonov.online.store.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal cost;

    public void incrementQuantity() {
        quantity++;
        cost = cost.add(pricePerProduct);
    }

    public int decrementQuantity() {
        if (--quantity > 0) {
            cost = cost.subtract(pricePerProduct);
        } else {
            quantity = 0;
            cost = BigDecimal.ZERO;
        }
        return quantity;
    }
}
