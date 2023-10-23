package com.bank.bankaccountservice.web;

import com.bank.bankaccountservice.entities.BankAccount;
import com.bank.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestControler {
    private BankAccountRepository bankAccountRepository;

    public AccountRestControler(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository=bankAccountRepository;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository .findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank account not found"));
    }
    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if ( bankAccount.getBalance() != null ) account.setBalance(bankAccount.getBalance());
        if ( bankAccount.getCreatedAt() != null ) account.setCreatedAt(new Date());
        if ( bankAccount.getCurrency() != null ) account.setCurrency(bankAccount.getCurrency());
        if ( bankAccount.getType() != null ) account.setType(bankAccount.getType());
        return bankAccountRepository.save(account);

    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }

}
