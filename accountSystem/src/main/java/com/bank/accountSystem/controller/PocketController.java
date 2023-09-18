package com.bank.accountSystem.controller;

import com.bank.accountSystem.dto.PocketRequest;
import com.bank.accountSystem.model.Account;
import com.bank.accountSystem.model.Pocket;
import com.bank.accountSystem.repository.iAccountRepository;
import com.bank.accountSystem.service.PocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pockets")
public class PocketController {
    @Autowired
    private PocketService pocketService;
    @PostMapping
    public ResponseEntity<String> createPocket(@RequestBody PocketRequest pocketRequest) {
        try {
            pocketService.createPocket(pocketRequest);
            return ResponseEntity.ok("Bolsillo creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el bolsillo");
        }
    }
}
