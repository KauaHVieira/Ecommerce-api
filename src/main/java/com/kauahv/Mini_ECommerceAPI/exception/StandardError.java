package com.kauahv.Mini_ECommerceAPI.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String path;
    private String error;
    private String message;

}
