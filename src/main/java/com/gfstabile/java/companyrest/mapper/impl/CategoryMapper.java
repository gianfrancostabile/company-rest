package com.gfstabile.java.companyrest.mapper.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.category.CategoryDTO;
import com.gfstabile.java.companyrest.mapper.IMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Mapper bean related to Category entity
 *
 * @author G. F. Stabile
 */
@Component
public class CategoryMapper implements IMapper<Category, CategoryDTO> {

    @Override
    public CategoryDTO fromEntityToDto(Category entity) {
        return Objects.nonNull(entity) ? CategoryDTO.builder()
            .internalCode(entity.getInternalCode())
            .name(entity.getName())
            .build() : null;
    }

    @Override
    public Category fromDtoToEntity(CategoryDTO dto) {
        return Objects.nonNull(dto) ? Category.builder()
            .id(0L)
            .internalCode(dto.getInternalCode())
            .name(dto.getName())
            .build() : null;
    }
}
