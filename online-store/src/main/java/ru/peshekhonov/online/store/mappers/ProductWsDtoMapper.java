package ru.peshekhonov.online.store.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.peshekhonov.online.store.entities.Category;
import ru.peshekhonov.online.store.entities.Product;
import ru.peshekhonov.online.store.exceptions.ResourceNotFoundException;
import ru.peshekhonov.online.store.services.CategoryService;
import ru.peshekhonov.online.store.soap.products.ProductWsDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductWsDtoMapper {

    @Mapping(source = "categoryTitle", target = "category", qualifiedByName = "getCategoryByTitle")
    Product map(ProductWsDto productWsDto, @Context CategoryService categoryService);

    @Named("getCategoryByTitle")
    default Category getCategoryByTitle(String categoryTitle, @Context CategoryService categoryService) {
        return categoryService.findByTitle(categoryTitle)
                .orElseThrow(() -> new ResourceNotFoundException("Категория с названием: " + categoryTitle + " не найдена"));
    }

    @Mapping(source = "category", target = "categoryTitle", qualifiedByName = "getCategoryTitle")
    ProductWsDto map(Product product);

    @Named("getCategoryTitle")
    default String getCategoryTitle(Category category) {
        return category.getTitle();
    }

    List<ProductWsDto> map(List<Product> products);
}
