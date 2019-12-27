package com.gfstabile.java.companyrest.entity.company;

import com.gfstabile.java.companyrest.entity.country.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class CompanyDTOTest {

    private final String internalCode;
    private final String name;
    private final Country country;
    private final String categoryInternalCode;

    public CompanyDTOTest() {
        String code = "1";
        this.internalCode = code;
        this.name = "Name";
        this.country = Country.AR;
        this.categoryInternalCode = code;
    }

    @Test
    public void testPojoMethods() {
        assertPojoMethodsFor(CompanyDTO.class).testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR, Method.EQUALS,
            Method.HASH_CODE)
            .areWellImplemented();
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues() {
        CompanyDTO dummyCompanyDTO = CompanyDTO.builder()
            .internalCode(this.internalCode)
            .name(this.name)
            .country(this.country)
            .categoryInternalCode(this.categoryInternalCode)
            .build();
        Assertions.assertEquals(
            "{\"internalCode\": \"1\", \"name\": \"Name\", \"country\": \"AR\", \"categoryInternalCode\": \"1\"}",
            dummyCompanyDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull() {
        CompanyDTO dummyCompanyDTO = CompanyDTO.builder()
            .name(this.name)
            .country(this.country)
            .categoryInternalCode(this.categoryInternalCode)
            .build();
        Assertions.assertEquals(
            "{\"internalCode\": null, \"name\": \"Name\", \"country\": \"AR\", \"categoryInternalCode\": \"1\"}",
            dummyCompanyDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull() {
        CompanyDTO dummyCompanyDTO = CompanyDTO.builder()
            .internalCode(this.internalCode)
            .country(this.country)
            .categoryInternalCode(this.categoryInternalCode)
            .build();
        Assertions.assertEquals(
            "{\"internalCode\": \"1\", \"name\": null, \"country\": \"AR\", \"categoryInternalCode\": \"1\"}",
            dummyCompanyDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCountryNull_CountryNull() {
        CompanyDTO dummyCompanyDTO = CompanyDTO.builder()
            .internalCode(this.internalCode)
            .name(this.name)
            .categoryInternalCode(this.categoryInternalCode)
            .build();
        Assertions.assertEquals(
            "{\"internalCode\": \"1\", \"name\": \"Name\", \"country\": null, \"categoryInternalCode\": \"1\"}",
            dummyCompanyDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCountryNull_CountryNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCategoryInternalCodeNull_CategoryInternalCodeNull() {
        CompanyDTO dummyCompanyDTO = CompanyDTO.builder()
            .internalCode(this.internalCode)
            .name(this.name)
            .country(this.country)
            .build();
        Assertions.assertEquals(
            "{\"internalCode\": \"1\", \"name\": \"Name\", \"country\": \"AR\", \"categoryInternalCode\": null}",
            dummyCompanyDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCategoryInternalCodeNull_CategoryInternalCodeNull");
    }
}
