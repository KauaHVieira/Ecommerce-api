package com.kauahv.Mini_ECommerceAPI.exception;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String path;
    private String error;
    private String message;

}
