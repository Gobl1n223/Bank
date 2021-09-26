package com.example.creditka.service;

import com.example.creditka.entity.Credit;
import com.example.creditka.entity.PaymentSchedule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PaymentScheduleService {
    List<PaymentSchedule> getAll();
    PaymentSchedule getOne(UUID uuid);
    List<PaymentSchedule> getByPassportID(String passportID);
    void save(Integer creditTime, BigDecimal creditAmount, String passportID, Credit credit);
    void update(PaymentSchedule paymentSchedule);
    void deleteById(UUID uuid);
    void delete(PaymentSchedule paymentSchedule);
    void saveAll(Set<PaymentSchedule> scheduleList);

}
