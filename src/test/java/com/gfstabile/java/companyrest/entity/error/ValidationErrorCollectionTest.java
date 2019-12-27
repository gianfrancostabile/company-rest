package com.gfstabile.java.companyrest.entity.error;

import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class ValidationErrorCollectionTest {

    @Test
    public void testPojoMethods() {
        Assertions.assertPojoMethodsFor(ValidationErrorCollection.class)
            .testing(Method.GETTER, Method.CONSTRUCTOR)
            .areWellImplemented();
    }
}
