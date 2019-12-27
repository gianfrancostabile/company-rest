package com.gfstabile.java.companyrest.mapper.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.company.Company;
import com.gfstabile.java.companyrest.entity.company.CompanyDTO;
import com.gfstabile.java.companyrest.entity.country.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyMapperTest {

    private static final Long ID = 1L;
    private static final Long ZERO_ID = 0L;
    private static final String INTERNAL_CODE = "1";
    private static final String NAME = "Name";
    private static final Country COUNTRY = Country.AR;
    private static final String CATEGORY_INTERNAL_CODE = "categoryIC";
    private static final Category DUMMY_CATEGORY = Category.builder()
        .internalCode(CATEGORY_INTERNAL_CODE)
        .build();
    private CompanyMapper companyMapper;

    @BeforeEach
    public void setUp() {
        this.companyMapper = new CompanyMapper();
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithCategoryInternalCode() {
        Company dummyCompany = Company.builder()
            .id(ID)
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .category(DUMMY_CATEGORY)
            .build();
        CompanyDTO actualCompanyDTO = this.companyMapper.fromEntityToDto(dummyCompany);
        CompanyDTO expectedCompanyDTO = CompanyDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .categoryInternalCode(CATEGORY_INTERNAL_CODE)
            .build();

        assertEquals(actualCompanyDTO, expectedCompanyDTO,
            "fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithCategoryInternalCode");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithIdNull() {
        Company dummyCompany = Company.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .category(DUMMY_CATEGORY)
            .build();
        CompanyDTO actualCompanyDTO = this.companyMapper.fromEntityToDto(dummyCompany);
        CompanyDTO expectedCompanyDTO = CompanyDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .categoryInternalCode(CATEGORY_INTERNAL_CODE)
            .build();

        assertEquals(actualCompanyDTO, expectedCompanyDTO,
            "fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithIdNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithInternalCodeNull() {
        Company dummyCompany = Company.builder()
            .name(NAME)
            .country(COUNTRY)
            .category(DUMMY_CATEGORY)
            .build();
        CompanyDTO actualCompanyDTO = this.companyMapper.fromEntityToDto(dummyCompany);
        CompanyDTO expectedCompanyDTO = CompanyDTO.builder()
            .name(NAME)
            .country(COUNTRY)
            .categoryInternalCode(CATEGORY_INTERNAL_CODE)
            .build();

        assertEquals(actualCompanyDTO, expectedCompanyDTO,
            "fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithInternalCodeNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithNameNull() {
        Company dummyCompany = Company.builder()
            .internalCode(INTERNAL_CODE)
            .country(COUNTRY)
            .category(DUMMY_CATEGORY)
            .build();
        CompanyDTO actualCompanyDTO = this.companyMapper.fromEntityToDto(dummyCompany);
        CompanyDTO expectedCompanyDTO = CompanyDTO.builder()
            .internalCode(INTERNAL_CODE)
            .country(COUNTRY)
            .categoryInternalCode(CATEGORY_INTERNAL_CODE)
            .build();

        assertEquals(actualCompanyDTO, expectedCompanyDTO,
            "fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithNameNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithCountryNull() {
        Company dummyCompany = Company.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .category(DUMMY_CATEGORY)
            .build();
        CompanyDTO actualCompanyDTO = this.companyMapper.fromEntityToDto(dummyCompany);
        CompanyDTO expectedCompanyDTO = CompanyDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .categoryInternalCode(CATEGORY_INTERNAL_CODE)
            .build();

        assertEquals(actualCompanyDTO, expectedCompanyDTO,
            "fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithCountryNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithCategoryNull() {
        Company dummyCompany = Company.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .build();
        CompanyDTO actualCompanyDTO = this.companyMapper.fromEntityToDto(dummyCompany);
        CompanyDTO expectedCompanyDTO = CompanyDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .build();

        assertEquals(actualCompanyDTO, expectedCompanyDTO,
            "fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithCategoryNull");
    }

    @Test
    public void fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithCategoryInternalCodeNull() {
        Company dummyCompany = Company.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .category(Category.builder()
                .build())
            .build();
        CompanyDTO actualCompanyDTO = this.companyMapper.fromEntityToDto(dummyCompany);
        CompanyDTO expectedCompanyDTO = CompanyDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .build();

        assertEquals(actualCompanyDTO, expectedCompanyDTO,
            "fromEntityToDto_ReturnsCompanyDTOWithSameValuesFromCompany_SendCompanyWithCategoryInternalCodeNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyDTO_SendCompanyDTO() {
        CompanyDTO dummyCompanyDTO = CompanyDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .categoryInternalCode(CATEGORY_INTERNAL_CODE)
            .build();
        Company actualCompany = this.companyMapper.fromDtoToEntity(dummyCompanyDTO);
        Company expectedCompany = Company.builder()
            .id(ZERO_ID)
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .category(Category.builder()
                .internalCode(CATEGORY_INTERNAL_CODE)
                .build())
            .build();

        assertEquals(actualCompany, expectedCompany,
            "fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyDTO_SendCompanyDTO");
    }

    @Test
    public void fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyDTO_SendCompanyDTOWithInternalCodeNull() {
        CompanyDTO dummyCompanyDTO = CompanyDTO.builder()
            .name(NAME)
            .country(COUNTRY)
            .categoryInternalCode(CATEGORY_INTERNAL_CODE)
            .build();
        Company actualCompany = this.companyMapper.fromDtoToEntity(dummyCompanyDTO);
        Company expectedCompany = Company.builder()
            .id(ZERO_ID)
            .name(NAME)
            .country(COUNTRY)
            .category(Category.builder()
                .internalCode(CATEGORY_INTERNAL_CODE)
                .build())
            .build();

        assertEquals(actualCompany, expectedCompany,
            "fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyDTO_SendCompanyDTOWithInternalCodeNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyDTO_SendCompanyDTOWithNameNull() {
        CompanyDTO dummyCompanyDTO = CompanyDTO.builder()
            .internalCode(INTERNAL_CODE)
            .country(COUNTRY)
            .categoryInternalCode(CATEGORY_INTERNAL_CODE)
            .build();
        Company actualCompany = this.companyMapper.fromDtoToEntity(dummyCompanyDTO);
        Company expectedCompany = Company.builder()
            .id(ZERO_ID)
            .internalCode(INTERNAL_CODE)
            .country(COUNTRY)
            .category(Category.builder()
                .internalCode(CATEGORY_INTERNAL_CODE)
                .build())
            .build();

        assertEquals(actualCompany, expectedCompany,
            "fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyDTO_SendCompanyDTOWithNameNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyDTO_SendCompanyDTOWithCountryNull() {
        CompanyDTO dummyCompanyDTO = CompanyDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .categoryInternalCode(CATEGORY_INTERNAL_CODE)
            .build();
        Company actualCompany = this.companyMapper.fromDtoToEntity(dummyCompanyDTO);
        Company expectedCompany = Company.builder()
            .id(ZERO_ID)
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .category(Category.builder()
                .internalCode(CATEGORY_INTERNAL_CODE)
                .build())
            .build();

        assertEquals(actualCompany, expectedCompany,
            "fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyDTO_SendCompanyDTOWithCountryNull");
    }

    @Test
    public void fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyDTO_SendCompanyDTOWithCategoryInternalCodeNull() {
        CompanyDTO dummyCompanyDTO = CompanyDTO.builder()
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .build();
        Company actualCompany = this.companyMapper.fromDtoToEntity(dummyCompanyDTO);
        Company expectedCompany = Company.builder()
            .id(ZERO_ID)
            .internalCode(INTERNAL_CODE)
            .name(NAME)
            .country(COUNTRY)
            .build();

        assertEquals(actualCompany, expectedCompany,
            "fromDtoToEntity_ReturnsCompanyWithSameValuesFromCompanyDTO_SendCompanyDTOWithCategoryInternalCodeNull");
    }

}
