package com.fatecmogi.family_finance.family_debt.domain.service;

import com.fatecmogi.family_finance.family_debt.application.dto.response.PaymentStatusDTO;
import com.fatecmogi.family_finance.family_debt.domain.mapper.PaymentStatusMapper;
import com.fatecmogi.family_finance.family_debt.infrastructure.entity.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentStatusService {
    private final PaymentStatusMapper mapper;

    public List<PaymentStatusDTO> findAll() {
        return Arrays.stream(PaymentStatusEnum.values())
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
