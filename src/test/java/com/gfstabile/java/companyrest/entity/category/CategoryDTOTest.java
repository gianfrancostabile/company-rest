package com.gfstabile.java.companyrest.entity.category;

import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class CategoryDTOTest {

    private final String internalCode;
    private final String name;

    public CategoryDTOTest() {
        this.internalCode = "1";
        this.name = "Name";
    }

    @Test
    public void testPojoMethods() {
        assertPojoMethodsFor(CategoryDTO.class).testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR, Method.EQUALS,
            Method.HASH_CODE)
            .areWellImplemented();
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues() {
        CategoryDTO dummyCategoryDTO = CategoryDTO.builder()
            .internalCode(this.internalCode)
            .name(this.name)
            .build();
        assertEquals("{\"internalCode\": \"1\", \"name\": \"Name\"}", dummyCategoryDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull() {
        CategoryDTO dummyCategoryDTO = CategoryDTO.builder()
            .name(this.name)
            .build();
        assertEquals("{\"internalCode\": null, \"name\": \"Name\"}", dummyCategoryDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull() {
        CategoryDTO dummyCategoryDTO = CategoryDTO.builder()
            .internalCode(this.internalCode)
            .build();
        assertEquals("{\"internalCode\": \"1\", \"name\": null}", dummyCategoryDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull");
    }
}
