package com.gfstabile.java.companyrest.mapper;

/**
 * Interface that defines the mandatory
 * methods for mappers classes.
 * <p>
 * Used when a entity has a request and response dto.
 * </p>
 *
 * @param <T> the entity class
 * @param <S> the request dto class
 * @param <R> the response dto class
 * @author G. F. Stabile
 */
public interface IResponseMapper<T, S, R> extends IMapper<T, S> {
    /**
     * Maps the {@link T} instance to
     * the {@link R} instance and returns it.
     *
     * @param entity the {@link T} instance to map.
     * @return a {@link R} instance with the {@link T} values
     */
    R fromEntityToResponseDto(T entity);
}
