package com.gfstabile.java.companyrest.entity.company;

import com.gfstabile.java.companyrest.entity.country.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The DTO related to Company entity
 *
 * @author G. F. Stabile
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
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
        stringBuilder.append(", \"category\": ");
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
}
