package com.fatecmogi.family_finance.family_debt.domain.mapper;

import com.fatecmogi.family_finance.family_debt.application.dto.response.PaymentStatusDTO;
import com.fatecmogi.family_finance.family_debt.infrastructure.entity.PaymentStatusEnum;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PaymentStatusMapper {
    String toString(PaymentStatusEnum paymentStatusEnum);

    default PaymentStatusDTO toDto(PaymentStatusEnum paymentStatusEnum) {
        if (paymentStatusEnum == null) {
            return null;
        }
        return new PaymentStatusDTO(paymentStatusEnum.getValue(), paymentStatusEnum.getFriendlyName());
    }

    default PaymentStatusEnum toEnum(PaymentStatusDTO paymentStatusDTO) {
        if (paymentStatusDTO == null) {
            return null;
        }
        return PaymentStatusEnum.fromDtoValue(paymentStatusDTO);
    }

    default PaymentStatusEnum toEnum(String value) {
        if (value == null) {
            return null;
        }
        return PaymentStatusEnum.fromValue(value);
    }
}
