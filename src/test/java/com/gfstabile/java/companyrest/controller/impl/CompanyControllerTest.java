package com.gfstabile.java.companyrest.controller.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.category.CategoryDTO;
import com.gfstabile.java.companyrest.entity.company.Company;
import com.gfstabile.java.companyrest.entity.company.CompanyRequestDTO;
import com.gfstabile.java.companyrest.entity.company.CompanyResponseDTO;
import com.gfstabile.java.companyrest.entity.country.Country;
import com.gfstabile.java.companyrest.exception.impl.DuplicatedInternalCodeException;
import com.gfstabile.java.companyrest.exception.impl.company.CompanyNotFoundException;
import com.gfstabile.java.companyrest.mapper.impl.CompanyMapper;
import com.gfstabile.java.companyrest.service.impl.CategoryService;
import com.gfstabile.java.companyrest.service.impl.CompanyService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    private static final String URI_PREFIX = "/company/";
    private static final String URI_PREFIX_INTERNAL_CODE = URI_PREFIX + "{internal_code}";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private CompanyService companyService;
    @MockBean
    private CompanyMapper companyMapper;

    @BeforeEach
    public void setUp() {
        when(this.companyMapper.fromDtoToEntity(this.getDummyCompanyDTO()))
            .thenReturn(this.getDummyCompany());
        when(this.companyMapper.fromEntityToDto(this.getDummyCompany()))
            .thenReturn(this.getDummyCompanyDTO());
        when(this.categoryService.getByInternalCode("1"))
            .thenReturn(Optional.of(this.getDummyCategory()));
        when(this.categoryService.getByInternalCode("empty"))
            .thenReturn(Optional.empty());
    }

    @Test
    public void save_SendValidCompanyDTO_Return201StatusCode() throws Exception {
        Mockito.doNothing()
            .when(this.companyService)
            .save(any(Company.class));

        this.mockMvc.perform(post(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.getDummyCompanyDTO()
                .toString()))
            .andExpect(status().isCreated());
    }

    @Test
    public void save_SendExistingCompanyDTO_Return400StatusCode() throws Exception {
        Mockito.doThrow(DuplicatedInternalCodeException.class)
            .when(this.companyService)
            .save(any(Company.class));

        this.mockMvc.perform(post(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.getDummyCompanyDTO()
                .toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void save_SendNonExistingCategoryInternalCode_Return400StatusCode() throws Exception {
        CompanyRequestDTO dummyCompanyRequestDTO = this.getDummyCompanyDTO();
        dummyCompanyRequestDTO.setCategoryInternalCode("empty");
        when(this.categoryService.getByInternalCode(anyString())).thenReturn(Optional.empty());

        this.mockMvc.perform(post(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(dummyCompanyRequestDTO.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void save_SendInvalidCompanyDTO_ReturnErrorListAnd400StatusCode() throws Exception {
        CompanyRequestDTO dummyInvalidCompanyRequestDTO = this.getDummyCompanyDTO();
        dummyInvalidCompanyRequestDTO.setCategoryInternalCode(null);
        dummyInvalidCompanyRequestDTO.setInternalCode("");

        this.mockMvc.perform(post(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(dummyInvalidCompanyRequestDTO.toString()))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(
                "{\"errors\":[\"CategoryInternalCode cannot be null or empty\",\"InternalCode cannot be null or empty\"]}"));
    }

    @Test
    public void save_SendNullCompanyDTO_ReturnErrorListAnd400StatusCode() throws Exception {
        this.mockMvc.perform(post(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(
                content().json("{\"errors\":[ \"The body is mandatory or the body structure is not tolerated\"]}"));
    }

    @Test
    public void save_SendInvalidObject_ReturnErrorListAnd400StatusCode() throws Exception {
        this.mockMvc.perform(post(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("content"))
            .andExpect(status().isBadRequest())
            .andExpect(
                content().json("{\"errors\":[\"The body is mandatory or the body structure is not tolerated\"]}"));
    }

    @Test
    public void update_SendExistingCompanyDTO_Return204StatusCode() throws Exception {
        Mockito.doNothing()
            .when(this.companyService)
            .update(any(Company.class));

        this.mockMvc.perform(put(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.getDummyCompanyDTO()
                .toString()))
            .andExpect(status().isNoContent());
    }

    @Test
    public void update_SendNonExistingCompanyDTO_Return400StatusCode() throws Exception {
        Mockito.doThrow(CompanyNotFoundException.class)
            .when(this.companyService)
            .update(any(Company.class));

        this.mockMvc.perform(put(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.getDummyCompanyDTO()
                .toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void update_SendNonExistingCategoryInternalCode_Return400StatusCode() throws Exception {
        CompanyRequestDTO dummyCompanyRequestDTO = this.getDummyCompanyDTO();
        dummyCompanyRequestDTO.setCategoryInternalCode("empty");
        when(this.categoryService.getByInternalCode(anyString())).thenReturn(Optional.empty());

        this.mockMvc.perform(put(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(dummyCompanyRequestDTO.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void update_SendInvalidCompanyDTO_ReturnErrorListAnd400StatusCode() throws Exception {
        CompanyRequestDTO dummyInvalidCompanyRequestDTO = this.getDummyCompanyDTO();
        dummyInvalidCompanyRequestDTO.setCategoryInternalCode(null);
        dummyInvalidCompanyRequestDTO.setInternalCode("");

        this.mockMvc.perform(put(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(dummyInvalidCompanyRequestDTO.toString()))
            .andExpect(status().isBadRequest())
            .andExpect(content().json(
                "{\"errors\":[\"CategoryInternalCode cannot be null or empty\",\"InternalCode cannot be null or empty\"]}"));
    }

    @Test
    public void update_SendNullCompanyDTO_ReturnErrorListAnd400StatusCode() throws Exception {
        this.mockMvc.perform(put(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(
                content().json("{\"errors\":[\"The body is mandatory or the body structure is not tolerated\"]}"));
    }

    @Test
    public void update_SendInvalidObject_ReturnErrorListAnd400StatusCode() throws Exception {
        this.mockMvc.perform(put(URI_PREFIX).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("content"))
            .andExpect(status().isBadRequest())
            .andExpect(
                content().json("{\"errors\":[\"The body is mandatory or the body structure is not tolerated\"]}"));
    }

    @Test
    public void deleteByInternalCode_SendExistingInternalCode_Return204StatusCode() throws Exception {
        Mockito.doNothing()
            .when(this.companyService)
            .deleteByInternalCode(anyString());
        when(this.companyService.getByInternalCode(anyString()))
            .thenReturn(Optional.empty());
        this.mockMvc.perform(delete(URI_PREFIX_INTERNAL_CODE, "1"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void deleteByInternalCode_SendNonExistingInternalCode_Return400StatusCode() throws Exception {
        Mockito.doThrow(CompanyNotFoundException.class)
            .when(this.companyService)
            .deleteByInternalCode(anyString());
        when(this.companyService.getByInternalCode(anyString()))
            .thenReturn(Optional.empty());
        this.mockMvc.perform(delete(URI_PREFIX_INTERNAL_CODE, "2"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteByInternalCode_SendExistingInternalCodeButItWasNotRemoved_Return500StatusCode() throws Exception {
        Mockito.doNothing()
            .when(this.companyService)
            .deleteByInternalCode(anyString());
        when(this.companyService.getByInternalCode(anyString()))
            .thenReturn(Optional.of(Company.builder()
                .build()));
        this.mockMvc.perform(delete(URI_PREFIX_INTERNAL_CODE, "1"))
            .andExpect(status().isInternalServerError());
    }

    @Test
    public void deleteByInternalCode_SendNullInternalCode_Return400StatusCode() throws Exception {
        this.mockMvc.perform(delete(URI_PREFIX))
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"errors\":[\"Request method 'DELETE' not supported\"]}"));
    }

    @Test
    public void deleteByInternalCode_SendBlankInternalCode_Return400StatusCode() throws Exception {
        this.mockMvc.perform(delete(URI_PREFIX_INTERNAL_CODE, ""))
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"errors\":[\"Request method 'DELETE' not supported\"]}"));
    }

    @Test
    public void getByInternalCode_SendExistingInternalCode_ReturnCompanyAnd200StatusCode() throws Exception {
        CompanyResponseDTO companyResponseDTO = CompanyResponseDTO.builder()
            .internalCode("1")
            .name("Company Name")
            .country(Country.AR)
            .categoryDTO(CategoryDTO.builder()
                .internalCode("1")
                .name("Category Name")
                .build())
            .build();

        when(this.companyService.getByInternalCode(anyString())).thenReturn(Optional.of(this.getDummyCompany()));
        when(this.companyMapper.fromEntityToResponseDto(any(Company.class))).thenReturn(companyResponseDTO);

        this.mockMvc.perform(get(URI_PREFIX_INTERNAL_CODE, "1"))
            .andExpect(status().isOk())
            .andExpect(content().json(companyResponseDTO.toString()));
    }

    @Test
    public void getByInternalCode_SendNonExistingInternalCode_Return204StatusCode() throws Exception {
        when(this.companyService.getByInternalCode(anyString()))
            .thenReturn(Optional.empty());
        this.mockMvc.perform(get(URI_PREFIX_INTERNAL_CODE, "2"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void getAll_ReturnListOfThreeCompanyDTOAnd200StatusCode() throws Exception {
        Company dummyCompanyOne = Company.builder()
            .id(1L)
            .internalCode("1")
            .name("1")
            .country(Country.AR)
            .category(this.getDummyCategory())
            .build();
        Company dummyCompanyTwo = Company.builder()
            .id(2L)
            .internalCode("2")
            .name("2")
            .country(Country.BR)
            .category(this.getDummyCategory())
            .build();
        Company dummyCompanyThree = Company.builder()
            .id(3L)
            .internalCode("3")
            .name("3")
            .country(Country.US)
            .category(this.getDummyCategory())
            .build();
        List<Company> dummyCompanyList = new ArrayList<>();
        dummyCompanyList.add(dummyCompanyOne);
        dummyCompanyList.add(dummyCompanyTwo);
        dummyCompanyList.add(dummyCompanyThree);

        CategoryDTO categoryDTO = CategoryDTO.builder()
            .internalCode("1")
            .name("Category Name")
            .build();

        CompanyResponseDTO dummyCompanyResponseDTOOne = CompanyResponseDTO.builder()
            .internalCode("1")
            .name("1")
            .country(Country.AR)
            .categoryDTO(categoryDTO)
            .build();
        CompanyResponseDTO dummyCompanyResponseDTOTwo = CompanyResponseDTO.builder()
            .internalCode("2")
            .name("2")
            .country(Country.BR)
            .categoryDTO(categoryDTO)
            .build();
        CompanyResponseDTO dummyCompanyResponseDTOThree = CompanyResponseDTO.builder()
            .internalCode("3")
            .name("3")
            .country(Country.US)
            .categoryDTO(categoryDTO)
            .build();
        List<CompanyResponseDTO> expectedCompanyResponseDTOList = new ArrayList<>();
        expectedCompanyResponseDTOList.add(dummyCompanyResponseDTOOne);
        expectedCompanyResponseDTOList.add(dummyCompanyResponseDTOTwo);
        expectedCompanyResponseDTOList.add(dummyCompanyResponseDTOThree);

        when(this.companyService.getAll()).thenReturn(dummyCompanyList);
        when(this.companyMapper.fromEntityToResponseDto(dummyCompanyOne)).thenReturn(dummyCompanyResponseDTOOne);
        when(this.companyMapper.fromEntityToResponseDto(dummyCompanyTwo)).thenReturn(dummyCompanyResponseDTOTwo);
        when(this.companyMapper.fromEntityToResponseDto(dummyCompanyThree)).thenReturn(dummyCompanyResponseDTOThree);

        this.mockMvc.perform(get(URI_PREFIX))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedCompanyResponseDTOList.toString()));
    }

    @Test
    public void getAll_ReturnListOfOneCompanyDTOAnd200StatusCode() throws Exception {
        Company dummyCompanyOne = Company.builder()
            .id(1L)
            .internalCode("1")
            .name("1")
            .country(Country.AR)
            .category(this.getDummyCategory())
            .build();
        List<Company> dummyCompanyList = new ArrayList<>();
        dummyCompanyList.add(dummyCompanyOne);

        CompanyResponseDTO dummyCompanyResponseDTOOne = CompanyResponseDTO.builder()
            .internalCode("1")
            .name("1")
            .country(Country.AR)
            .categoryDTO(CategoryDTO.builder()
                .internalCode("1")
                .name("Category Name")
                .build())
            .build();
        List<CompanyResponseDTO> expectedCompanyResponseDTOList = new ArrayList<>();
        expectedCompanyResponseDTOList.add(dummyCompanyResponseDTOOne);

        when(this.companyService.getAll()).thenReturn(dummyCompanyList);
        when(this.companyMapper.fromEntityToResponseDto(dummyCompanyOne)).thenReturn(dummyCompanyResponseDTOOne);

        this.mockMvc.perform(get(URI_PREFIX))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedCompanyResponseDTOList.toString()));
    }

    @Test
    public void getAll_ReturnEmptyListAnd204StatusCode() throws Exception {
        List<Company> dummyCompanyList = new ArrayList<>();
        List<CompanyResponseDTO> expectedCompanyResponseDTOList = new ArrayList<>();

        when(this.companyService.getAll())
            .thenReturn(dummyCompanyList);

        this.mockMvc.perform(get(URI_PREFIX))
            .andExpect(status().isNoContent())
            .andExpect(content().json(expectedCompanyResponseDTOList.toString()));
    }

    public Category getDummyCategory() {
        return Category.builder()
            .id(1L)
            .internalCode("1")
            .name("Name")
            .build();
    }

    public CompanyRequestDTO getDummyCompanyDTO() {
        return CompanyRequestDTO.builder()
            .internalCode("1")
            .name("Company Name")
            .country(Country.AR)
            .categoryInternalCode("1")
            .build();
    }

    public Company getDummyCompany() {
        return Company.builder()
            .id(1L)
            .internalCode("1")
            .name("Company Name")
            .country(Country.AR)
            .category(Category.builder()
                .id(0L)
                .internalCode("1")
                .name("Category Name")
                .build())
            .build();
    }
}
