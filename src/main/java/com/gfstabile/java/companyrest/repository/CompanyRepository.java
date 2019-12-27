package com.gfstabile.java.companyrest.repository;

import com.gfstabile.java.companyrest.entity.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Repository bean related to Company entity.
 *
 * @author G. F. Stabile
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Transactional
    @Modifying
    @Query(
        value = "update companies c set c.name = :name, c.country = :country, c.category_id = :category_id where c.internal_code = :internal_code",
        nativeQuery = true)
    void updateCompany(@Param("internal_code") String internalCode, @Param("name") String name,
        @Param("country") String country, @Param("category_id") Long categoryId);

    @Transactional
    void deleteByInternalCode(String internalCode);

    Optional<Company> findByInternalCode(String internalCode);
}
