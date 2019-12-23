package com.gfstabile.java.companyrest.entity.category;

import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class CategoryDTOTest {

    @Test
    public void testPojoMethods() {
        assertPojoMethodsFor(CategoryDTO.class).testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR)
            .areWellImplemented();
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues() {
        CategoryDTO dummyCategoryDTO = new CategoryDTO("1", "Name");
        assertEquals("{\"internalCode\": \"1\", \"name\": \"Name\"}", dummyCategoryDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValues_AllFieldsWithValues");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull() {
        CategoryDTO dummyCategoryDTO = new CategoryDTO(null, "Name");
        assertEquals("{\"internalCode\": null, \"name\": \"Name\"}", dummyCategoryDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButInternalCodeNull_InternalCodeNull");
    }

    @Test
    public void toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull() {
        CategoryDTO dummyCategoryDTO = new CategoryDTO("1", null);
        assertEquals("{\"internalCode\": \"1\", \"name\": null}", dummyCategoryDTO.toString(),
            "toString_ReturnsStringRepresentationOfAllFieldWithTheirValuesButNameNull_NameNull");
    }
}
