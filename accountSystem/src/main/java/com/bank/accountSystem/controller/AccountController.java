package com.bank.accountSystem.controller;

import com.bank.accountSystem.dto.DepositRequest;
import com.bank.accountSystem.dto.TransferRequest;
import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //1.0 OBTENER TODAS LAS CUENTAS
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accounts);
    }

    //1.1 APERTURA DE CUENTA
    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account account){
        Account savedAccount = accountService.saveAccount(account);
        return ResponseEntity.ok(savedAccount);
    }

    //1.2 DEPOSITAR EN UNA CUENTA
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<String> realizarDeposito(@PathVariable("accountNumber") String accountNumber, @RequestBody DepositRequest depositRequest) {
        String resultDeposit = accountService.deposit(accountNumber, depositRequest.getAmount());
        if (resultDeposit.equals("Account doesn´t exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultDeposit);
        }
        return ResponseEntity.ok(resultDeposit);
    }

    //1.3 TRANSFERENCIA A OTRA CUENTA
    @PostMapping("/transfer")
    public ResponseEntity<String> makeTransfer(@RequestBody TransferRequest transferRequest) {
        String resultTransfer = accountService.makeTransfer(transferRequest);
        if (resultTransfer.equals("Una o ambas cuentas no existen")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultTransfer);
        } else if (resultTransfer.equals("Saldo insuficiente en la cuenta de origen")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultTransfer);
        }
        return ResponseEntity.ok(resultTransfer);
    }

    //1.4 CONSULTAR CUENTA
    @GetMapping("/{accountNumber}")
    public Account findByAccountNumber(@PathVariable String accountNumber){
        return accountService.findByAccountNumber(accountNumber);
    }

}
