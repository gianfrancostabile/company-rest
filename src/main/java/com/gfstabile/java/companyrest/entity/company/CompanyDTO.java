package com.gfstabile.java.companyrest.entity.company;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.gfstabile.java.companyrest.entity.country.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * The DTO related to Company entity
 *
 * @author G. F. Stabile
 */
@Getter
@Setter
@Builder
@JsonDeserialize(builder = CompanyDTO.CompanyDTOBuilder.class)
public class CompanyDTO implements Serializable {

    @NotBlank(message = "InternalCode cannot be null or empty")
    private String internalCode;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;

    @NotNull(message = "Country cannot be null")
    private Country country;

    @NotBlank(message = "CategoryInternalCode cannot be null or empty")
    private String categoryInternalCode;

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
        stringBuilder.append(", \"categoryInternalCode\": ");
        if (Objects.nonNull(categoryInternalCode)) {
            stringBuilder.append(quotes)
                .append(categoryInternalCode)
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

        CompanyDTO companyDTO = (CompanyDTO) object;
        return Objects.equals(internalCode, companyDTO.internalCode) && Objects.equals(name, companyDTO.name) &&
            Objects.equals(country, companyDTO.country) &&
            Objects.equals(categoryInternalCode, companyDTO.categoryInternalCode);
    }

    @Override
    public int hashCode() {
        int result = Objects.nonNull(internalCode) ? internalCode.hashCode() : 0;
        result = 31 * result + (Objects.nonNull(name) ? name.hashCode() : 0);
        result = 31 * result + (Objects.nonNull(country) ? country.hashCode() : 0);
        result = 31 * result + (Objects.nonNull(categoryInternalCode) ? categoryInternalCode.hashCode() : 0);
        return result;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class CompanyDTOBuilder {

    }
}
