package com.gfstabile.java.companyrest.controller.impl;

import com.gfstabile.java.companyrest.controller.IController;
import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.category.CategoryDTO;
import com.gfstabile.java.companyrest.mapper.impl.CategoryMapper;
import com.gfstabile.java.companyrest.service.impl.CategoryService;
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

@RestController
@RequestMapping("/category")
public class CategoryController implements IController<CategoryDTO> {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseEntity<Void> save(@Valid CategoryDTO categoryDTO) {
        this.categoryService.save(this.categoryMapper.fromDtoToEntity(categoryDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@Valid CategoryDTO categoryDTO) {
        this.categoryService.update(this.categoryMapper.fromDtoToEntity(categoryDTO));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteByInternalCode(@NotBlank String internalCode) {
        this.categoryService.deleteByInternalCode(internalCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<CategoryDTO> getByInternalCode(@NotBlank String internalCode) {
        Optional<Category> optionalCategory = this.categoryService.getByInternalCode(internalCode);
        return optionalCategory.map(
            category -> new ResponseEntity<>(this.categoryMapper.fromEntityToDto(category), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> serviceResponse = this.categoryService.getAll();
        return new ResponseEntity<>(serviceResponse.stream()
            .map(this.categoryMapper::fromEntityToDto)
            .collect(Collectors.toList()), HttpStatus.OK);
    }
}
