package com.gfstabile.java.companyrest.service.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.exception.AbstractServiceException;
import com.gfstabile.java.companyrest.exception.impl.BlankInternalCodeException;
import com.gfstabile.java.companyrest.exception.impl.DuplicatedInternalCodeException;
import com.gfstabile.java.companyrest.exception.impl.category.CategoryNotFoundException;
import com.gfstabile.java.companyrest.exception.impl.category.InvalidCategoryException;
import com.gfstabile.java.companyrest.exception.impl.category.NullCategoryException;
import com.gfstabile.java.companyrest.repository.CategoryRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void save_SendValidCategory_DoesNotThrownAbstractServiceException() throws AbstractServiceException {
        Mockito.when(this.categoryRepository.save(any(Category.class)))
            .thenReturn(any(Category.class));
        Category dummyCategory = Category.builder()
            .id(0L)
            .internalCode("1")
            .name("Name")
            .build();

        this.categoryService.save(dummyCategory);
        assertTrue(true, "save_SendValidCategory_DoNotThrownException");
    }

    @Test
    public void save_SendExistingCategoryById_ThrownDuplicatedInternalCodeException() {
        Mockito.doThrow(ConstraintViolationException.class)
            .when(this.categoryRepository)
            .save(any(Category.class));
        Category dummyCategory = Category.builder()
            .id(1L)
            .internalCode("same-id")
            .name("Same id")
            .build();
        assertThrows(DuplicatedInternalCodeException.class, () -> this.categoryService.save(dummyCategory),
            "save_SendExistingCategoryByInternalCode_ThrownDuplicatedInternalCodeException");
    }

    @Test
    public void save_SendExistingCategoryByInternalCode_ThrownDuplicatedInternalCodeException() {
        Mockito.doThrow(ConstraintViolationException.class)
            .when(this.categoryRepository)
            .save(any(Category.class));
        Category dummyCategory = Category.builder()
            .id(0L)
            .internalCode("same-internal-code")
            .name("Same internal code")
            .build();

        assertThrows(DuplicatedInternalCodeException.class, () -> this.categoryService.save(dummyCategory),
            "save_SendExistingCategoryByInternalCode_ThrownDuplicatedInternalCodeException");
    }

    @Test
    public void save_SendNullCategory_ThrownNullCategoryException() {
        assertThrows(NullCategoryException.class, () -> this.categoryService.save(null),
            "save_SendInvalidCategory_ThrownInvalidCategoryException");
    }

    @Test
    public void save_SendInvalidCategory_ThrownInvalidCategoryException() {
        Category dummyCategory = Category.builder()
            .id(0L)
            .internalCode("")
            .name(null)
            .build();

        assertThrows(InvalidCategoryException.class, () -> this.categoryService.save(dummyCategory),
            "save_SendInvalidCategory_ThrownInvalidCategoryException");
    }

    @Test
    public void update_SendValidCategory_DoNotThrownException() throws AbstractServiceException {
        Mockito.when(this.categoryRepository.findByInternalCode(anyString()))
            .thenReturn(Optional.of(Category.builder()
                .build()));
        Mockito.doNothing()
            .when(this.categoryRepository)
            .updateCategory(anyString(), anyString());
        Category dummyCategory = Category.builder()
            .id(1L)
            .internalCode("1")
            .name("New name")
            .build();

        this.categoryService.update(dummyCategory);
        assertTrue(true, "update_SendValidCategory_DoNotThrownException");
    }

    @Test
    public void update_SendNonExistentCategory_ThrownCategoryNotFoundException() {
        Mockito.when(this.categoryRepository.findByInternalCode(anyString()))
            .thenReturn(Optional.empty());
        Category dummyCategory = Category.builder()
            .id(1L)
            .internalCode("non-existent-internal-code")
            .name("New name")
            .build();

        assertThrows(CategoryNotFoundException.class, () -> this.categoryService.update(dummyCategory));
    }

    @Test
    public void update_SendNullCategory_ThrownNullCategoryException() {
        assertThrows(NullCategoryException.class, () -> this.categoryService.update(null),
            "update_SendNullCategory_ThrownNullCategoryException");
    }

    @Test
    public void update_SendInvalidCategory_ThrownInvalidCategoryException() {
        Category dummyCategory = Category.builder()
            .id(0L)
            .internalCode("")
            .name(null)
            .build();

        assertThrows(InvalidCategoryException.class, () -> this.categoryService.update(dummyCategory),
            "update_SendInvalidCategory_ThrownInvalidCategoryException");
    }

    @Test
    public void delete_SendValidInternalCode_DoNotThrownException() throws AbstractServiceException {
        Mockito.when(this.categoryRepository.findByInternalCode(anyString()))
            .thenReturn(Optional.of(Category.builder()
                .build()));
        Mockito.doNothing()
            .when(this.categoryRepository)
            .deleteByInternalCode(anyString());

        this.categoryService.deleteByInternalCode("existing-internal-code");
        assertTrue(true, "delete_SendValidInternalCode_DoNotThrownException");
    }

    @Test
    public void delete_SendNonExistentInternalCode_ThrownCategoryNotFoundException() {
        Mockito.when(this.categoryRepository.findByInternalCode(anyString()))
            .thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,
            () -> this.categoryService.deleteByInternalCode("non-existent-internal-code"),
            "delete_SendNonExistentInternalCode_ThrownCategoryNotFoundException");
    }

    @Test
    public void delete_SendNullInternalCode_ThrownBlankInternalCodeException() {
        assertThrows(BlankInternalCodeException.class, () -> this.categoryService.deleteByInternalCode(null),
            "delete_SendNullInternalCode_ThrownBlankInternalCodeException");
    }

    @Test
    public void delete_SendBlankInternalCode_ThrownBlankInternalCodeException() {
        assertThrows(BlankInternalCodeException.class, () -> this.categoryService.deleteByInternalCode(" "),
            "delete_SendBlankInternalCode_ThrownBlankInternalCodeException");
    }

    @Test
    public void getByInternalCode_SendExistingInternalCode_ReturnOptionalWithACategory() {
        String internalCode = "existing-internal-code";
        Category dummyCategory = Category.builder()
            .id(1L)
            .internalCode(internalCode)
            .name("name")
            .build();
        Mockito.when(this.categoryRepository.findByInternalCode(internalCode))
            .thenReturn(Optional.of(dummyCategory));

        Optional<Category> actualOptionalCategory = this.categoryService.getByInternalCode(internalCode);
        if (actualOptionalCategory.isPresent()) {
            assertEquals(dummyCategory, actualOptionalCategory.get(),
                "getByInternalCode_SendExistingInternalCode_ReturnOptionalWithACategory");
        } else {
            fail("getByInternalCode_SendExistingInternalCode_ReturnOptionalWithACategory");
        }
    }

    @Test
    public void getByInternalCode_SendNonExistingInternalCode_ReturnEmptyOptional() {
        String internalCode = "existing-internal-code";
        Mockito.when(this.categoryRepository.findByInternalCode(internalCode))
            .thenReturn(Optional.empty());

        Optional<Category> actualOptionalCategory = this.categoryService.getByInternalCode(internalCode);
        assertFalse(actualOptionalCategory.isPresent(),
            "getByInternalCode_SendNonExistingInternalCode_ReturnEmptyOptional");
    }

    @Test
    public void getByInternalCode_SendNullInternalCode_ReturnEmptyOptional() {
        Optional<Category> actualOptionalCategory = this.categoryService.getByInternalCode(null);
        assertFalse(actualOptionalCategory.isPresent(), "getByInternalCode_SendNullInternalCode_ReturnEmptyOptional");
    }

    @Test
    public void getAll_ReturnListOfThreeCategories() {
        List<Category> expectedCategoryList = new ArrayList<>();
        expectedCategoryList.add(Category.builder()
            .id(1L)
            .internalCode("1")
            .name("1")
            .build());
        expectedCategoryList.add(Category.builder()
            .id(2L)
            .internalCode("2")
            .name("2")
            .build());
        expectedCategoryList.add(Category.builder()
            .id(3L)
            .internalCode("3")
            .name("3")
            .build());
        Mockito.when(this.categoryRepository.findAll())
            .thenReturn(expectedCategoryList);

        List<Category> actualCategoryList = this.categoryService.getAll();
        assertEquals(expectedCategoryList, actualCategoryList, "getAll_ReturnListOfThreeCategories");
    }

    @Test
    public void getAll_ReturnListOfOneCategory() {
        List<Category> expectedCategoryList = new ArrayList<>();
        expectedCategoryList.add(Category.builder()
            .id(1L)
            .internalCode("1")
            .name("1")
            .build());
        Mockito.when(this.categoryRepository.findAll())
            .thenReturn(expectedCategoryList);

        List<Category> actualCategoryList = this.categoryService.getAll();
        assertEquals(expectedCategoryList, actualCategoryList, "getAll_ReturnListOfOneCategory");
    }

    @Test
    public void getAll_ReturnEmpty() {
        List<Category> expectedCategoryList = new ArrayList<>();
        Mockito.when(this.categoryRepository.findAll())
            .thenReturn(expectedCategoryList);

        List<Category> actualCategoryList = this.categoryService.getAll();
        assertEquals(expectedCategoryList, actualCategoryList, "getAll_ReturnEmpty");
    }
}
