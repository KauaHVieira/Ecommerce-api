package com.kauahv.Mini_ECommerceAPI.dto;

import com.kauahv.Mini_ECommerceAPI.enums.UserRole;

public record RegisterDTO(String name, String email, String password, String phone, UserRole role) {
}
