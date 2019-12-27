package com.gfstabile.java.companyrest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CompanyRestApplicationTest {

    @Test
    public void contextLoads() {
        CompanyRestApplication.main(new String[] { });
        Assertions.assertTrue(true, "contextLoads");
    }

}
