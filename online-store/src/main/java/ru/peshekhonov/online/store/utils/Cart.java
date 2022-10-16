package ru.peshekhonov.online.store.utils;

import lombok.Data;
import ru.peshekhonov.online.store.entities.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private BigDecimal totalCost;

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

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
        totalCost = null;
    }

    private void recalculate() {
        totalCost = BigDecimal.ZERO;
        items.forEach(i -> totalCost = totalCost.add(i.getCost()));
    }
}
