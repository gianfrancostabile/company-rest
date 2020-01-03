package com.gfstabile.java.companyrest.service.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.exception.AbstractServiceException;
import com.gfstabile.java.companyrest.exception.impl.BlankInternalCodeException;
import com.gfstabile.java.companyrest.exception.impl.DuplicatedInternalCodeException;
import com.gfstabile.java.companyrest.exception.impl.category.CategoryNotFoundException;
import com.gfstabile.java.companyrest.exception.impl.category.InvalidCategoryException;
import com.gfstabile.java.companyrest.exception.impl.category.NullCategoryException;
import com.gfstabile.java.companyrest.repository.CategoryRepository;
import com.gfstabile.java.companyrest.service.IService;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.exception.ConstraintViolationException;
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
    public void save(Category category) throws AbstractServiceException {
        if (Objects.isNull(category)) {
            throw new NullCategoryException();
        } else if (!category.isValid()) {
            throw new InvalidCategoryException();
        }
        try {
            this.categoryRepository.save(category);
        } catch (Exception exception) {
            throw new DuplicatedInternalCodeException();
        }
    }

    @Override
    public void update(Category category) throws AbstractServiceException {
        if (Objects.isNull(category)) {
            throw new NullCategoryException();
        } else if (!category.isValid()) {
            throw new InvalidCategoryException();
        } else if (!this.categoryRepository.findByInternalCode(category.getInternalCode())
            .isPresent()) {
            throw new CategoryNotFoundException();
        }
        this.categoryRepository.updateCategory(category.getInternalCode(), category.getName());
    }

    @Override
    public void deleteByInternalCode(String internalCode) throws AbstractServiceException {
        if (Strings.isBlank(internalCode)) {
            throw new BlankInternalCodeException();
        } else if (!this.categoryRepository.findByInternalCode(internalCode)
            .isPresent()) {
            throw new CategoryNotFoundException();
        }
        this.categoryRepository.deleteByInternalCode(internalCode);
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
