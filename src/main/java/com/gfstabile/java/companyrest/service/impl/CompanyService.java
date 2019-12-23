package com.gfstabile.java.companyrest.service.impl;

import com.gfstabile.java.companyrest.entity.company.Company;
import com.gfstabile.java.companyrest.repository.impl.CompanyRepository;
import com.gfstabile.java.companyrest.service.IService;
import org.apache.logging.log4j.util.Strings;
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
    public void save(Company company) {
        if (Objects.nonNull(company) && company.isValid()) {
            this.companyRepository.save(company);
        }
    }

    @Override
    public void update(Company company) {
        if (Objects.nonNull(company) && company.isValid()) {
            this.companyRepository.updateCompany(company.getInternalCode(), company.getName(), company.getCountry(),
                company.getCategory()
                    .getId());
        }
    }

    @Override
    public void deleteByInternalCode(String internalCode) {
        if (Strings.isNotBlank(internalCode)) {
            this.companyRepository.deleteByInternalCode(internalCode);
        }
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
