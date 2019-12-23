package com.gfstabile.java.companyrest.service.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.repository.impl.CategoryRepository;
import com.gfstabile.java.companyrest.service.IService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The service bean related to Category entity
 *
 * @author G. F. Stabile
 */
@Service
public class CategoryService implements IService<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        if (Objects.nonNull(category) && category.isValid()) {
            this.categoryRepository.save(category);
        }
    }

    @Override
    public void update(Category category) {
        if (Objects.nonNull(category) && category.isValid()) {
            this.categoryRepository.updateCategory(category.getInternalCode(), category.getName());
        }
    }

    @Override
    public void deleteByInternalCode(String internalCode) {
        if (Strings.isNotBlank(internalCode)) {
            this.categoryRepository.deleteByInternalCode(internalCode);
        }
    }

    @Override
    public Optional<Category> getByInternalCode(String internalCode) {
        Optional<Category> response = Optional.empty();
        if (Strings.isNotBlank(internalCode)) {
            response = this.categoryRepository.findByInternalCode(internalCode);
        }
        return response;
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }
}
