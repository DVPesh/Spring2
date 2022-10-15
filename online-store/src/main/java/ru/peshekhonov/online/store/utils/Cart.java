package ru.peshekhonov.online.store.utils;

import lombok.Data;
import ru.peshekhonov.online.store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private BigDecimal totalCost;

    public void add(Product p) {
        for (CartItem item : items) {
            if (item.getProductId().equals(p.getId())) {
                item.incrementQuantity();
                recalculate();
                return;
            }
        }
        CartItem cartItem = new CartItem(p.getId(), p.getTitle(), 1, p.getPrice(), p.getPrice());
        items.add(cartItem);
        recalculate();
    }

    public void clear() {
        items.clear();
        totalCost = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalCost = BigDecimal.ZERO;
        items.forEach(i -> totalCost = totalCost.add(i.getCost()));
    }
}
