package com.example.creditka.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * График платежей вынесен в отдельную сущность
 * Имеет внешний ключь CreditOffer
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "PAYMENT_SCHEDULE")
public class PaymentSchedule  {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID ID;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CREDIT_OFFER", nullable = false)
    private CreditOffer creditOffer;

    @JoinColumn(name = "PAYMENT_DATE")
    private LocalDate paymentDate;

    @JoinColumn(name = "AMOUNT_OF_PAYMENT")
    private BigDecimal amountOfPayment;

    @JoinColumn(name = "AMOUNT_OF_REPAYMENT_OF_THE_LOAN_BODY")
    private BigDecimal amountOfRepaymentOfTheLoanBody;

    @JoinColumn(name = "AMOUNT_OF_INTEREST_REPAYMENT")
    private BigDecimal amountOfInterestRepayment;



    public PaymentSchedule(LocalDate paymentDate, BigDecimal amountOfPayment, BigDecimal amountOfRepaymentOfTheLoanBody, BigDecimal amountOfInterestRepayment, CreditOffer creditOffer) {
        this.paymentDate = paymentDate;
        this.amountOfPayment = amountOfPayment;
        this.amountOfRepaymentOfTheLoanBody = amountOfRepaymentOfTheLoanBody;
        this.amountOfInterestRepayment = amountOfInterestRepayment;
        this.creditOffer = creditOffer;
    }
}
