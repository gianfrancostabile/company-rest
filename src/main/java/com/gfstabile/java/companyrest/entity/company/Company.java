package com.gfstabile.java.companyrest.entity.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.country.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * The Company entity
 *
 * @author G. F. Stabile
 */
@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String internalCode;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        String quotes = "\"";
        final StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append("\"id\": ")
            .append(id)
            .append(", \"internalCode\": ");
        if (Objects.nonNull(internalCode)) {
            stringBuilder.append(quotes)
                .append(internalCode)
                .append(quotes);
        } else {
            stringBuilder.append("null");
        }
        stringBuilder.append(", \"name\": ");
        if (Objects.nonNull(name)) {
            stringBuilder.append(quotes)
                .append(name)
                .append(quotes);
        } else {
            stringBuilder.append("null");
        }
        stringBuilder.append(", \"country\": ");
        if (Objects.nonNull(country)) {
            stringBuilder.append(quotes)
                .append(country.name())
                .append(quotes);
        } else {
            stringBuilder.append("null");
        }
        stringBuilder.append(", \"category\": ");
        if (Objects.nonNull(category)) {
            stringBuilder.append(quotes)
                .append(category.toString())
                .append(quotes);
        } else {
            stringBuilder.append("null");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    /**
     * Validates if the company's attributes are valid.
     * <p>
     * It is considered valid when the {@link Company#id} is not null,
     * {@link Company#internalCode} is not blank and
     * {@link Company#name} is not blank and
     * {@link Company#country} is not null and
     * {@link Company#category} is not null and
     * {@link Company#category#isValid()} is true
     * </p>
     *
     * @return {@code true} if is valid; otherwise {@code false}
     */
    @JsonIgnore
    @Transient
    public boolean isValid() {
        return Objects.nonNull(id) && Strings.isNotBlank(internalCode) && Strings.isNotBlank(name) &&
            Objects.nonNull(country) && Objects.nonNull(category) && category.isValid();
    }
}
