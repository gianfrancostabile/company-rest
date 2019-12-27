package com.gfstabile.java.companyrest.controller.impl;

import com.gfstabile.java.companyrest.controller.IController;
import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.company.Company;
import com.gfstabile.java.companyrest.entity.company.CompanyDTO;
import com.gfstabile.java.companyrest.exception.AbstractServiceException;
import com.gfstabile.java.companyrest.mapper.impl.CompanyMapper;
import com.gfstabile.java.companyrest.service.impl.CategoryService;
import com.gfstabile.java.companyrest.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The controller bean related to Company entity
 *
 * @author G. F. Stabile
 */
@RestController
@RequestMapping("/company")
public class CompanyController implements IController<CompanyDTO> {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public ResponseEntity<Void> save(@Valid CompanyDTO companyDTO) throws AbstractServiceException {
        HttpStatus responseCode = HttpStatus.BAD_REQUEST;
        Optional<Category> optionalCategory =
            this.categoryService.getByInternalCode(companyDTO.getCategoryInternalCode());
        if (optionalCategory.isPresent()) {
            Company company = this.companyMapper.fromDtoToEntity(companyDTO);
            company.setCategory(optionalCategory.get());
            this.companyService.save(company);
            responseCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(responseCode);
    }

    @Override
    public ResponseEntity<Void> update(@Valid CompanyDTO companyDTO) throws AbstractServiceException {
        HttpStatus responseCode = HttpStatus.BAD_REQUEST;
        Optional<Category> optionalCategory =
            this.categoryService.getByInternalCode(companyDTO.getCategoryInternalCode());
        if (optionalCategory.isPresent()) {
            Company company = this.companyMapper.fromDtoToEntity(companyDTO);
            company.setCategory(optionalCategory.get());
            this.companyService.update(company);
            responseCode = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(responseCode);
    }

    @Override
    public ResponseEntity<Void> deleteByInternalCode(@NotBlank String internalCode) throws AbstractServiceException {
        this.companyService.deleteByInternalCode(internalCode);
        HttpStatus responseCode = this.companyService.getByInternalCode(internalCode)
            .isPresent() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(responseCode);
    }

    @Override
    public ResponseEntity<CompanyDTO> getByInternalCode(@NotBlank String internalCode) {
        Optional<Company> optionalCompany = this.companyService.getByInternalCode(internalCode);
        return optionalCompany.map(
            company -> new ResponseEntity<>(this.companyMapper.fromEntityToDto(company), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    public ResponseEntity<List<CompanyDTO>> getAll() {
        List<Company> companyResponse = this.companyService.getAll();
        List<CompanyDTO> companyDTOList = companyResponse.stream()
            .map(this.companyMapper::fromEntityToDto)
            .collect(Collectors.toList());
        HttpStatus responseStatusCode = companyDTOList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(companyDTOList, responseStatusCode);
    }
}
