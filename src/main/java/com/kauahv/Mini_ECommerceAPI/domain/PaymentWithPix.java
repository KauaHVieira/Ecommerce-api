package com.kauahv.Mini_ECommerceAPI.domain;

import com.kauahv.Mini_ECommerceAPI.enums.PaymentType;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_pix")
@PrimaryKeyJoinColumn(name = "id")
public class PaymentWithPix extends Payment{

    private String pixKey;

    @Override
    public PaymentType getType() {
        return PaymentType.PIX;
    }
}
