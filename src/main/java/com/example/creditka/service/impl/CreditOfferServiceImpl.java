package com.example.creditka.service.impl;

import com.example.creditka.entity.CreditOffer;
import com.example.creditka.entity.Payment;
import com.example.creditka.DAO.CreditOfferDAO;
import com.example.creditka.service.IService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CreditOfferServiceImpl implements IService<CreditOffer> {

    private final CreditOfferDAO creditOfferDAO;

    public CreditOfferServiceImpl(CreditOfferDAO creditOfferDAO) {
        this.creditOfferDAO = creditOfferDAO;
    }

    @Transactional
    public CreditOffer create(CreditOffer creditOffer) { return creditOfferDAO.save(creditOffer);
    }

    @Override
    public List<CreditOffer> getAll() {
        return creditOfferDAO.findAll();
    }

    @Override
    public CreditOffer getByUuid(UUID uuid) {
        return creditOfferDAO.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void delete(CreditOffer creditOffer) {
        creditOfferDAO.delete(creditOffer);
    }

    public void generatePaymentList(CreditOffer creditOffer) {
        float p = creditOffer.getCredit().getInterestRate() / 12;
        long monthlyPayment = (long) Math.round(creditOffer.getAmount() * (p + (p / (Math.pow((1 + p), creditOffer.getCreditTerm()) - 1))));
        long interestTotal = 0;
        long summary = creditOffer.getAmount();
        List<Payment> paymentList = creditOffer.getPaymentList();
        paymentList.clear();
        for (int i = 0; i < creditOffer.getCreditTerm(); i++) {
            long interestAmount = (long) Math.floor(summary * p);
            long principalAmount = monthlyPayment - interestAmount;
            summary -= principalAmount;
            paymentList.add(new Payment(
                    UUID.randomUUID(),
                    creditOffer.getDate().plusMonths(i),
                    monthlyPayment,
                    principalAmount,
                    interestAmount,
                    creditOffer));
            interestTotal += interestAmount;
        }
        creditOffer.setInterestTotal(interestTotal);
        creditOffer.setPaymentList(paymentList);
    }
}
