package com.bank.accountSystem.service;

import com.bank.accountSystem.dto.PocketRequest;
import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.model.Pocket;
import com.bank.accountSystem.repository.iAccountRepository;
import com.bank.accountSystem.repository.iPocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PocketService {
    @Autowired
    private iAccountRepository accountRepository;
    @Autowired
    private iPocketRepository pocketRepository;

    public void createPocket(PocketRequest pocketRequest) {
        Account account = accountRepository.findByAccountNumber(pocketRequest.getAccountNumber());

        if (account == null) {
            throw new RuntimeException("No se encontró la cuenta principal");
        }

        // Crear el bolsillo y asociarlo a la cuenta principal
        Pocket pocket = new Pocket();
        pocket.setPocketName(pocketRequest.getName());
        pocket.setPocketInitialBalance(pocketRequest.getPocketInitialBalance());
        pocket.setPocketNumber(pocketRequest.getPocketNumber());
        pocket.setAccount(account);

        // Actualizar el saldo de la cuenta principal
        double newBalance = account.getInitial_balance() - pocketRequest.getPocketInitialBalance();
        account.setInitial_balance(newBalance);

        // Guardar el bolsillo y la cuenta principal actualizada en la base de datos
        pocketRepository.save(pocket);
        accountRepository.save(account);
    }
}
