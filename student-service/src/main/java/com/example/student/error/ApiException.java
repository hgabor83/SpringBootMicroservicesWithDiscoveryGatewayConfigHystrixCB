package com.example.student.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonIgnoreProperties({"stack", "cause", "suppressed", "stackTrace", "localizedMessage"})
@JsonInclude(NON_NULL)
public class ApiException extends RuntimeException {
    private final String message;
}
