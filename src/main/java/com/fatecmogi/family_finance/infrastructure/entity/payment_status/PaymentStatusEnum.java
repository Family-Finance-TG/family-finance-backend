package com.fatecmogi.family_finance.infrastructure.entity.payment_status;

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
}
