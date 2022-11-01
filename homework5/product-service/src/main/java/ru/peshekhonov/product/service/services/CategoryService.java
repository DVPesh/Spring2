package ru.peshekhonov.product.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.peshekhonov.product.service.entities.Category;
import ru.peshekhonov.product.service.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public List<String> findAllTitles() {
        return categoryRepository.findAll().stream().map(Category::getTitle).toList();
    }
}
