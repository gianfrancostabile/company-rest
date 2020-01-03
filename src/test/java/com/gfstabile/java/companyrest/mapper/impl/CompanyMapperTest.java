package com.gfstabile.java.companyrest.mapper.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.category.CategoryDTO;
import com.gfstabile.java.companyrest.entity.company.Company;
import com.gfstabile.java.companyrest.entity.company.CompanyRequestDTO;
import com.gfstabile.java.companyrest.entity.company.CompanyResponseDTO;
import com.gfstabile.java.companyrest.entity.country.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CompanyMapperTest {

    private static final Long ZERO_ID = 0L;
    private static final String CATEGORY_INTERNAL_CODE = "categoryIC";
    private static final String CATEGORY_NAME = "Category Name";
    private static final String COMPANY_INTERNAL_CODE = "1";
    private static final String COMPANY_NAME = "Name";
    private static final Country COMPANY_COUNTRY = Country.AR;

    @InjectMocks
    private CompanyMapper companyMapper;
    @Mock
    private CategoryMapper categoryMapper;

    private Company dummyCompany;
    private CompanyRequestDTO dummyCompanyRequestDTO;
    private CompanyResponseDTO dummyCompanyResponseDTO;
    private Category dummyCategory;
    private CategoryDTO dummyCategoryDTO;

    @BeforeEach
    public void setUp() {
        this.dummyCategory = Category.builder()
            .id(ZERO_ID)
            .internalCode(CATEGORY_INTERNAL_CODE)
            .name(CATEGORY_NAME)
            .build();
        this.dummyCategoryDTO = CategoryDTO.builder()
            .internalCode(CATEGORY_INTERNAL_CODE)
            .name(CATEGORY_NAME)
            .build();
        this.dummyCompany = Company.builder()
            .id(ZERO_ID)
            .internalCode(COMPANY_INTERNAL_CODE)
            .name(COMPANY_NAME)
            .country(COMPANY_COUNTRY)
            .category(this.dummyCategory)
            .build();
        this.dummyCompanyRequestDTO = CompanyRequestDTO.builder()
            .internalCode(COMPANY_INTERNAL_CODE)
            .name(COMPANY_NAME)
            .country(COMPANY_COUNTRY)
            .categoryInternalCode(CATEGORY_INTERNAL_CODE)
            .build();
        this.dummyCompanyResponseDTO = CompanyResponseDTO.builder()
            .internalCode(COMPANY_INTERNAL_CODE)
            .name(COMPANY_NAME)
            .country(COMPANY_COUNTRY)
            .categoryDTO(this.dummyCategoryDTO)
            .build();
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithCategoryInternalCode() {
        CompanyRequestDTO actualCompanyRequestDTO = this.companyMapper.fromEntityToDto(this.dummyCompany);
        assertEquals(this.dummyCompanyRequestDTO, actualCompanyRequestDTO,
            "fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithCategoryInternalCode");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithIdNull() {
        this.dummyCompany.setId(null);
        CompanyRequestDTO actualCompanyRequestDTO = this.companyMapper.fromEntityToDto(this.dummyCompany);

        assertEquals(this.dummyCompanyRequestDTO, actualCompanyRequestDTO,
            "fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithIdNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithInternalCodeNull() {
        this.dummyCompany.setInternalCode(null);
        this.dummyCompanyRequestDTO.setInternalCode(null);
        CompanyRequestDTO actualCompanyRequestDTO = this.companyMapper.fromEntityToDto(this.dummyCompany);
        assertEquals(this.dummyCompanyRequestDTO, actualCompanyRequestDTO,
            "fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithInternalCodeNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithNameNull() {
        this.dummyCompany.setName(null);
        this.dummyCompanyRequestDTO.setName(null);
        CompanyRequestDTO actualCompanyRequestDTO = this.companyMapper.fromEntityToDto(this.dummyCompany);
        assertEquals(this.dummyCompanyRequestDTO, actualCompanyRequestDTO,
            "fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithNameNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithCountryNull() {
        this.dummyCompany.setCountry(null);
        this.dummyCompanyRequestDTO.setCountry(null);
        CompanyRequestDTO actualCompanyRequestDTO = this.companyMapper.fromEntityToDto(this.dummyCompany);
        assertEquals(this.dummyCompanyRequestDTO, actualCompanyRequestDTO,
            "fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithCountryNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithCategoryNull() {
        this.dummyCompany.setCategory(null);
        this.dummyCompanyRequestDTO.setCategoryInternalCode(null);
        CompanyRequestDTO actualCompanyRequestDTO = this.companyMapper.fromEntityToDto(this.dummyCompany);
        assertEquals(this.dummyCompanyRequestDTO, actualCompanyRequestDTO,
            "fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithCategoryNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithCategoryInternalCodeNull() {
        this.dummyCompany.getCategory()
            .setInternalCode(null);
        this.dummyCompanyRequestDTO.setCategoryInternalCode(null);
        CompanyRequestDTO actualCompanyRequestDTO = this.companyMapper.fromEntityToDto(this.dummyCompany);
        assertEquals(this.dummyCompanyRequestDTO, actualCompanyRequestDTO,
            "fromEntityToDto_ReturnsCompanyRequestDTOWithSameValuesFromCompany_SendCompanyWithCategoryInternalCodeNull");
    }

    @Test
    public void fromEntityToDto_ReturnsNull_SendNull() {
        CompanyRequestDTO actualCompanyRequestDTO = this.companyMapper.fromEntityToDto(null);
        assertEquals(null, actualCompanyRequestDTO, "fromEntityToDto_ReturnsNull_SendNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyRequestDTO_SendCompanyRequestDTO() {
        this.dummyCompany.getCategory().setId(null);
        this.dummyCompany.getCategory().setName(null);
        Company actualCompany = this.companyMapper.fromDtoToEntity(this.dummyCompanyRequestDTO);
        assertEquals(this.dummyCompany, actualCompany,
            "fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyRequestDTO_SendCompanyRequestDTO");
    }

    @Test
    public void fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyRequestDTO_SendCompanyRequestDTOWithInternalCodeNull() {
        this.dummyCompanyRequestDTO.setInternalCode(null);
        this.dummyCompany.setInternalCode(null);
        this.dummyCompany.getCategory().setId(null);
        this.dummyCompany.getCategory().setName(null);
        Company actualCompany = this.companyMapper.fromDtoToEntity(this.dummyCompanyRequestDTO);
        assertEquals(this.dummyCompany, actualCompany,
            "fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyRequestDTO_SendCompanyRequestDTOWithInternalCodeNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyRequestDTO_SendCompanyRequestDTOWithNameNull() {
        this.dummyCompanyRequestDTO.setName(null);
        this.dummyCompany.setName(null);
        this.dummyCompany.getCategory().setId(null);
        this.dummyCompany.getCategory().setName(null);
        Company actualCompany = this.companyMapper.fromDtoToEntity(this.dummyCompanyRequestDTO);
        assertEquals(this.dummyCompany, actualCompany,
            "fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyRequestDTO_SendCompanyRequestDTOWithNameNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyRequestDTO_SendCompanyRequestDTOWithCountryNull() {
        this.dummyCompanyRequestDTO.setCountry(null);
        this.dummyCompany.setCountry(null);
        this.dummyCompany.getCategory().setId(null);
        this.dummyCompany.getCategory().setName(null);
        Company actualCompany = this.companyMapper.fromDtoToEntity(this.dummyCompanyRequestDTO);
        assertEquals(this.dummyCompany, actualCompany,
            "fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyRequestDTO_SendCompanyRequestDTOWithCountryNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyRequestDTO_SendCompanyRequestDTOWithCategoryInternalCodeNull() {
        this.dummyCompanyRequestDTO.setCategoryInternalCode(null);
        this.dummyCompany.setCategory(null);
        Company actualCompany = this.companyMapper.fromDtoToEntity(this.dummyCompanyRequestDTO);
        assertEquals(this.dummyCompany, actualCompany,
            "fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyRequestDTO_SendCompanyRequestDTOWithCategoryInternalCodeNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsNull_SendNull() {
        Company actualCompany = this.companyMapper.fromDtoToEntity(null);
        assertEquals(null, actualCompany, "fromDtoToEntity_ReturnsNull_SendNull");
    }

    @Test
    public void fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithAllValues() {
        when(this.categoryMapper.fromEntityToDto(this.dummyCategory)).thenReturn(this.dummyCategoryDTO);
        CompanyResponseDTO actualCompanyResponseDTO = this.companyMapper.fromEntityToResponseDto(this.dummyCompany);
        assertEquals(this.dummyCompanyResponseDTO, actualCompanyResponseDTO,
            "fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithAllValues");
    }

    @Test
    public void fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithIdNull() {
        when(this.categoryMapper.fromEntityToDto(this.dummyCategory)).thenReturn(this.dummyCategoryDTO);
        this.dummyCompany.setId(null);
        CompanyResponseDTO actualCompanyResponseDTO = this.companyMapper.fromEntityToResponseDto(this.dummyCompany);
        assertEquals(this.dummyCompanyResponseDTO, actualCompanyResponseDTO,
            "fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithIdNull");
    }

    @Test
    public void fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithInternalCodeNull() {
        when(this.categoryMapper.fromEntityToDto(this.dummyCategory)).thenReturn(this.dummyCategoryDTO);
        this.dummyCompany.setInternalCode(null);
        this.dummyCompanyResponseDTO.setInternalCode(null);
        CompanyResponseDTO actualCompanyResponseDTO = this.companyMapper.fromEntityToResponseDto(this.dummyCompany);
        assertEquals(this.dummyCompanyResponseDTO, actualCompanyResponseDTO,
            "fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithInternalCodeNull");
    }

    @Test
    public void fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithNameNull() {
        when(this.categoryMapper.fromEntityToDto(this.dummyCategory)).thenReturn(this.dummyCategoryDTO);
        this.dummyCompany.setName(null);
        this.dummyCompanyResponseDTO.setName(null);
        CompanyResponseDTO actualCompanyResponseDTO = this.companyMapper.fromEntityToResponseDto(this.dummyCompany);
        assertEquals(this.dummyCompanyResponseDTO, actualCompanyResponseDTO,
            "fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithNameNull");
    }

    @Test
    public void fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithCountryNull() {
        when(this.categoryMapper.fromEntityToDto(this.dummyCategory)).thenReturn(this.dummyCategoryDTO);
        this.dummyCompany.setCountry(null);
        this.dummyCompanyResponseDTO.setCountry(null);
        CompanyResponseDTO actualCompanyResponseDTO = this.companyMapper.fromEntityToResponseDto(this.dummyCompany);
        assertEquals(this.dummyCompanyResponseDTO, actualCompanyResponseDTO,
            "fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithCountryNull");
    }

    @Test
    public void fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithCategoryNull() {
        when(this.categoryMapper.fromEntityToDto(null)).thenReturn(null);
        this.dummyCompany.setCategory(null);
        this.dummyCompanyResponseDTO.setCategoryDTO(null);
        CompanyResponseDTO actualCompanyResponseDTO = this.companyMapper.fromEntityToResponseDto(this.dummyCompany);
        assertEquals(this.dummyCompanyResponseDTO, actualCompanyResponseDTO,
            "fromEntityToResponseDto_ReturnsCompanyWithSameValuesFromCompany_SendCompanyWithCountryNull");
    }

    @Test
    public void fromEntityToResponseDto_ReturnsNull_SendNull() {
        CompanyResponseDTO actualCompanyResponseDTO = this.companyMapper.fromEntityToResponseDto(null);
        assertEquals(null, actualCompanyResponseDTO, "fromEntityToResponseDto_ReturnsNull_SendNull");
    }
}
