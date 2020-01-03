package com.gfstabile.java.companyrest.entity.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.gfstabile.java.companyrest.entity.category.Category;
import com.gfstabile.java.companyrest.entity.country.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
import java.io.Serializable;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = Company.CompanyBuilder.class)
public class Company implements Serializable {

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

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
            stringBuilder.append(category.toString());
        } else {
            stringBuilder.append("null");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(internalCode, company.internalCode) &&
            Objects.equals(name, company.name) && Objects.equals(country, company.country) &&
            Objects.equals(category, company.category);
    }

    @Override
    public int hashCode() {
        int result = Objects.nonNull(id) ? id.hashCode() : 1;
        result = 31 * result + (Objects.nonNull(internalCode) ? internalCode.hashCode() : 0);
        result = 31 * result + (Objects.nonNull(name) ? name.hashCode() : 0);
        result = 31 * result + (Objects.nonNull(country) ? country.hashCode() : 0);
        result = 31 * result + (Objects.nonNull(category) ? category.hashCode() : 0);
        return result;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class CompanyBuilder {

    }
}