package com.kauahv.Mini_ECommerceAPI.dto;

import com.kauahv.Mini_ECommerceAPI.domain.Payment;
import com.kauahv.Mini_ECommerceAPI.enums.PaymentStatus;
import com.kauahv.Mini_ECommerceAPI.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {

    private PaymentType paymentType;

}
