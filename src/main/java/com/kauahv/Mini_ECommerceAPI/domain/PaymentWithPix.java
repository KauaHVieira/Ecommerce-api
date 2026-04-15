package com.kauahv.Mini_ECommerceAPI.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentWithPix extends Payment{

    private String pixKey;

    @Override
    public String getType() {
        return "PIX";
    }
}
