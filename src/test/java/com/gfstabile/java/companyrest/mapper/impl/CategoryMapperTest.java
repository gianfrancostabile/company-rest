package com.gfstabile.java.companyrest.mapper.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.category.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {

    private static final Long ID = 0L;
    private static final String INTERNAL_CODE = "1";
    private static final String NAME = "Name";

    private CategoryMapper categoryMapper;

    private Category dummyCategory;
    private CategoryDTO dummyCategoryDTO;

    @BeforeEach
    public void setUp() {
        this.categoryMapper = new CategoryMapper();
        this.dummyCategory = Category.builder()
            .id(ID)
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .build();
        this.dummyCategoryDTO = CategoryDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .build();
    }

    @Test
    public void fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategory() {
        CategoryDTO actualCategoryDTO = this.categoryMapper.fromEntityToDto(this.dummyCategory);
        assertEquals(this.dummyCategoryDTO, actualCategoryDTO,
            "fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategory");
    }

    @Test
    public void fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithIdNull() {
        this.dummyCategory.setId(null);
        CategoryDTO actualCategoryDTO = this.categoryMapper.fromEntityToDto(this.dummyCategory);
        assertEquals(this.dummyCategoryDTO, actualCategoryDTO,
            "fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithIdNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithInternalCodeNull() {
        this.dummyCategory.setInternalCode(null);
        this.dummyCategoryDTO.setInternalCode(null);
        CategoryDTO actualCategoryDTO = this.categoryMapper.fromEntityToDto(this.dummyCategory);
        assertEquals(this.dummyCategoryDTO, actualCategoryDTO,
            "fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithInternalCodeNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithNameNull() {
        this.dummyCategory.setName(null);
        this.dummyCategoryDTO.setName(null);
        CategoryDTO actualCategoryDTO = this.categoryMapper.fromEntityToDto(this.dummyCategory);
        assertEquals(this.dummyCategoryDTO, actualCategoryDTO,
            "fromEntityToDto_ReturnsCategoryDTOWithSameInternalCodeAndName_SendCategoryWithNameNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategoryDTO() {
        Category actualCategory = this.categoryMapper.fromDtoToEntity(this.dummyCategoryDTO);
        assertEquals(this.dummyCategory, actualCategory,
            "fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategory");
    }

    @Test
    public void fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategoryWithInternalCodeNull() {
        this.dummyCategoryDTO.setInternalCode(null);
        this.dummyCategory.setInternalCode(null);
        Category actualCategory = this.categoryMapper.fromDtoToEntity(this.dummyCategoryDTO);
        assertEquals(this.dummyCategory, actualCategory,
            "fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategoryWithInternalCodeNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategoryWithNameNull() {
        this.dummyCategoryDTO.setName(null);
        this.dummyCategory.setName(null);
        Category actualCategory = this.categoryMapper.fromDtoToEntity(this.dummyCategoryDTO);
        assertEquals(this.dummyCategory, actualCategory,
            "fromDtoToEntity_ReturnsCategoryWithId0LAndSameInternalCodeAndName_SendCategoryWithNameNull");
    }
}
