package com.gfstabile.java.companyrest.entity.category;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

/**
 * The DTO class related to Category entity
 *
 * @author G. F. Stabile
 */
@Getter
@Setter
@Builder
@JsonDeserialize(builder = CategoryDTO.CategoryDTOBuilder.class)
public class CategoryDTO implements Serializable {

    @NotBlank(message = "InternalCode cannot be null or empty")
    private String internalCode;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;

    @Override
    public String toString() {
        String quotes = "\"";
        final StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append("\"internalCode\": ");
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

        CategoryDTO categoryDTO = (CategoryDTO) object;
        return Objects.equals(internalCode, categoryDTO.internalCode) && Objects.equals(name, categoryDTO.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.nonNull(internalCode) ? internalCode.hashCode() : 0;
        result = 31 * result + (Objects.nonNull(name) ? name.hashCode() : 0);
        return result;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class CategoryDTOBuilder {

    }
}
