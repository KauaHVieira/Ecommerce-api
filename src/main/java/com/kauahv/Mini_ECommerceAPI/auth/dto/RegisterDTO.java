package com.kauahv.Mini_ECommerceAPI.auth.dto;

import com.kauahv.Mini_ECommerceAPI.user.enums.UserRole;

public record RegisterDTO(String name, String email, String password, String phone, UserRole role) {
}
