package com.example.creditka.DAO;

import com.example.creditka.entity.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentScheduleDAO extends JpaRepository<PaymentSchedule, UUID> {
    List<PaymentSchedule> findByPassportID(String passportID);
}
