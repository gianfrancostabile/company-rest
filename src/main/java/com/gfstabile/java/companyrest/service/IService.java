package com.gfstabile.java.companyrest.service;

import com.gfstabile.java.companyrest.exception.AbstractServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Interface that defines the mandatory
 * methods for service classes.
 *
 * @param <T> the entity class
 * @author G. F. Stabile
 */
public interface IService<T> {
    /**
     * Inserts the entity into the database
     *
     * @param entity the instance to insert
     */
    void save(T entity) throws AbstractServiceException;

    /**
     * Updates the entity into the database
     *
     * @param entity the instance to update
     */
    void update(T entity) throws AbstractServiceException;

    /**
     * Deletes a row registry from database by the internal code
     *
     * @param internalCode the entity internal code
     */
    void deleteByInternalCode(String internalCode) throws AbstractServiceException;

    /**
     * Returns an optional instance with the entity information
     *
     * @param internalCode the entity internal code
     * @return An optional instance with the entity information;
     * if the internal code does not exists returns an empty optional
     */
    Optional<T> getByInternalCode(String internalCode);

    /**
     * Returns a list with all the saved entities
     *
     * @return A list with all the saved entities;
     * if no entity has been saved returns a empty list
     */
    List<T> getAll();
}
