package ru.peshekhonov.online.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.peshekhonov.online.store.dto.OrderDto;
import ru.peshekhonov.online.store.entities.Order;
import ru.peshekhonov.online.store.entities.Visitor;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderItemDtoMapper.class)
public interface OrderDtoMapper {

    @Mapping(source = "visitor", target = "username", qualifiedByName = "getUsername")
    OrderDto map(Order order);

    @Named("getUsername")
    default String getUsername(Visitor visitor) {
        return visitor.getUsername();
    }

    List<OrderDto> map(List<Order> orders);
}
