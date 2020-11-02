package com.capgemini.challenge.entities;

import javax.persistence.*;

@Entity
public class Account{
    
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique=true)
    private String accountNumber;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private float balance;
    
    public Account(){}
    
    public Account(long id, String name, String accountNumber, String password, float balance){
        
        this.id            = id;
        this.name          = name;
        this.accountNumber = accountNumber;
        this.password      = password;
        this.balance       = balance;
    }

    public long getId(){
        
        return id;
    }
    
    public void setId(long id){
        
        this.id = id;
    }
    
    public String getName(){
        
        return name;
    }
    
    public void setName(String name){
        
        this.name = name;
    }
    
    public String getAccountNumber(){
        
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber){
        
        this.accountNumber = accountNumber;
    }
    
    public String getPassword(){
        
        return password;
    }
    
    public void setPassword(String password){
        
        this.password = password;
    }
    
    public float getBalance(){
        
        return balance;
    }
    
    public void setBalance(float balance){
        
        this.balance = balance;
    }
    
    public void deposit(Float amount){
        
        if( amount <= 0 ) throw new IllegalArgumentException("A quantia depositada é inválida.");
        
        this.balance += amount;
    }
    
    public void withdraw(Float amount){
        
        if( amount <= 0 ) throw new IllegalArgumentException("O quantia para saque é inválida.");
        if( amount > this.balance ) throw new IllegalArgumentException("Saldo insuficiente.");
        
        this.balance -= amount;
    }
}