package com.kauahv.Mini_ECommerceAPI.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FieldMessage {

    private String fieldName;
    private String message;
}
