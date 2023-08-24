package com.bank.accountSystem.repository;

import com.bank.accountSystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iAccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumber(Long accountNumber);
}
