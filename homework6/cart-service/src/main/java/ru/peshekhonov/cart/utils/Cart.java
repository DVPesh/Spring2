package ru.peshekhonov.cart.utils;

import lombok.Data;
import ru.peshekhonov.api.dto.ProductDto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private BigDecimal totalCost;

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(ProductDto p) {
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

    public void subtract(ProductDto p) {
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProductId().equals(p.getId())) {
                if (item.decrementQuantity() < 1) {
                    iterator.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void clear() {
        items.clear();
        totalCost = null;
    }

    public void removeItem(Long productId) {
        if (items.removeIf((item) -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }

    private void recalculate() {
        totalCost = BigDecimal.ZERO;
        items.forEach(i -> totalCost = totalCost.add(i.getCost()));
        if (totalCost.compareTo(BigDecimal.ZERO) == 0) {
            totalCost = null;
        }
    }
}
