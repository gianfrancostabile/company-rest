package com.gfstabile.java.companyrest.controller.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.category.CategoryDTO;
import com.gfstabile.java.companyrest.exception.impl.DuplicatedInternalCodeException;
import com.gfstabile.java.companyrest.exception.impl.category.CategoryNotFoundException;
import com.gfstabile.java.companyrest.mapper.impl.CategoryMapper;
import com.gfstabile.java.companyrest.service.impl.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    private static final String URI_PREFIX = "/category/";
    private static final String URI_PREFIX_INTERNAL_CODE = URI_PREFIX + "{internal_code}";
    private static final Category DUMMY_CATEGORY = Category.builder()
        .id(1L)
        .internalCode("1")
        .name("Name")
        .build();
    private static final CategoryDTO DUMMY_CATEGORY_DTO = CategoryDTO.builder()
        .internalCode("1")
        .name("Name")
        .build();
    private final static LocalDateTime LOCAL_DATE = LocalDateTime.of(2000, 1, 1, 1, 0, 0);

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        Mockito.when(this.categoryMapper.fromEntityToDto(DUMMY_CATEGORY))
            .thenReturn(DUMMY_CATEGORY_DTO);
        Mockito.when(this.categoryMapper.fromDtoToEntity(DUMMY_CATEGORY_DTO))
            .thenReturn(DUMMY_CATEGORY);
    }

    @Test
    public void save_SendValidCategoryDTO_Return201StatusCode() throws Exception {
        Mockito.doNothing()
            .when(this.categoryService)
            .save(any(Category.class));
        this.mockMvc.perform(post(URI_PREFIX).content(DUMMY_CATEGORY_DTO.toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated());
    }

    @Test
    public void save_SendExistingCategoryDTO_Return400StatusCode() throws Exception {
        Mockito.doThrow(DuplicatedInternalCodeException.class)
            .when(this.categoryService)
            .save(any(Category.class));
        this.mockMvc.perform(post(URI_PREFIX).content(DUMMY_CATEGORY_DTO.toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void save_SendNullCategoryDTO_Return400StatusCode() throws Exception {
        this.mockMvc.perform(post(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(
                content().json("{\"errors\":[\"The body is mandatory or the body structure is not tolerated\"]}"));
    }

    @Test
    public void save_SendInvalidCategoryDTO_Return400StatusCode() throws Exception {
        this.mockMvc.perform(post(URI_PREFIX).content(Category.builder()
            .build()
            .toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(
                "{\"errors\":[\"Name cannot be null or empty\", \"InternalCode cannot be null or empty\"]}"));
    }

    @Test
    public void save_SendInvalidObject_Return400StatusCode() throws Exception {
        this.mockMvc.perform(post(URI_PREFIX).content("content")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(
                content().json("{\"errors\":[\"The body is mandatory or the body structure is not tolerated\"]}"));
    }

    @Test
    public void update_SendValidCategoryDTO_Return204StatusCode() throws Exception {
        Mockito.doNothing()
            .when(this.categoryService)
            .update(any(Category.class));
        this.mockMvc.perform(put(URI_PREFIX).content(DUMMY_CATEGORY_DTO.toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNoContent());
    }

    @Test
    public void update_SendNonExistingCategoryDTO_Return400StatusCode() throws Exception {
        Mockito.doThrow(CategoryNotFoundException.class)
            .when(this.categoryService)
            .update(any(Category.class));
        this.mockMvc.perform(put(URI_PREFIX).content(DUMMY_CATEGORY_DTO.toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void update_SendNullCategoryDTO_Return400StatusCode() throws Exception {
        this.mockMvc.perform(put(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(
                content().json("{\"errors\":[\"The body is mandatory or the body structure is not tolerated\"]}"));
    }

    @Test
    public void update_SendInvalidCategoryDTO_Return400StatusCode() throws Exception {
        this.mockMvc.perform(put(URI_PREFIX).content(Category.builder()
            .build()
            .toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(
                "{\"errors\":[\"Name cannot be null or empty\", \"InternalCode cannot be null or empty\"]}"));
    }

    @Test
    public void update_SendInvalidObject_Return400StatusCode() throws Exception {
        this.mockMvc.perform(put(URI_PREFIX).content("content")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(
                content().json("{\"errors\":[\"The body is mandatory or the body structure is not tolerated\"]}"));
    }

    @Test
    public void deleteByInternalCode_SendValidInternalCode_Return204StatusCode() throws Exception {
        Mockito.doNothing()
            .when(this.categoryService)
            .deleteByInternalCode(anyString());
        Mockito.when(this.categoryService.getByInternalCode(anyString()))
            .thenReturn(Optional.empty());
        this.mockMvc.perform(delete(URI_PREFIX_INTERNAL_CODE, "1"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void deleteByInternalCode_SendNonExistingInternalCode_Return400StatusCode() throws Exception {
        Mockito.doThrow(CategoryNotFoundException.class)
            .when(this.categoryService)
            .deleteByInternalCode(anyString());
        this.mockMvc.perform(delete(URI_PREFIX_INTERNAL_CODE, "2"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteByInternalCode_SendExistingInternalCodeButTheResourceWasNotDeleted_Return500StatusCode()
        throws Exception {
        Mockito.doNothing()
            .when(this.categoryService)
            .deleteByInternalCode(anyString());
        Mockito.when(this.categoryService.getByInternalCode(anyString()))
            .thenReturn(Optional.of(Category.builder()
                .build()));
        this.mockMvc.perform(delete(URI_PREFIX_INTERNAL_CODE, "1"))
            .andExpect(status().isInternalServerError());
    }

    @Test
    public void deleteByInternalCode_SendNullInternalCode_Return405StatusCode() throws Exception {
        this.mockMvc.perform(delete(URI_PREFIX))
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"errors\":[\"Request method 'DELETE' not supported\"]}"));
    }

    @Test
    public void deleteByInternalCode_SendBlankInternalCode_Return405StatusCode() throws Exception {
        this.mockMvc.perform(delete(URI_PREFIX_INTERNAL_CODE, ""))
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"errors\":[\"Request method 'DELETE' not supported\"]}"));
    }

    @Test
    public void getByInternalCode_SendValidInternalCode_ReturnCategoryAnd200StatusCode() throws Exception {
        Mockito.when(this.categoryService.getByInternalCode(anyString()))
            .thenReturn(Optional.of(DUMMY_CATEGORY));
        this.mockMvc.perform(get(URI_PREFIX_INTERNAL_CODE, "1"))
            .andExpect(status().isOk());
    }

    @Test
    public void getByInternalCode_SendNonExistingInternalCode_Return204StatusCode() throws Exception {
        Mockito.when(this.categoryService.getByInternalCode(anyString()))
            .thenReturn(Optional.empty());
        this.mockMvc.perform(get(URI_PREFIX_INTERNAL_CODE, "2"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void getAll_ReturnListOfThreeCategoriesAnd200StatusCode() throws Exception {
        Category dummyCategoryOne = Category.builder()
            .id(1L)
            .internalCode("1")
            .name("1")
            .build();
        Category dummyCategoryTwo = Category.builder()
            .id(2L)
            .internalCode("2")
            .name("2")
            .build();
        Category dummyCategoryThree = Category.builder()
            .id(3L)
            .internalCode("3")
            .name("3")
            .build();
        List<Category> dummyCategoryList = new ArrayList<>();
        dummyCategoryList.add(dummyCategoryOne);
        dummyCategoryList.add(dummyCategoryTwo);
        dummyCategoryList.add(dummyCategoryThree);

        CategoryDTO dummyCategoryDTOOne = CategoryDTO.builder()
            .internalCode("1")
            .name("1")
            .build();
        CategoryDTO dummyCategoryDTOTwo = CategoryDTO.builder()
            .internalCode("2")
            .name("2")
            .build();
        CategoryDTO dummyCategoryDTOThree = CategoryDTO.builder()
            .internalCode("3")
            .name("3")
            .build();
        List<CategoryDTO> expectedCategoryDTOList = new ArrayList<>();
        expectedCategoryDTOList.add(dummyCategoryDTOOne);
        expectedCategoryDTOList.add(dummyCategoryDTOTwo);
        expectedCategoryDTOList.add(dummyCategoryDTOThree);

        Mockito.when(this.categoryService.getAll())
            .thenReturn(dummyCategoryList);
        Mockito.when(this.categoryMapper.fromEntityToDto(dummyCategoryOne))
            .thenReturn(dummyCategoryDTOOne);
        Mockito.when(this.categoryMapper.fromEntityToDto(dummyCategoryTwo))
            .thenReturn(dummyCategoryDTOTwo);
        Mockito.when(this.categoryMapper.fromEntityToDto(dummyCategoryThree))
            .thenReturn(dummyCategoryDTOThree);

        this.mockMvc.perform(get(URI_PREFIX))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedCategoryDTOList.toString()));
    }

    @Test
    public void getAll_ReturnListOfOneCategoryAnd200StatusCode() throws Exception {
        Category dummyCategoryOne = Category.builder()
            .id(1L)
            .internalCode("1")
            .name("1")
            .build();
        List<Category> dummyCategoryList = new ArrayList<>();
        dummyCategoryList.add(dummyCategoryOne);

        CategoryDTO dummyCategoryDTOOne = CategoryDTO.builder()
            .internalCode("1")
            .name("1")
            .build();
        List<CategoryDTO> expectedCategoryDTOList = new ArrayList<>();
        expectedCategoryDTOList.add(dummyCategoryDTOOne);

        Mockito.when(this.categoryService.getAll())
            .thenReturn(dummyCategoryList);
        Mockito.when(this.categoryMapper.fromEntityToDto(dummyCategoryOne))
            .thenReturn(dummyCategoryDTOOne);

        this.mockMvc.perform(get(URI_PREFIX))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedCategoryDTOList.toString()));
    }

    @Test
    public void getAll_ReturnEmptylISTAnd204StatusCode() throws Exception {
        List<Category> expectedCategoryList = new ArrayList<>();
        Mockito.when(this.categoryService.getAll())
            .thenReturn(expectedCategoryList);

        this.mockMvc.perform(get(URI_PREFIX))
            .andExpect(status().isNoContent())
            .andExpect(content().json("[]"));
    }
}
