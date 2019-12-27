package com.gfstabile.java.companyrest.controller;

import com.gfstabile.java.companyrest.exception.AbstractServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Interface that defines the mandatory
 * methods for controller classes.
 *
 * @param <T> the dto class related to the entity
 * @author G. F. Stabile
 */
public interface IController<T> {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> save(@RequestBody @Valid T dto) throws AbstractServiceException;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> update(@RequestBody @Valid T dto) throws AbstractServiceException;

    @DeleteMapping("/{internalCode}")
    ResponseEntity<Void> deleteByInternalCode(@PathVariable @NotBlank String internalCode)
        throws AbstractServiceException;

    @GetMapping(value = "/{internalCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> getByInternalCode(@PathVariable @NotBlank String internalCode);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<T>> getAll();
}
