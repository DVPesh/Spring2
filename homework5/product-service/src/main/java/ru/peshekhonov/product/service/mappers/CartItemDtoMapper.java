package ru.peshekhonov.product.service.mappers;

import org.mapstruct.Mapper;
import ru.peshekhonov.dto.CartItemDto;
import ru.peshekhonov.product.service.utils.CartItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemDtoMapper {

    CartItemDto map(CartItem cartItem);

    List<CartItemDto> map(List<CartItem> cartItems);
}
