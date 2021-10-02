package com.example.creditka.controller;



import com.example.creditka.entity.Client;
import com.example.creditka.entity.Credit;
import com.example.creditka.entity.CreditOffer;
import com.example.creditka.service.impl.ClientServiceImpl;
import com.example.creditka.service.impl.CreditOfferServiceImpl;
import com.example.creditka.service.impl.CreditServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

@Controller
@RequestMapping("/creditsOffers")
public class CreditOfferController {

    private final ClientServiceImpl clientService;
    private final CreditServiceImpl creditService;
    private final CreditOfferServiceImpl creditOfferService;

    public CreditOfferController(ClientServiceImpl clientService,
                                 CreditServiceImpl creditService,
                                 CreditOfferServiceImpl creditOfferService) {
        this.clientService = clientService;
        this.creditService = creditService;
        this.creditOfferService = creditOfferService;
    }



    @GetMapping("/create")
    public String createCreditOffer(Model model) {
        model.addAttribute("credits", creditService.getAll());
        model.addAttribute("clients", clientService.getAll());
        return "creditOffers/creditOfferCreate";
    }

    @PostMapping("/create")
    public String appendCreditOffer(@RequestParam(name = "client_uuid") Client client,
                                 @RequestParam(name = "credit_uuid") Credit credit,
                                 @RequestParam(name = "summary") long summary,
                                 @RequestParam(name = "credit_term") int creditTerm,
                                 @RequestParam(name = "date")
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstPaymentDate) {
        CreditOffer creditOffer = new CreditOffer(UUID.randomUUID(),
                client, credit, summary*100,
                creditTerm, firstPaymentDate);
        creditOfferService.generatePaymentList(creditOffer);
        credit.getBank().addClient(client);
        UUID uuid = creditOfferService.create(creditOffer).getUuid();
        return "redirect:/creditsOffers/" + uuid;
    }

    @GetMapping
    public String getAllCreditOffers(Model model) {
        model.addAttribute("creditOffers", creditOfferService.getAll() );
        return "creditOffers/creditOffers";
    }

    @GetMapping("/{uuid}")
    public String getCreditOffer(@PathVariable String uuid, Model model) {
        CreditOffer creditOffer = creditOfferService.getByUuid(UUID.fromString(uuid));
        Collections.sort(creditOffer.getPaymentList(), (o2, o1) -> (int) (o1.getInterestAmount() - o2.getInterestAmount()));
        model.addAttribute("creditOffer", creditOffer);
        return "creditOffers/creditOffer";
    }

    @GetMapping("/{uuid}/update")
    public String updateCreditOffer(Model model, @PathVariable String uuid) {
        CreditOffer creditOffer = creditOfferService.getByUuid(UUID.fromString(uuid));
        model.addAttribute("creditOffer", creditOffer);
        model.addAttribute("credits", creditService.getAll());
        model.addAttribute("clients", clientService.getAll());
        return "creditOffers/creditOfferUpdate";
    }

    @PostMapping("/{uuid}/update")
    public String updateCreditOffer(@PathVariable String uuid,
                                    @RequestParam(name = "client_uuid") Client client,
                                    @RequestParam(name = "credit_uuid") Credit credit,
                                    @RequestParam(name = "summary") long summary,
                                    @RequestParam(name = "credit_term") int creditTerm,
                                    @RequestParam(name = "date")
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstPaymentDate) {
        CreditOffer creditOffer = creditOfferService.getByUuid(UUID.fromString(uuid));
        creditOffer.setClient(client);
        creditOffer.setCredit(credit);
        creditOffer.setAmount(summary*100);
        creditOffer.setCreditTerm(creditTerm);
        creditOffer.setDate(firstPaymentDate);
        creditOfferService.generatePaymentList(creditOffer);
        credit.getBank().addClient(client);
        creditOfferService.create(creditOffer);
        return "redirect:/creditsOffers/";
    }

    @GetMapping("/{uuid}/delete")
    public String deleteCreditOffer(@PathVariable String uuid,
                                    Model model) {
        CreditOffer creditOffer = creditOfferService.getByUuid(UUID.fromString(uuid));
        model.addAttribute("creditOffer", creditOffer);
        return "creditOffers/creditOfferDelete";
    }

    @PostMapping("/{uuid}/delete")
    public String deleteCreditOffer(@PathVariable String uuid) {
        CreditOffer creditOffer = creditOfferService.getByUuid(UUID.fromString(uuid));
        creditOfferService.delete(creditOffer);
        return "redirect:/creditsOffers/";
    }


}
