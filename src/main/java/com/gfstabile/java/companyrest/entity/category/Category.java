package com.gfstabile.java.companyrest.entity.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Objects;

/**
 * The Category entity
 *
 * @author G. F. Stabile
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = Category.CategoryBuilder.class)
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String internalCode;

    @Column(nullable = false)
    private String name;

    /**
     * Validates if the category's attributes are valid.
     * <p>
     * It is considered valid when the {@link Category#id} is not null,
     * {@link Category#internalCode} is not blank and
     * {@link Category#name} is not blank
     * </p>
     *
     * @return {@code true} if is valid; otherwise {@code false}
     */
    @JsonIgnore
    @Transient
    public boolean isValid() {
        return Objects.nonNull(id) && Strings.isNotBlank(internalCode) && Strings.isNotBlank(name);
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
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (Objects.isNull(object) || getClass() != object.getClass())
            return false;

        Category category = (Category) object;
        return Objects.equals(id, category.id) && Objects.equals(internalCode, category.internalCode) &&
            Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.nonNull(id) ? id.hashCode() : 1;
        result = 31 * result + (Objects.nonNull(internalCode) ? internalCode.hashCode() : 0);
        result = 31 * result + (Objects.nonNull(name) ? name.hashCode() : 0);
        return result;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class CategoryBuilder {

    }
}
