package com.fatecmogi.family_finance.family_debt.infrastructure.entity;

import com.fatecmogi.family_finance.common.infrastructure.entity.BaseEntity;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "family_debts")
@AttributeOverride(name = "id", column = @Column(name = "family_debt_id"))
@AttributeOverride(name = "createdAt", column = @Column(name = "family_debt_created_at"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "family_debt_updated_at"))
public class FamilyDebt extends BaseEntity {

    @Column(name = "family_debt_title", nullable = false, length = 100)
    private String title;

    @Column(name = "family_debt_description")
    private String description;

    @Column(name = "family_debt_value", nullable = false)
    private float value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_debt_creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_debt_responsible_id")
    private User responsible;

    @Column(name = "family_debt_due_date", nullable = false)
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "family_debt_payment_status", nullable = false)
    private PaymentStatusEnum paymentStatus;
}
