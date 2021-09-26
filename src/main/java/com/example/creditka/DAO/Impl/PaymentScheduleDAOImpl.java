package com.example.creditka.DAO.Impl;

import com.example.creditka.DAO.PaymentScheduleDAO;
import com.example.creditka.entity.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface PaymentScheduleDAOImpl extends JpaRepository<PaymentSchedule, UUID>, PaymentScheduleDAO {
    @Override
    @Query("SELECT a FROM PaymentSchedule a  where a.creditOffer.client.passportID=:passportID")
    List<PaymentSchedule> findByPassportID(@Param("passportID") String passportID);
}
