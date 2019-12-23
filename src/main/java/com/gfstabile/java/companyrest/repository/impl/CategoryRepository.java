package com.gfstabile.java.companyrest.repository.impl;

import com.gfstabile.java.companyrest.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Repository bean related to Category bean.
 *
 * @author G. F. Stabile
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE categories c SET c.name = :name WHERE c.internal_code = :internal_code", nativeQuery = true)
    void updateCategory(@Param("internal_code") String internalCode, @Param("name") String name);

    @Transactional
    void deleteByInternalCode(String internalCode);

    Optional<Category> findByInternalCode(String internalCode);

    Optional<Long> findCategoryIdByInternalCode(String internalCode);
}
