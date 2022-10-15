package ru.peshekhonov.online.store.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.peshekhonov.online.store.entities.Category;
import ru.peshekhonov.online.store.repositories.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
