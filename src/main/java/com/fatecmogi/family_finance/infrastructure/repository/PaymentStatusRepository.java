package com.fatecmogi.family_finance.infrastructure.repository;

import com.fatecmogi.family_finance.infrastructure.entity.payment_status.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {

}
