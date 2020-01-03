package com.gfstabile.java.companyrest.entity.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.gfstabile.java.companyrest.entity.category.CategoryDTO;
import com.gfstabile.java.companyrest.entity.country.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@JsonDeserialize(builder = CompanyResponseDTO.CompanyResponseDTOBuilder.class)
public class CompanyResponseDTO implements Serializable {
    private String internalCode;
    private String name;
    private Country country;
    @JsonProperty("category")
    private CategoryDTO categoryDTO;

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
        stringBuilder.append(", \"country\": ");
        if (Objects.nonNull(country)) {
            stringBuilder.append(quotes)
                .append(country.name())
                .append(quotes);
        } else {
            stringBuilder.append("null");
        }
        stringBuilder.append(", \"category\": ")
            .append(categoryDTO);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        CompanyResponseDTO that = (CompanyResponseDTO) object;
        return Objects.equals(internalCode, that.internalCode) && Objects.equals(name, that.name) &&
            country == that.country && Objects.equals(categoryDTO, that.categoryDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalCode, name, country, categoryDTO);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class CompanyResponseDTOBuilder {

    }
}
