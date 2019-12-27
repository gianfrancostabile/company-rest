package com.gfstabile.java.companyrest.mapper.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.category.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {

    private static final Long ID = 1L;
    private static final String INTERNAL_CODE = "1";
    private static final String NAME = "Name";
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        this.categoryMapper = new CategoryMapper();
    }

    @Test
    public void fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategory() {
        Category dummyCategory = Category.builder()
            .id(ID)
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .build();
        CategoryDTO actualCategoryDTO = this.categoryMapper.fromEntityToDto(dummyCategory);
        CategoryDTO expectedCategoryDTO = CategoryDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .build();

        assertEquals(expectedCategoryDTO, actualCategoryDTO,
            "fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategory");
    }

    @Test
    public void fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithIdNull() {
        Category dummyCategory = Category.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .build();
        CategoryDTO actualCategoryDTO = this.categoryMapper.fromEntityToDto(dummyCategory);
        CategoryDTO expectedCategoryDTO = CategoryDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .build();

        assertEquals(expectedCategoryDTO, actualCategoryDTO,
            "fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithIdNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithInternalCodeNull() {
        Category dummyCategory = Category.builder()
            .id(ID)
            .name(NAME)
            .build();
        CategoryDTO actualCategoryDTO = this.categoryMapper.fromEntityToDto(dummyCategory);
        CategoryDTO expectedCategoryDTO = CategoryDTO.builder()
            .name(NAME)
            .build();

        assertEquals(expectedCategoryDTO, actualCategoryDTO,
            "fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithInternalCodeNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithNameNull() {
        Category dummyCategory = Category.builder()
            .id(ID)
            .internalCode(INTERNAL_CODE)
            .build();
        CategoryDTO actualCategoryDTO = this.categoryMapper.fromEntityToDto(dummyCategory);
        CategoryDTO expectedCategoryDTO = CategoryDTO.builder()
            .internalCode(INTERNAL_CODE)
            .build();

        assertEquals(expectedCategoryDTO, actualCategoryDTO,
            "fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithNameNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategoryDTO() {
        CategoryDTO dummyCategoryDTO = CategoryDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .build();
        Category actualCategory = this.categoryMapper.fromDtoToEntity(dummyCategoryDTO);
        Category expectedCategory = Category.builder()
            .id(0L)
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .build();

        assertEquals(actualCategory, expectedCategory,
            "fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategory");
    }

    @Test
    public void fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategoryWithInternalCodeNull() {
        CategoryDTO dummyCategoryDTO = CategoryDTO.builder()
            .name(NAME)
            .build();
        Category actualCategory = this.categoryMapper.fromDtoToEntity(dummyCategoryDTO);
        Category expectedCategory = Category.builder()
            .id(0L)
            .name(NAME)
            .build();

        assertEquals(actualCategory, expectedCategory,
            "fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategoryWithInternalCodeNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategoryWithNameNull() {
        CategoryDTO dummyCategoryDTO = CategoryDTO.builder()
            .internalCode(INTERNAL_CODE)
            .build();
        Category actualCategory = this.categoryMapper.fromDtoToEntity(dummyCategoryDTO);
        Category expectedCategory = Category.builder()
            .id(0L)
            .internalCode(INTERNAL_CODE)
            .build();

        assertEquals(actualCategory, expectedCategory,
            "fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategoryWithNameNull");
    }
}
