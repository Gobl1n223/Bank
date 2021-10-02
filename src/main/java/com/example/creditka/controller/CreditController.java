package com.example.creditka.controller;

import com.example.creditka.entity.Bank;
import com.example.creditka.entity.Credit;
import com.example.creditka.service.impl.BankServiceImpl;
import com.example.creditka.service.impl.CreditServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/credits")
public class CreditController {

    private final CreditServiceImpl creditService;
    private final BankServiceImpl bankService;

    public CreditController(CreditServiceImpl creditService, BankServiceImpl bankService) {
        this.creditService = creditService;
        this.bankService = bankService;
    }

    @GetMapping
    public String getAllCredits(Model model) {
        model.addAttribute("credits", creditService.getAll());
        return "credits/credits";
    }

    @GetMapping("/create")
    public String createCredit(Model model) {
        model.addAttribute("banks", bankService.getAll());
        return "credits/creditCreate";
    }

    @PostMapping("/create")
    public String appendCredit(@RequestParam(name = "name") String name,
                          @RequestParam(name = "limit") long limit,
                          @RequestParam(name = "interest") float interest,
                          @RequestParam(name = "bank") Bank bank) {
        creditService.create(new Credit(name, limit*100, interest/100, bank));
        return "redirect:";
    }

    @GetMapping("/{uuid}/update")
    public String updateCredit(@PathVariable String uuid, Model model) {
        model.addAttribute("banks", bankService.getAll());
        model.addAttribute("credit", creditService.getByUuid(UUID.fromString(uuid)));
        return "credits/creditUpdate";
    }

    @PostMapping("/{uuid}/update")
    public String updateCredit(@RequestParam(name = "uuid") String uuid,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "limit") long limit,
                             @RequestParam(name = "interest") float interest,
                             @RequestParam(name = "bank") Bank bank) {
        Credit credit = creditService.getByUuid(UUID.fromString(uuid));
        credit.setName(name);
        credit.setLimit(limit * 100);
        credit.setInterestRate(interest / 100);
        credit.setBank(bank);
        creditService.create(credit);
        return "redirect:/credits/";
    }

    @GetMapping("/{uuid}/delete")
    public String deleteCredit(@PathVariable String uuid, Model model) {
        model.addAttribute("credit", creditService.getByUuid(UUID.fromString(uuid)));
        return "credits/creditDelete";
    }

    @PostMapping("/{uuid}/delete")
    public String deleteCredit(@PathVariable String uuid) {
         creditService.delete(creditService.getByUuid(UUID.fromString(uuid)));
        return "redirect:/credits/";
    }


}
