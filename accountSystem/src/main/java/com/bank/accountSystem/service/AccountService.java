package com.bank.accountSystem.service;

import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.repository.iAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private iAccountRepository accountRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account findByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }

    public String deposit(String accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            return "Account doesn´t exist";
        }
        double new_balance = account.getInitial_balance() + amount;
        account.setInitial_balance(new_balance);
        accountRepository.save(account);
        return "Successful deposit";
    }


}
