package com.kauahv.Mini_ECommerceAPI.payment.dto;

import com.kauahv.Mini_ECommerceAPI.payment.enums.PaymentStatus;
import com.kauahv.Mini_ECommerceAPI.payment.enums.PaymentType;
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
