package ru.peshekhonov.product.service.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.peshekhonov.dto.ProductDto;
import ru.peshekhonov.product.service.entities.Category;
import ru.peshekhonov.product.service.entities.Product;
import ru.peshekhonov.product.service.exceptions.ResourceNotFoundException;
import ru.peshekhonov.product.service.services.CategoryService;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {

    @Mapping(source = "categoryTitle", target = "category", qualifiedByName = "getCategoryByTitle")
    Product map(ProductDto productDto, @Context CategoryService categoryService);

    @Named("getCategoryByTitle")
    default Category getCategoryByTitle(String categoryTitle, @Context CategoryService categoryService) {
        return categoryService.findByTitle(categoryTitle)
                .orElseThrow(() -> new ResourceNotFoundException("Категория с названием: " + categoryTitle + " не найдена"));
    }

    @Mapping(source = "category", target = "categoryTitle", qualifiedByName = "getCategoryTitle")
    ProductDto map(Product product);

    @Named("getCategoryTitle")
    default String getCategoryTitle(Category category) {
        return category.getTitle();
    }

    List<ProductDto> map(List<Product> products);
}
