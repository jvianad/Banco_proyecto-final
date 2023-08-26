package com.bank.accountSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String accountNumber;

    @Column
    private String user;

    @Column
    private double initial_balance;

    private boolean enable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getInitial_balance() {
        return initial_balance;
    }

    public void setInitial_balance(double initial_balance) {
        this.initial_balance = initial_balance;
    }
}
