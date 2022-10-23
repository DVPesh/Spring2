package ru.peshekhonov.online.store.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.peshekhonov.online.store.entities.OrderItem;
import ru.peshekhonov.online.store.repositories.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem item) {
        return orderItemRepository.save(item);
    }
}
