package com.bank.accountSystem.controller;


import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    //1.1 APERTURA DE CUENTA
    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account account){
        Account savedAccount = accountService.saveAccount(account);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{accountNumber}")
                .buildAndExpand(savedAccount.getAccountNumber()).toUri();

        return ResponseEntity.created(ubicacion).body(savedAccount);
    }

    /*//1.2 TRANFERENCIA A OTRA CUENTA
    @PostMapping("/{account_number}/deposit")
    public ResponseEntity<String> realizarDeposito(@PathVariable("account_number") Long account_number, @RequestBody DepositRequest depositRequest) {
        String resultDeposito = accountService.deposit(account_number, depositRequest.getAmount());

        if (resultDeposito.equals("La cuenta no existe")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultDeposito);
        }

        return ResponseEntity.ok(resultDeposito);
    }*/


    //1.4 CONSULTAR CUENTA
    @GetMapping("/{account_number}")
    public Account findByAccountNumber(@PathVariable Long accountNumber){
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
