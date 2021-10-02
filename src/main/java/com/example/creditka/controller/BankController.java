package com.example.creditka.controller;

import com.example.creditka.entity.Bank;
import com.example.creditka.service.impl.BankServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/banks")
public class BankController {

    private final BankServiceImpl bankService;

    public BankController(BankServiceImpl bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/create")
    public String createBank() {
        System.out.println("BankController: createGet------------------------");
        return "banks/bankCreate";
    }

    @PostMapping("/create")
    public String createBank(@RequestParam(name = "name") String name) {
        System.out.println("BankController: createPost------------------------");
        bankService.create(new Bank(name));
        return "redirect:";
    }
    @GetMapping("/{uuid}/credits")
    public String getBankCredit(@PathVariable String uuid, Model model) {
        System.out.println("BankController: getBankCredit------------------------");
        model.addAttribute("bank", bankService.getByUuid(UUID.fromString(uuid)));
        return "banks/bankCredits";
    }

    @GetMapping("/{uuid}/clients")
    public String getBankClient(@PathVariable String uuid, Model model) {
        System.out.println("BankController: getBankClient------------------------");
        model.addAttribute("bank", bankService.getByUuid(UUID.fromString(uuid)));
        return "banks/bankClients";
    }
    @GetMapping
    public String getAll(Model model) {
        System.out.println("BankController: getAll------------------------");
        model.addAttribute("banks", bankService.getAll() );
        return "banks/banks";
    }

    @GetMapping("/{uuid}/update")
    public String updateBank(@PathVariable String uuid, Model model) {
        System.out.println("BankController: updateBankGet------------------------");
        Bank bank = bankService.getByUuid(UUID.fromString(uuid));
        model.addAttribute("bank", bankService.getByUuid(UUID.fromString(uuid)));
        return "banks/bankUpdate";
    }

    @PostMapping("/{uuid}/update")
    public String updateBank(@RequestParam(name = "uuid") String uuid,
                           @RequestParam(name = "name") String name) {
        System.out.println("BankController: updateBankPost------------------------");
        Bank bank = bankService.getByUuid(UUID.fromString(uuid));
        bank.setName(name);
        bankService.create(bank);
        return "redirect:/banks/";
    }

    @GetMapping("/{uuid}/delete")
    public String deleteBank(@PathVariable String uuid, Model model) {
        System.out.println("BankController: deleteBankGet------------------------");
        Bank bank = bankService.getByUuid(UUID.fromString(uuid));
        model.addAttribute("bank", bank);
        return "banks/bankDelete";
    }

    @PostMapping("/{uuid}/delete")
    public String deleteBank(@PathVariable String uuid) {
        System.out.println("BankController: deleteBankPost------------------------");
        Bank bank = bankService.getByUuid(UUID.fromString(uuid));
        bankService.delete(bank);
        return "redirect:/banks/";
    }
}

