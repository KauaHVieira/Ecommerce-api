package com.kauahv.Mini_ECommerceAPI.payment;

import com.kauahv.Mini_ECommerceAPI.payment.dto.PaymentResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMapper {

    public PaymentResponseDTO toDto(Payment obj){
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setPaymentType(obj.getType());
        dto.setStatus(obj.getStatus());

        return dto;
    }

    public List<PaymentResponseDTO> toDtoList(List<Payment> Payments){
        return Payments.stream().map(this::toDto).toList();
    }
}
