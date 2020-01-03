package com.gfstabile.java.companyrest.entity.company;

import com.gfstabile.java.companyrest.entity.category.CategoryDTO;
import com.gfstabile.java.companyrest.entity.country.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;
import pl.pojo.tester.internal.field.AbstractFieldValueChanger;
import pl.pojo.tester.internal.field.DefaultFieldValueChanger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class CompanyResponseDTOTest {
    private final AbstractFieldValueChanger valueChanger =
        DefaultFieldValueChanger.INSTANCE.attachNext(new CompanyResponseDTOTest.CategoryDTOFieldsValuesChanger());
    private CompanyResponseDTO dummyCompanyResponseDTO;

    @BeforeEach
    public void setUp() {
        this.dummyCompanyResponseDTO = CompanyResponseDTO.builder()
            .internalCode("1")
            .name("Name")
            .country(Country.AR)
            .categoryDTO(CategoryDTO.builder()
                .internalCode("1")
                .name("Name")
                .build())
            .build();
    }

    @Test
    public void testPojoMethods() {
        assertPojoMethodsFor(CompanyResponseDTO.class).using(valueChanger)
            .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR, Method.EQUALS, Method.HASH_CODE)
            .areWellImplemented();
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues() {
        assertEquals(
            "{\"internalCode\": \"1\", \"name\": \"Name\", \"country\": \"AR\", \"category\": {\"internalCode\": \"1\", \"name\": \"Name\"}}",
            this.dummyCompanyResponseDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull() {
        this.dummyCompanyResponseDTO.setInternalCode(null);
        assertEquals(
            "{\"internalCode\": null, \"name\": \"Name\", \"country\": \"AR\", \"category\": {\"internalCode\": \"1\", \"name\": \"Name\"}}",
            this.dummyCompanyResponseDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull() {
        this.dummyCompanyResponseDTO.setName(null);
        assertEquals(
            "{\"internalCode\": \"1\", \"name\": null, \"country\": \"AR\", \"category\": {\"internalCode\": \"1\", \"name\": \"Name\"}}",
            this.dummyCompanyResponseDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCountryNull_CountryNull() {
        this.dummyCompanyResponseDTO.setCountry(null);
        assertEquals(
            "{\"internalCode\": \"1\", \"name\": \"Name\", \"country\": null, \"category\": {\"internalCode\": \"1\", \"name\": \"Name\"}}",
            this.dummyCompanyResponseDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCountryNull_CountryNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCategoryInternalCodeNull_CategoryInternalCodeNull() {
        this.dummyCompanyResponseDTO.setCategoryDTO(null);
        assertEquals("{\"internalCode\": \"1\", \"name\": \"Name\", \"country\": \"AR\", \"category\": null}",
            this.dummyCompanyResponseDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCategoryInternalCodeNull_CategoryInternalCodeNull");
    }

    protected class CategoryDTOFieldsValuesChanger extends AbstractFieldValueChanger<CategoryDTO> {

        @Override
        protected CategoryDTO increaseValue(CategoryDTO value, Class<?> type) {
            value.setInternalCode(value.getInternalCode() + "++increased");
            value.setName(value.getName() + "++increased");
            return value;
        }

        @Override
        protected boolean canChange(Class<?> type) {
            return type.equals(CategoryDTO.class);
        }
    }
}