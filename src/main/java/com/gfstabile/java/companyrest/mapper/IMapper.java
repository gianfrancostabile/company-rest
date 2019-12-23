package com.gfstabile.java.companyrest.mapper;

/**
 * Interface that defines the mandatory
 * methods for mappers classes.
 *
 * @param <T> the entity class
 * @param <S> the dto class
 * @author G. F. Stabile
 */
public interface IMapper<T, S> {
    /**
     * Maps the {@link T} instance to
     * the {@link S} instance and returns it.
     *
     * @param entity the {@link T} instance to map.
     * @return a {@link S} instance with the {@link T} values
     */
    S fromEntityToDto(T entity);

    /**
     * Maps the {@link S} instance to
     * the {@link T} instance and returns it.
     *
     * @param dto the {@link S} instance to map.
     * @return a {@link T} instance with the {@link S} values
     */
    T fromDtoToEntity(S dto);
}
