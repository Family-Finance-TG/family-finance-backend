package com.fatecmogi.family_finance.domain.mapper.payment_status;

import com.fatecmogi.family_finance.infrastructure.entity.payment_status.PaymentStatusEnum;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PaymentStatusMapper {

    @ValueMapping(source = "TO_PAY", target = "TO_PAY")
    @ValueMapping(source = "PAID", target = "PAID")
    @ValueMapping(source = "OVERDUE", target = "OVERDUE")
    @ValueMapping(source = "CANCELLED", target = "CANCELLED")
    PaymentStatusEnum map(String value);

    String map(PaymentStatusEnum paymentStatusEnum);
}
