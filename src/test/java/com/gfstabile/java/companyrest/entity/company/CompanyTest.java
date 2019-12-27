package com.gfstabile.java.companyrest.entity.company;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.country.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;
import pl.pojo.tester.internal.field.AbstractFieldValueChanger;
import pl.pojo.tester.internal.field.DefaultFieldValueChanger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class CompanyTest {

    private final Long id;
    private final String internalCode;
    private final String name;
    private final Country country;
    private final Category dummyCategory;
    private final String blank;

    private final AbstractFieldValueChanger valueChanger =
        DefaultFieldValueChanger.INSTANCE.attachNext(new CategoryFieldsValuesChanger());

    public CompanyTest() {
        this.id = 1L;
        this.internalCode = "1";
        this.name = "Name";
        this.country = Country.AR;
        this.dummyCategory = Category.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.name)
            .build();
        this.blank = "";
    }

    @Test
    public void testPojoMethods() {
        assertPojoMethodsFor(Company.class).using(valueChanger)
            .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR, Method.EQUALS, Method.HASH_CODE)
            .areWellImplemented();
    }

    @Test
    public void isValid_ReturnTrue_IsNotNullAndInternalCodeNotBlankAndNameNotBlankAndCountryNotNullAndCategoryNotNullAndCategoryIsValid() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.name)
            .country(this.country)
            .category(this.dummyCategory)
            .build();
        assertTrue(dummyCompany.isValid(),
            "isValid_ReturnTrue_IsNotNullAndInternalCodeNotBlankAndNameNotBlankAndCountryNotNullAndCategoryNotNullAndCategoryIsValid");
    }

    @Test
    public void isValid_ReturnFalse_IdNull() {
        Company dummyCompany = Company.builder()
            .internalCode(this.internalCode)
            .name(this.name)
            .country(this.country)
            .category(this.dummyCategory)
            .build();
        assertFalse(dummyCompany.isValid(), "isValid_ReturnFalse_IdNull");
    }

    @Test
    public void isValid_ReturnFalse_InternalCodeNull() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .name(this.name)
            .country(this.country)
            .category(this.dummyCategory)
            .build();
        assertFalse(dummyCompany.isValid(), "isValid_ReturnFalse_InternalCodeNull");
    }

    @Test
    public void isValid_ReturnFalse_InternalCodeBlank() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .internalCode(this.blank)
            .name(this.name)
            .country(this.country)
            .category(this.dummyCategory)
            .build();
        assertFalse(dummyCompany.isValid(), "isValid_ReturnFalse_InternalCodeBlank");
    }

    @Test
    public void isValid_ReturnFalse_NameNull() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .country(this.country)
            .category(this.dummyCategory)
            .build();
        assertFalse(dummyCompany.isValid(), "isValid_ReturnFalse_NameNull");
    }

    @Test
    public void isValid_ReturnFalse_NameBlank() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.blank)
            .country(this.country)
            .category(this.dummyCategory)
            .build();
        assertFalse(dummyCompany.isValid(), "isValid_ReturnFalse_NameBlank");
    }

    @Test
    public void isValid_ReturnFalse_CountryNull() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.name)
            .category(this.dummyCategory)
            .build();
        assertFalse(dummyCompany.isValid(), "isValid_ReturnFalse_CountryNull");
    }

    @Test
    public void isValid_ReturnFalse_CategoryNull() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.name)
            .country(this.country)
            .build();
        assertFalse(dummyCompany.isValid(), "isValid_ReturnFalse_CategoryNull");
    }

    @Test
    public void isValid_ReturnFalse_CategoryIsInvalid() {
        Company dummyCompany = new Company(this.id, this.internalCode, this.name, this.country, Category.builder()
            .build());
        assertFalse(dummyCompany.isValid(), "isValid_ReturnFalse_CategoryIsInvalid");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.name)
            .country(this.country)
            .category(this.dummyCategory)
            .build();
        Assertions.assertEquals(
            "{\"id\": 1, \"internalCode\": \"1\", \"name\": \"Name\", \"country\": \"AR\", \"category\": {\"id\": 1, \"internalCode\": \"1\", \"name\": \"Name\"}}",
            dummyCompany.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButIdNull_IdNull() {
        Company dummyCompany = Company.builder()
            .internalCode(this.internalCode)
            .name(this.name)
            .country(this.country)
            .category(this.dummyCategory)
            .build();
        Assertions.assertEquals(
            "{\"id\": null, \"internalCode\": \"1\", \"name\": \"Name\", \"country\": \"AR\", \"category\": {\"id\": 1, \"internalCode\": \"1\", \"name\": \"Name\"}}",
            dummyCompany.toString(), "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButIdNull_IdNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .name(this.name)
            .country(this.country)
            .category(this.dummyCategory)
            .build();
        Assertions.assertEquals(
            "{\"id\": 1, \"internalCode\": null, \"name\": \"Name\", \"country\": \"AR\", \"category\": {\"id\": 1, \"internalCode\": \"1\", \"name\": \"Name\"}}",
            dummyCompany.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .country(this.country)
            .category(this.dummyCategory)
            .build();
        Assertions.assertEquals(
            "{\"id\": 1, \"internalCode\": \"1\", \"name\": null, \"country\": \"AR\", \"category\": {\"id\": 1, \"internalCode\": \"1\", \"name\": \"Name\"}}",
            dummyCompany.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCountryNull_CountryNull() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.name)
            .category(this.dummyCategory)
            .build();
        Assertions.assertEquals(
            "{\"id\": 1, \"internalCode\": \"1\", \"name\": \"Name\", \"country\": null, \"category\": {\"id\": 1, \"internalCode\": \"1\", \"name\": \"Name\"}}",
            dummyCompany.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCountryNull_CountryNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCategoryNull_CategoryNull() {
        Company dummyCompany = Company.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.name)
            .country(this.country)
            .build();
        Assertions.assertEquals(
            "{\"id\": 1, \"internalCode\": \"1\", \"name\": \"Name\", \"country\": \"AR\", \"category\": null}",
            dummyCompany.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButCategoryNull_CategoryNull");
    }

    protected class CategoryFieldsValuesChanger extends AbstractFieldValueChanger<Category> {

        @Override
        protected Category increaseValue(Category value, Class<?> type) {
            value.setId(value.getId() + 1);
            value.setInternalCode(value.getInternalCode() + "++increased");
            value.setName(value.getName() + "++increased");
            return value;
        }

        @Override
        protected boolean canChange(Class<?> type) {
            return type.equals(Category.class);
        }
    }
}
