package com.gfstabile.java.companyrest.service.impl;

import com.gfstabile.java.companyrest.entity.company.Company;
import com.gfstabile.java.companyrest.exception.AbstractServiceException;
import com.gfstabile.java.companyrest.exception.impl.BlankInternalCodeException;
import com.gfstabile.java.companyrest.exception.impl.DuplicatedInternalCodeException;
import com.gfstabile.java.companyrest.exception.impl.company.CompanyNotFoundException;
import com.gfstabile.java.companyrest.exception.impl.company.InvalidCompanyException;
import com.gfstabile.java.companyrest.exception.impl.company.NullCompanyException;
import com.gfstabile.java.companyrest.repository.CompanyRepository;
import com.gfstabile.java.companyrest.service.IService;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The service bean related to Company entity
 *
 * @author G. F. Stabile
 */
@Service
public class CompanyService implements IService<Company> {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void save(Company company) throws AbstractServiceException {
        if (Objects.isNull(company)) {
            throw new NullCompanyException();
        } else if (!company.isValid()) {
            throw new InvalidCompanyException();
        }
        try {
            this.companyRepository.save(company);
        } catch (ConstraintViolationException exception) {
            throw new DuplicatedInternalCodeException();
        }
    }

    @Override
    public void update(Company company) throws AbstractServiceException {
        if (Objects.isNull(company)) {
            throw new NullCompanyException();
        } else if (!company.isValid()) {
            throw new InvalidCompanyException();
        } else if (!this.companyRepository.findByInternalCode(company.getInternalCode())
            .isPresent()) {
            throw new CompanyNotFoundException();
        }
        this.companyRepository.updateCompany(company.getInternalCode(), company.getName(), company.getCountry()
            .name(), company.getCategory()
            .getId());
    }

    @Override
    public void deleteByInternalCode(String internalCode) throws AbstractServiceException {
        if (Strings.isBlank(internalCode)) {
            throw new BlankInternalCodeException();
        } else if (!this.companyRepository.findByInternalCode(internalCode)
            .isPresent()) {
            throw new CompanyNotFoundException();
        }
        this.companyRepository.deleteByInternalCode(internalCode);
    }

    @Override
    public Optional<Company> getByInternalCode(String internalCode) {
        Optional<Company> response = Optional.empty();
        if (Strings.isNotBlank(internalCode)) {
            response = this.companyRepository.findByInternalCode(internalCode);
        }
        return response;
    }

    @Override
    public List<Company> getAll() {
        return this.companyRepository.findAll();
    }
}
