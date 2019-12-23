package com.gfstabile.java.companyrest.mapper.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.category.CategoryDTO;
import com.gfstabile.java.companyrest.mapper.IMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper bean related to Category entity
 *
 * @author G. F. Stabile
 */
@Component
public class CategoryMapper implements IMapper<Category, CategoryDTO> {

    @Override
    public CategoryDTO fromEntityToDto(Category entity) {
        return new CategoryDTO(entity.getInternalCode(), entity.getName());
    }

    @Override
    public Category fromDtoToEntity(CategoryDTO dto) {
        return new Category(0L, dto.getInternalCode(), dto.getName());
    }
}
