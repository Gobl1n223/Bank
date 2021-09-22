package com.example.creditka.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "CREDIT")
@NoArgsConstructor
public class Credit  {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID ID;

    @Column(name = "CREDIT_LIMIT")
    private BigDecimal creditLimit;

    @Column(name = "PERCENT_RATE")
    private BigDecimal percentRate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK")
    private Bank bank;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "credit")
    private Set<CreditOffer> creditOffers;

    public Credit(Bank bank, BigDecimal creditLimit, BigDecimal percentRate) {
        this.bank = bank;
        this.creditLimit = creditLimit;
        this.percentRate = percentRate;
    }
}
