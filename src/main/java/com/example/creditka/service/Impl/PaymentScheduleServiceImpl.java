package com.example.creditka.service.Impl;

import com.example.creditka.DAO.Impl.PaymentScheduleDAOImpl;
import com.example.creditka.entity.Credit;
import com.example.creditka.entity.CreditOffer;
import com.example.creditka.entity.PaymentSchedule;
import com.example.creditka.service.PaymentScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PaymentScheduleServiceImpl implements PaymentScheduleService {
    public static final Logger LOG = LoggerFactory.getLogger(PaymentScheduleServiceImpl.class);

     PaymentScheduleDAOImpl payDao;
     ClientServiceImpl clientService;
     CreditServiceImpl creditService;
     CreditOfferServiceImpl creditOfferService;

    @Autowired
    public void setDao(PaymentScheduleDAOImpl paymentScheduleDAO, ClientServiceImpl clientService,
                                      CreditServiceImpl creditService, CreditOfferServiceImpl creditOfferService) {
        payDao = paymentScheduleDAO;
        this.clientService = clientService;
        this.creditService = creditService;
        this.creditOfferService = creditOfferService;
    }


    @Override
    public List<PaymentSchedule> getAll() {
        return payDao.findAll();
    }

    @Override
    public PaymentSchedule getOne(UUID uuid) {
        return payDao.getById(uuid);
    }

    @Override
    public List<PaymentSchedule> getByPassportID(String passportID) {
        return payDao.findByPassportID(passportID);
    }

    @Override
    public void save(Integer creditTime, BigDecimal creditAmount, String passportID, Credit credit) {
        credit = creditService.getOne(credit.getID());
        CreditOffer creditOffer = new CreditOffer(credit, clientService.getByPassportID(passportID), creditAmount);
        creditOfferService.save(creditOffer);
        BigDecimal percentOfLoan = getPercentOfLoan(creditAmount, credit.getPercentRate(), creditTime);
        BigDecimal paymentPerMonth = divideByMonth(percentOfLoan.add(creditAmount), creditTime);
        BigDecimal paymentPerMonthBody = divideByMonth(creditAmount, creditTime);
        BigDecimal paymentPerMonthPercent = divideByMonth(percentOfLoan, creditTime);

        for (int i = 0; i < creditTime*12; i++) {
            payDao.save(new PaymentSchedule(
                    LocalDate.now().plusMonths(i+1),
                    paymentPerMonth,
                    paymentPerMonthBody,
                    paymentPerMonthPercent,
                    creditOffer));
        }
        LOG.info("Calculations made!", credit.getID(), creditOffer.getID());
    }

    private BigDecimal getPercentOfLoan(BigDecimal creditAmount, BigDecimal percent, Integer creditTime) {
        BigDecimal dividedPercent = percent.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_DOWN);
        BigDecimal percentOfLoan = creditAmount.multiply(dividedPercent);
        return percentOfLoan.multiply(BigDecimal.valueOf(creditTime));
    }


    private BigDecimal divideByMonth(BigDecimal value, Integer creditTime) {
        return value.divide(BigDecimal.valueOf(creditTime*12), 2, RoundingMode.HALF_DOWN);
    }

    @Override
    public void update(PaymentSchedule paymentSchedule) {
         payDao.save(paymentSchedule);
    }

    @Override
    public void deleteById(UUID uuid) {
      payDao.deleteById(uuid);
    }

    @Override
    public void delete(PaymentSchedule paymentSchedule) {
      payDao.delete(paymentSchedule);
    }

    @Override
    public void saveAll(Set<PaymentSchedule> scheduleList) {
        for (PaymentSchedule schedule:
                scheduleList) {
            payDao.save(schedule);
        }
    }
}
