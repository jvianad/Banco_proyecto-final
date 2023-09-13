package com.bank.accountSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "pocket")
public class Pocket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String pocketName;
    @Column(unique = true)
    private String pocketNumber;
    @Column
    private double initial_balance;
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    public Pocket() {
    }

    public Pocket(Integer id, String pocketName, double initial_balance) {
        this.id = id;
        this.pocketName = pocketName;
        this.initial_balance = initial_balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPocketName() {
        return pocketName;
    }

    public void setPocketName(String pocketName) {
        this.pocketName = pocketName;
    }

    public double getInitial_balance() {
        return initial_balance;
    }

    public void setInitial_balance(double initial_balance) {
        this.initial_balance = initial_balance;
    }

    public Account getAccountNumber() {
        return account;
    }

    public void setAccountNumber(Account accountNumber) {
        this.account = accountNumber;
    }

    public String getPocketNumber() {
        return pocketNumber;
    }

    public void setPocketNumber(String pocketNumber) {
        this.pocketNumber = pocketNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
