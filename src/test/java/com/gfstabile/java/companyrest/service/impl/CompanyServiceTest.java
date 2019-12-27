package com.gfstabile.java.companyrest.service.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.company.Company;
import com.gfstabile.java.companyrest.entity.country.Country;
import com.gfstabile.java.companyrest.exception.AbstractServiceException;
import com.gfstabile.java.companyrest.exception.impl.BlankInternalCodeException;
import com.gfstabile.java.companyrest.exception.impl.DuplicatedInternalCodeException;
import com.gfstabile.java.companyrest.exception.impl.company.CompanyNotFoundException;
import com.gfstabile.java.companyrest.exception.impl.company.InvalidCompanyException;
import com.gfstabile.java.companyrest.exception.impl.company.NullCompanyException;
import com.gfstabile.java.companyrest.repository.CompanyRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    private static Category DUMMY_CATEGORY = Category.builder()
        .id(1L)
        .internalCode("1")
        .name("category-name")
        .build();

    @Test
    public void save_SendValidCompany_DoesNotThrownAbstractServiceException() throws AbstractServiceException {
        Mockito.when(this.companyRepository.save(any(Company.class)))
            .thenReturn(any(Company.class));
        Company dummyCompany = Company.builder()
            .id(0L)
            .internalCode("1")
            .name("Name")
            .country(Country.AR)
            .category(DUMMY_CATEGORY)
            .build();

        this.companyService.save(dummyCompany);
        assertTrue(true, "save_SendValidCompany_DoesNotThrownAbstractServiceException");
    }

    @Test
    public void save_SendExistingCompanyById_ThrownDuplicatedInternalCodeException() {
        Mockito.doThrow(ConstraintViolationException.class)
            .when(this.companyRepository)
            .save(any(Company.class));
        Company dummyCompany = Company.builder()
            .id(1L)
            .internalCode("same-id")
            .name("Same id")
            .country(Country.AR)
            .category(DUMMY_CATEGORY)
            .build();
        assertThrows(DuplicatedInternalCodeException.class, () -> this.companyService.save(dummyCompany),
            "save_SendExistingCompanyById_ThrownDuplicatedInternalCodeException");
    }

    @Test
    public void save_SendExistingCompanyByInternalCode_ThrownDuplicatedInternalCodeException() {
        Mockito.doThrow(ConstraintViolationException.class)
            .when(this.companyRepository)
            .save(any(Company.class));
        Company dummyCompany = Company.builder()
            .id(0L)
            .internalCode("same-internal-code")
            .name("Same internal code")
            .country(Country.AR)
            .category(DUMMY_CATEGORY)
            .build();
        assertThrows(DuplicatedInternalCodeException.class, () -> this.companyService.save(dummyCompany),
            "save_SendExistingCompanyByInternalCode_ThrownDuplicatedInternalCodeException");
    }


    @Test
    public void save_SendNullCompany_ThrownNullCompanyException() {
        assertThrows(NullCompanyException.class, () -> this.companyService.save(null),
            "save_SendInvalidCategory_ThrownInvalidCategoryException");
    }

    @Test
    public void save_SendInvalidCompany_ThrownInvalidCompanyException() {
        Company dummyCompany = Company.builder()
            .id(0L)
            .internalCode("")
            .country(Country.AR)
            .build();

        assertThrows(InvalidCompanyException.class, () -> this.companyService.save(dummyCompany),
            "save_SendInvalidCompany_ThrownInvalidCompanyException");
    }

    @Test
    public void update_SendValidCompany_DoesNotThrownAbstractServiceException() throws AbstractServiceException {
        Company dummyCompany = Company.builder()
            .id(0L)
            .internalCode("existing-internal-code")
            .name("New name")
            .country(Country.ZW)
            .category(DUMMY_CATEGORY)
            .build();
        Mockito.when(this.companyRepository.findByInternalCode(anyString()))
            .thenReturn(Optional.of(dummyCompany));
        Mockito.doNothing()
            .when(this.companyRepository)
            .updateCompany(anyString(), anyString(), anyString(), anyLong());

        this.companyService.update(dummyCompany);
        assertTrue(true, "update_SendValidCompany_DoesNotThrownAbstractServiceException");
    }

    @Test
    public void update_SendNonExistingCompanyByInternalCode_ThrownCompanyNotFoundException() {
        Company dummyCompany = Company.builder()
            .id(0L)
            .internalCode("non-existing-internal-code")
            .name("Non existing internal code")
            .country(Country.ZW)
            .category(DUMMY_CATEGORY)
            .build();
        Mockito.when(this.companyRepository.findByInternalCode(anyString()))
            .thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> this.companyService.update(dummyCompany),
            "update_SendNonExistingCompanyByInternalCode_ThrownCompanyNotFoundException");
    }


    @Test
    public void update_SendNullCompany_ThrownNullCompanyException() {
        assertThrows(NullCompanyException.class, () -> this.companyService.update(null),
            "update_SendNullCompany_ThrownNullCompanyException");
    }

    @Test
    public void update_SendInvalidCompany_ThrownInvalidCompanyException() {
        Company dummyCompany = Company.builder()
            .id(0L)
            .internalCode("")
            .country(Country.ZW)
            .build();

        assertThrows(InvalidCompanyException.class, () -> this.companyService.update(dummyCompany),
            "update_SendInvalidCompany_ThrownInvalidCompanyException");
    }

    @Test
    public void delete_SendValidInternalCode_DoNotThrownException() throws AbstractServiceException {
        Mockito.when(this.companyRepository.findByInternalCode(any(String.class)))
            .thenReturn(Optional.of(Company.builder()
                .build()));
        Mockito.doNothing()
            .when(this.companyRepository)
            .deleteByInternalCode(any(String.class));

        this.companyService.deleteByInternalCode("existing-internal-code");
        assertTrue(true, "delete_SendValidInternalCode_DoNotThrownException");
    }

    @Test
    public void delete_SendNonExistentInternalCode_ThrownCompanyNotFoundException() {
        Mockito.when(this.companyRepository.findByInternalCode(any(String.class)))
            .thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class,
            () -> this.companyService.deleteByInternalCode("non-existent-internal-code"),
            "delete_SendNonExistentInternalCode_ThrownCompanyNotFoundException");
    }

    @Test
    public void delete_SendNullInternalCode_ThrownBlankInternalCodeException() {
        assertThrows(BlankInternalCodeException.class, () -> this.companyService.deleteByInternalCode(null),
            "delete_SendNullInternalCode_ThrownBlankInternalCodeException");
    }

    @Test
    public void delete_SendBlankInternalCode_ThrownBlankInternalCodeException() {
        assertThrows(BlankInternalCodeException.class, () -> this.companyService.deleteByInternalCode(" "),
            "delete_SendBlankInternalCode_ThrownBlankInternalCodeException");
    }

    @Test
    public void getByInternalCode_SendExistingInternalCode_ReturnOptionalWithACompany() {
        String internalCode = "existing-internal-code";
        Company dummyCompany = Company.builder()
            .id(1L)
            .internalCode(internalCode)
            .name("name")
            .country(Country.AR)
            .category(DUMMY_CATEGORY)
            .build();
        Mockito.when(this.companyRepository.findByInternalCode(internalCode))
            .thenReturn(Optional.of(dummyCompany));

        Optional<Company> actualOptionalCompany = this.companyService.getByInternalCode(internalCode);
        if (actualOptionalCompany.isPresent()) {
            assertEquals(dummyCompany, actualOptionalCompany.get(),
                "getByInternalCode_SendExistingInternalCode_ReturnOptionalWithACompany");
        } else {
            fail("getByInternalCode_SendExistingInternalCode_ReturnOptionalWithACompany");
        }
    }

    @Test
    public void getByInternalCode_SendNonExistingInternalCode_ReturnEmptyOptional() {
        String internalCode = "existing-internal-code";
        Mockito.when(this.companyRepository.findByInternalCode(internalCode))
            .thenReturn(Optional.empty());

        Optional<Company> actualOptionalCompany = this.companyService.getByInternalCode(internalCode);
        assertFalse(actualOptionalCompany.isPresent(),
            "getByInternalCode_SendNonExistingInternalCode_ReturnEmptyOptional");
    }

    @Test
    public void getByInternalCode_SendNullInternalCode_ReturnEmptyOptional() {
        Optional<Company> actualOptionalCompany = this.companyService.getByInternalCode(null);
        assertFalse(actualOptionalCompany.isPresent(), "getByInternalCode_SendNullInternalCode_ReturnEmptyOptional");
    }

    @Test
    public void getAll_ReturnListOfThreeCompany() {
        List<Company> expectedCompanyList = new ArrayList<>();
        expectedCompanyList.add(Company.builder()
            .id(1L)
            .internalCode("1")
            .name("1")
            .country(Country.AR)
            .category(DUMMY_CATEGORY)
            .build());
        expectedCompanyList.add(Company.builder()
            .id(2L)
            .internalCode("2")
            .name("2")
            .country(Country.BR)
            .category(DUMMY_CATEGORY)
            .build());
        expectedCompanyList.add(Company.builder()
            .id(3L)
            .internalCode("3")
            .name("3")
            .country(Country.US)
            .category(DUMMY_CATEGORY)
            .build());
        Mockito.when(this.companyRepository.findAll())
            .thenReturn(expectedCompanyList);

        List<Company> actualCompanyList = this.companyService.getAll();
        assertEquals(expectedCompanyList, actualCompanyList, "getAll_ReturnListOfThreeCompany");
    }

    @Test
    public void getAll_ReturnListOfOneCompany() {
        List<Company> expectedCompanyList = new ArrayList<>();
        expectedCompanyList.add(Company.builder()
            .id(1L)
            .internalCode("1")
            .name("1")
            .country(Country.AR)
            .category(DUMMY_CATEGORY)
            .build());
        Mockito.when(this.companyRepository.findAll())
            .thenReturn(expectedCompanyList);

        List<Company> actualCompanyList = this.companyService.getAll();
        assertEquals(expectedCompanyList, actualCompanyList, "getAll_ReturnListOfOneCompany");
    }

    @Test
    public void getAll_ReturnEmpty() {
        List<Company> expectedCompanyList = new ArrayList<>();
        Mockito.when(this.companyRepository.findAll())
            .thenReturn(expectedCompanyList);

        List<Company> actualCompanyList = this.companyService.getAll();
        assertEquals(expectedCompanyList, actualCompanyList, "getAll_ReturnEmpty");
    }
}
