package ru.peshekhonov.product.service.mappers;

import org.mapstruct.Mapper;
import ru.peshekhonov.dto.CartDto;
import ru.peshekhonov.product.service.utils.Cart;

@Mapper(componentModel = "spring", uses = {CartItemDtoMapper.class})
public interface CartDtoMapper {

    CartDto map(Cart cart);
}
