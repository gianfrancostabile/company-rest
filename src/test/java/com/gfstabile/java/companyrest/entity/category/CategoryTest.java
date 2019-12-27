package com.gfstabile.java.companyrest.entity.category;

import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class CategoryTest {

    private final Long id;
    private final String internalCode;
    private final String name;
    private final String blank;

    public CategoryTest() {
        this.id = 1L;
        this.internalCode = "1";
        this.name = "Name";
        this.blank = "";
    }

    @Test
    public void testPojoMethods() {
        assertPojoMethodsFor(Category.class).testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR, Method.EQUALS,
            Method.HASH_CODE)
            .areWellImplemented();
    }

    @Test
    public void isValid_ReturnsTrue_IdNotNullAndInternalCodeNotBlankAndNameNotBlank() {
        Category dummyCategory = Category.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.name)
            .build();
        assertTrue(dummyCategory.isValid(), "isValid_ReturnsTrue_IdNotNullAndInternalCodeNotBlankAndNameNotBlank");
    }

    @Test
    public void isValid_ReturnsFalse_IdNull() {
        Category dummyCategory = Category.builder()
            .internalCode(this.internalCode)
            .name(this.name)
            .build();
        assertFalse(dummyCategory.isValid(), "isValid_ReturnsFalse_IdNull");
    }

    @Test
    public void isValid_ReturnsFalse_InternalCodeNull() {
        Category dummyCategory = Category.builder()
            .id(this.id)
            .name(this.name)
            .build();
        assertFalse(dummyCategory.isValid(), "isValid_ReturnsFalse_InternalCodeNull");
    }

    @Test
    public void isValid_ReturnsFalse_InternalCodeBlank() {
        Category dummyCategory = Category.builder()
            .id(this.id)
            .internalCode(this.blank)
            .name(this.name)
            .build();
        assertFalse(dummyCategory.isValid(), "isValid_ReturnsFalse_InternalCodeBlank");
    }

    @Test
    public void isValid_ReturnsFalse_NameNull() {
        Category dummyCategory = Category.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .build();
        assertFalse(dummyCategory.isValid(), "isValid_ReturnsFalse_NameNull");
    }

    @Test
    public void isValid_ReturnsFalse_NameBlank() {
        Category dummyCategory = Category.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.blank)
            .build();
        assertFalse(dummyCategory.isValid(), "isValid_ReturnsFalse_NameBlank");
    }


    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues() {
        Category dummyCategory = Category.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .name(this.name)
            .build();
        assertEquals("{\"id\": 1, \"internalCode\": \"1\", \"name\": \"Name\"}", dummyCategory.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButIdNull_IdNull() {
        Category dummyCategory = Category.builder()
            .internalCode(this.internalCode)
            .name(this.name)
            .build();
        assertEquals("{\"id\": null, \"internalCode\": \"1\", \"name\": \"Name\"}", dummyCategory.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButIdNull_IdNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull() {
        Category dummyCategory = Category.builder()
            .id(this.id)
            .name(this.name)
            .build();
        assertEquals("{\"id\": 1, \"internalCode\": null, \"name\": \"Name\"}", dummyCategory.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull() {
        Category dummyCategory = Category.builder()
            .id(this.id)
            .internalCode(this.internalCode)
            .build();
        assertEquals("{\"id\": 1, \"internalCode\": \"1\", \"name\": null}", dummyCategory.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull");
    }
}
