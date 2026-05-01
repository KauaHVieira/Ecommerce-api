package com.kauahv.Mini_ECommerceAPI.exception;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public void addError(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }
}
