package com.example.creditka.entity;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Сущность Клиента
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="client")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "client_uuid")
    private UUID uuid;

    @Column(name = "full_name")
    @NotEmpty
    @Size(min = 5, max = 100)
    private String fullName;

    @Column(name = "phone_number")
    @NotEmpty
    @Digits(integer=15, fraction=0)
    private String phoneNumber;

    @Column(name = "email")
    @NotEmpty
    @Email
    private String email;

    @Column(name = "passport_number")
    @NotEmpty
    @Size(min = 5, max = 20)
    private String passportNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CreditOffer> creditOffers;

    public Client(@NonNull String fullName, String phoneNumber, String email, String passportNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passportNumber = passportNumber;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(uuid, client.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
