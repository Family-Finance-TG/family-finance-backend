package com.fatecmogi.family_finance.infrastructure.entity.payment_status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment_statuses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_status_id")
    private Long id;

    @Column(name = "payment_status_friendly_name", nullable = false, length = 50)
    private String frindlyName;

    @Column(name = "payment_status_value", nullable = false, length = 50)
    private String value;
}
