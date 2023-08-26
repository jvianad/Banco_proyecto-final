package com.bank.accountSystem.controller;


import com.bank.accountSystem.dto.DepositRequest;
import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //OBTENER TODAS LAS CUENTAS
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(accounts);
        }
    }

    //1.1 APERTURA DE CUENTA
    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account account){
        Account savedAccount = accountService.saveAccount(account);
        return ResponseEntity.ok(savedAccount);
    }

    //1.2 TRANFERENCIA A OTRA CUENTA
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<String> realizarDeposito(@PathVariable("accountNumber") String accountNumber, @RequestBody DepositRequest depositRequest) {
        String resultDeposit = accountService.deposit(accountNumber, depositRequest.getAmount());
        if (resultDeposit.equals("Account doesn´t exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultDeposit);
        }
        return ResponseEntity.ok(resultDeposit);
    }


    //1.4 CONSULTAR CUENTA
    @GetMapping("/{accountNumber}")
    public Account findByAccountNumber(@PathVariable String accountNumber){
        return accountService.findByAccountNumber(accountNumber);
    }



    /*@PostMapping("/{account_number}/deposit")
    public ResponseEntity<String> deposit(@PathVariable Long accountNumber, @RequestBody double amount) {
        boolean successfulDeposit = accountService.deposit(accountNumber,amount);
        if (successfulDeposit) {
            return ResponseEntity.ok("Depósito realizado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo realizar el depósito.");
        }
    }*/


}
