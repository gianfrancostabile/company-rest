package com.gfstabile.java.companyrest.entity.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ValidationErrorCollection {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    private List<String> errors;

    public ValidationErrorCollection(List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }
}
