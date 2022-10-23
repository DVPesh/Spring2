package ru.peshekhonov.online.store.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.peshekhonov.online.store.dto.OrderDetailsDto;
import ru.peshekhonov.online.store.entities.Order;
import ru.peshekhonov.online.store.entities.OrderItem;
import ru.peshekhonov.online.store.entities.Visitor;
import ru.peshekhonov.online.store.exceptions.ResourceNotFoundException;
import ru.peshekhonov.online.store.repositories.OrderRepository;
import ru.peshekhonov.online.store.utils.Cart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderItemService orderItemService;

    @Transactional
    public void createOrder(Visitor visitor, Optional<OrderDetailsDto> orderDetailsDto) {
        Cart cart = cartService.getCurrentCart();
        Order orderInstance = new Order();
        if (orderDetailsDto.isPresent()) {
            orderInstance.setAddress(orderDetailsDto.get().getAddress());
            orderInstance.setPhone(orderDetailsDto.get().getPhone());
        }
        orderInstance.setVisitor(visitor);
        orderInstance.setTotalCost(cart.getTotalCost());
        final Order order = orderRepository.save(orderInstance);
        List<OrderItem> items = cart.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setQuantity(o.getQuantity());
                    item.setPricePerProduct(o.getPricePerProduct());
                    item.setCost(o.getCost());
                    Long productId = o.getProductId();
                    item.setProduct(productService.findById(productId)
                            .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + productId + " не найден")));
                    return orderItemService.save(item);
                }).collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
        cartService.clear();
    }

    public List<Order> findOrdersByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }
}
