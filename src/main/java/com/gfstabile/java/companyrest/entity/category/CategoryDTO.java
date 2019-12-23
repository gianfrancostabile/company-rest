package com.gfstabile.java.companyrest.entity.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * The DTO class related to Category entity
 *
 * @author G. F. Stabile
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
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
}
