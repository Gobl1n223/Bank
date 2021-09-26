package com.example.creditka.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

/**
 * Сущность кредитного предложения
 * Имеет 2 внешних ключа Credit and Client и лист PaymentSchedule
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "CREDIT_OFFER")
public class CreditOffer  {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID ID;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name="CREDIT",nullable = false)
    private Credit credit;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name="CLIENT",nullable = false)
    private Client client;

    @Column(name = "AMOUNT_OF_PAYMENT")
    private BigDecimal amountOfPayment;

    @OneToMany(mappedBy = "creditOffer", fetch = FetchType.EAGER)
    private Set<PaymentSchedule> paymentSchedule;


    public CreditOffer(Credit credit, Client client, BigDecimal amountOfPayment) {
        this.credit = credit;
        this.client = client;
        this.amountOfPayment = amountOfPayment;
    }


}
