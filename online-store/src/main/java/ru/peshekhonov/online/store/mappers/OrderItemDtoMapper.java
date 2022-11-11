package ru.peshekhonov.online.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.peshekhonov.online.store.dto.OrderItemDto;
import ru.peshekhonov.online.store.entities.OrderItem;
import ru.peshekhonov.online.store.entities.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemDtoMapper {

    @Mapping(source = "product", target = "productId", qualifiedByName = "getProductId")
    @Mapping(source = "product", target = "productTitle", qualifiedByName = "getProductTitle")
    OrderItemDto map(OrderItem item);

    @Named("getProductId")
    default Long getProductId(Product product) {
        return product.getId();
    }

    @Named("getProductTitle")
    default String getProductTitle(Product product) {
        return product.getTitle();
    }

    List<OrderItemDto> map(List<OrderItem> items);
}
