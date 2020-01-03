package com.gfstabile.java.companyrest.entity.company;

import com.gfstabile.java.companyrest.entity.country.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class CompanyRequestDTOTest {

    private CompanyRequestDTO dummyCompanyRequestDTO;

    @BeforeEach
    public void setUp() {
        this.dummyCompanyRequestDTO = CompanyRequestDTO.builder()
            .internalCode("1")
            .name("Name")
            .country(Country.AR)
            .categoryInternalCode("1")
            .build();
    }
    
    @Test
    public void testPojoMethods() {
        assertPojoMethodsFor(CompanyRequestDTO.class).testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR,
            Method.EQUALS, Method.HASH_CODE)
            .areWellImplemented();
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues() {
        assertEquals(
            "{\"internalCode\": \"1\", \"name\": \"Name\", \"country\": \"AR\", \"categoryInternalCode\": \"1\"}",
            this.dummyCompanyRequestDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull() {
        this.dummyCompanyRequestDTO.setInternalCode(null);
        assertEquals(
            "{\"internalCode\": null, \"name\": \"Name\", \"country\": \"AR\", \"categoryInternalCode\": \"1\"}",
            this.dummyCompanyRequestDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull() {
        this.dummyCompanyRequestDTO.setName(null);
        assertEquals("{\"internalCode\": \"1\", \"name\": null, \"country\": \"AR\", \"categoryInternalCode\": \"1\"}",
            this.dummyCompanyRequestDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCountryNull_CountryNull() {
        this.dummyCompanyRequestDTO.setCountry(null);
        assertEquals(
            "{\"internalCode\": \"1\", \"name\": \"Name\", \"country\": null, \"categoryInternalCode\": \"1\"}",
            this.dummyCompanyRequestDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCountryNull_CountryNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCategoryInternalCodeNull_CategoryInternalCodeNull() {
        this.dummyCompanyRequestDTO.setCategoryInternalCode(null);
        assertEquals(
            "{\"internalCode\": \"1\", \"name\": \"Name\", \"country\": \"AR\", \"categoryInternalCode\": null}",
            this.dummyCompanyRequestDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCategoryInternalCodeNull_CategoryInternalCodeNull");
    }
}
