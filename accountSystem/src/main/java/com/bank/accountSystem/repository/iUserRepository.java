package com.bank.accountSystem.repository;

import com.bank.accountSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iUserRepository extends JpaRepository<User,Long> {
}
