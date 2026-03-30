package com.kauahv.Mini_ECommerceAPI.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Object id){
        super("Resource not found. Id: " + id);
    }
}
