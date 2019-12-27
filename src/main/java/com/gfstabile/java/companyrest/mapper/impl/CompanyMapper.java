package com.gfstabile.java.companyrest.mapper.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.company.Company;
import com.gfstabile.java.companyrest.entity.company.CompanyDTO;
import com.gfstabile.java.companyrest.mapper.IMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Mapper bean related to Company entity
 *
 * @author G. F. Stabile
 */
@Component
public class CompanyMapper implements IMapper<Company, CompanyDTO> {

    @Override
    public CompanyDTO fromEntityToDto(Company entity) {
        CompanyDTO companyDTO = null;
        if (Objects.nonNull(entity)) {
            String categoryInternalCode = Objects.nonNull(entity.getCategory()) ? entity.getCategory()
                .getInternalCode() : null;
            companyDTO = CompanyDTO.builder()
                .internalCode(entity.getInternalCode())
                .name(entity.getName())
                .country(entity.getCountry())
                .categoryInternalCode(categoryInternalCode)
                .build();
        }
        return companyDTO;
    }

    @Override
    public Company fromDtoToEntity(CompanyDTO dto) {
        Company company = null;
        if (Objects.nonNull(dto)) {
            Category category = Objects.nonNull(dto.getCategoryInternalCode()) ? Category.builder()
                .internalCode(dto.getCategoryInternalCode())
                .build() : null;
            company = Company.builder()
                .id(0L)
                .internalCode(dto.getInternalCode())
                .name(dto.getName())
                .country(dto.getCountry())
                .category(category)
                .build();
        }
        return company;
    }
}
