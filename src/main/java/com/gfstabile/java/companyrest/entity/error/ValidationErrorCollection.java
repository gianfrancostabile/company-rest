package com.gfstabile.java.companyrest.entity.error;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * Used as rest response if an error occurs, during rest method execution.
 *
 * @author G. F. Stabile
 */
@Getter
public class ValidationErrorCollection implements Serializable {
    private List<String> errors;

    public ValidationErrorCollection(List<String> errors) {
        this.errors = errors;
    }
}
