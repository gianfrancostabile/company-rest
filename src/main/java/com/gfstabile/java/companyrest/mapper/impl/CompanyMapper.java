package com.gfstabile.java.companyrest.mapper.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.company.Company;
import com.gfstabile.java.companyrest.entity.company.CompanyRequestDTO;
import com.gfstabile.java.companyrest.entity.company.CompanyResponseDTO;
import com.gfstabile.java.companyrest.mapper.IResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Mapper bean related to Company entity
 *
 * @author G. F. Stabile
 */
@Component
public class CompanyMapper implements IResponseMapper<Company, CompanyRequestDTO, CompanyResponseDTO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CompanyRequestDTO fromEntityToDto(Company entity) {
        CompanyRequestDTO companyRequestDTO = null;
        if (Objects.nonNull(entity)) {
            String categoryInternalCode = Objects.nonNull(entity.getCategory()) ? entity.getCategory()
                .getInternalCode() : null;
            companyRequestDTO = CompanyRequestDTO.builder()
                .internalCode(entity.getInternalCode())
                .name(entity.getName())
                .country(entity.getCountry())
                .categoryInternalCode(categoryInternalCode)
                .build();
        }
        return companyRequestDTO;
    }

    @Override
    public Company fromDtoToEntity(CompanyRequestDTO dto) {
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

    @Override
    public CompanyResponseDTO fromEntityToResponseDto(Company entity) {
        return Objects.nonNull(entity) ? CompanyResponseDTO.builder()
            .internalCode(entity.getInternalCode())
            .name(entity.getName())
            .country(entity.getCountry())
            .categoryDTO(this.categoryMapper.fromEntityToDto(entity.getCategory()))
            .build() : null;
    }
}
