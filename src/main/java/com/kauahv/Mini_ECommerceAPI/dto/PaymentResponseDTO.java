package com.kauahv.Mini_ECommerceAPI.dto;

import com.kauahv.Mini_ECommerceAPI.enums.PaymentStatus;
import com.kauahv.Mini_ECommerceAPI.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    private PaymentType paymentType;
    private PaymentStatus status;

}
