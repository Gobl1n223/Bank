package com.example.creditka.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "BANK")
@NoArgsConstructor
public class Bank{

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(length = 45,name = "nameBank",unique = true)
    private String nameBank;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bank",orphanRemoval = true)
    @ToString.Exclude
    private Set<Client> clients;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bank", orphanRemoval = true)
    @ToString.Exclude
    private Set<Credit> credits;

    public Bank(String nameBank) {
        this.nameBank = nameBank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Bank bank = (Bank) o;

        return Objects.equals(id, bank.id);
    }


}
