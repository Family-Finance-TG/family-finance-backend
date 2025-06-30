package com.fatecmogi.family_finance.family_debt.infrastructure.entity;

import com.fatecmogi.family_finance.family_debt.application.dto.response.PaymentStatusDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatusEnum {

    TO_PAY("TO_PAY", "To pay"),
    PAID("PAID", "Paid"),
    OVERDUE("OVERDUE", "Overdue"),
    CANCELLED("CANCELLED", "Cancelled");

    private final String value;
    private final String friendlyName;

    public static PaymentStatusEnum fromValue(String value) {
        for (PaymentStatusEnum status : PaymentStatusEnum.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }

    public static PaymentStatusEnum fromDtoValue(PaymentStatusDTO dto) {
        for (PaymentStatusEnum status : PaymentStatusEnum.values()) {
            if (status.value.equals(dto.value())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown payment status value: " + dto.value());
    }
}
