package com.gfstabile.java.companyrest.entity.category;

import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class CategoryTest {

    @Test
    public void testPojoMethods() {
        assertPojoMethodsFor(Category.class).testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR)
            .areWellImplemented();
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues() {
        Category dummyCategory = new Category(1L, "1", "Name");
        assertEquals("{\"id\": 1, \"internalCode\": \"1\", \"name\": \"Name\"}", dummyCategory.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButIdNull_IdNull() {
        Category dummyCategory = new Category(null, "1", "Name");
        assertEquals("{\"id\": null, \"internalCode\": \"1\", \"name\": \"Name\"}", dummyCategory.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButIdNull_IdName");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull() {
        Category dummyCategory = new Category(1L, null, "Name");
        assertEquals("{\"id\": 1, \"internalCode\": null, \"name\": \"Name\"}", dummyCategory.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull() {
        Category dummyCategory = new Category(1L, "1", null);
        assertEquals("{\"id\": 1, \"internalCode\": \"1\", \"name\": null}", dummyCategory.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull");
    }

    @Test
    public void isValid_ReturnsTrue_IdNotNullAndInternalCodeNotBlankAndNameNotBlank() {
        Category dummyCategory = new Category(1L, "1", "Name");
        assertTrue(dummyCategory.isValid(), "isValid_ReturnsTrue_IdNotNullAndInternalCodeNotBlankAndNameNotBlank");
    }

    @Test
    public void isValid_ReturnsFalse_IdNull() {
        Category dummyCategory = new Category(null, "1", "Name");
        assertFalse(dummyCategory.isValid(), "isValid_ReturnsFalse_IdNull");
    }

    @Test
    public void isValid_ReturnsFalse_InternalCodeNull() {
        Category dummyCategory = new Category(1L, null, "Name");
        assertFalse(dummyCategory.isValid(), "isValid_ReturnsFalse_InternalCodeNull");
    }

    @Test
    public void isValid_ReturnsFalse_InternalCodeBlank() {
        Category dummyCategory = new Category(1L, "", "Name");
        assertFalse(dummyCategory.isValid(), "isValid_ReturnsFalse_InternalCodeBlank");
    }

    @Test
    public void isValid_ReturnsFalse_NameNull() {
        Category dummyCategory = new Category(1L, "1", null);
        assertFalse(dummyCategory.isValid(), "isValid_ReturnsFalse_NameNull");
    }

    @Test
    public void isValid_ReturnsFalse_NameBlank() {
        Category dummyCategory = new Category(1L, "1", "");
        assertFalse(dummyCategory.isValid(), "isValid_ReturnsFalse_NameBlank");
    }
}
