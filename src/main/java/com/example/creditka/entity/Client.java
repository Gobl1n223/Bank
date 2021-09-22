package com.example.creditka.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "CLIENT")
public class Client  {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID ID;



    @Column(length = 30, nullable = false)
    private String name;


    @Column(length = 15,name = "PHONE",nullable = false)
    private String Phone;

    @Column(length = 10,name = "PASSPORTID",nullable = false)
    private String passportID;



    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK", nullable = false)
    private Bank bank;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    private Set<CreditOffer> creditOffers;


    public Client(String name, String Phone, String passportID, Bank bank) {
        this.name = name;
        this.Phone = Phone;
        this.passportID = passportID;
        this.bank = bank;
    }
}
