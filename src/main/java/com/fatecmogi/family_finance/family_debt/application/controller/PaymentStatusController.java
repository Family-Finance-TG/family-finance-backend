package com.fatecmogi.family_finance.family_debt.application.controller;

import com.fatecmogi.family_finance.common.application.util.AppResponseData;
import com.fatecmogi.family_finance.family_debt.domain.service.PaymentStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/payment-statuses")
public class PaymentStatusController {
    private final PaymentStatusService service;

    public PaymentStatusController(PaymentStatusService service) {
        this.service = service;
    }

    @GetMapping
    public AppResponseData findAll(){
        return new AppResponseData(
                HttpStatus.OK,
                service.findAll()
        );
    }
}
