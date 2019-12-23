package com.gfstabile.java.companyrest.mapper.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.company.Company;
import com.gfstabile.java.companyrest.entity.company.CompanyDTO;
import com.gfstabile.java.companyrest.mapper.IMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper bean related to Company entity
 *
 * @author G. F. Stabile
 */
@Component
public class CompanyMapper implements IMapper<Company, CompanyDTO> {

    @Override
    public CompanyDTO fromEntityToDto(Company entity) {
        return new CompanyDTO(entity.getInternalCode(), entity.getName(), entity.getCountry(), entity.getCategory()
            .getInternalCode());
    }

    @Override
    public Company fromDtoToEntity(CompanyDTO dto) {
        Company company = new Company(0L, dto.getInternalCode(), dto.getName(), dto.getCountry(), new Category());
        company.getCategory()
            .setInternalCode(dto.getCategoryInternalCode());
        return company;
    }
}
