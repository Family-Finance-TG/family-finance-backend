package com.fatecmogi.family_finance.infrastructure.entity.payment_status;

import lombok.Getter;

@Getter
public enum PaymentStatusEnum {

    TO_PAY(new PaymentStatus(1L, "To pay", "TO_PAY")),
    PAID(new PaymentStatus(2L, "Paid", "PAID")),
    OVERDUE(new PaymentStatus(3L, "Overdue", "OVERDUE")),
    CANCELLED(new PaymentStatus(4L, "Cancelled", "CANCELLED"));

    private final PaymentStatus paymentStatus;

    PaymentStatusEnum(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }
}
