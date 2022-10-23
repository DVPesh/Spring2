package ru.peshekhonov.online.store.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.peshekhonov.online.store.dto.ProductDto;
import ru.peshekhonov.online.store.entities.Product;
import ru.peshekhonov.online.store.mappers.ProductDtoMapper;
import ru.peshekhonov.online.store.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductDtoMapper mapper;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<ProductDto> findAllProductDto() {
        return mapper.map(productRepository.findAll());
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void createNewProduct(ProductDto productDto) {
        productRepository.save(mapper.map(productDto, categoryService));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }
}
