package com.capgemini.challenge.controllers;

import com.capgemini.challenge.entities.Account;
import com.capgemini.challenge.repositories.AccountRepository;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController{
    
    private final AccountRepository repository;
    
    @Autowired
    public AccountController(AccountRepository repository){
        
        this.repository = repository;
    }
    
    @PostMapping
    public Map<String, String> getBalance(@RequestBody Map<String, String> request, HttpServletResponse response){
        
        Optional<Account> account = this.repository.findByAccountNumberAndPassword(
            request.getOrDefault("accountNumber", ""),
            request.getOrDefault("password", "")
        );
        
        HashMap<String, String> map = new HashMap<>();
        if( account.isPresent() ){
            
            map.put("name", account.get().getName());
            map.put("balance", Float.toString(account.get().getBalance()));
        }
        else{
            
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return map;
    }
    
    @PutMapping
    public Map<String, String> deposit(@RequestBody Map<String, String> request, HttpServletResponse response){
        
        Optional<Account> account = this.repository.findByAccountNumber(
            request.getOrDefault("accountNumber", "")
        );
        
        HashMap<String, String> map = new HashMap<>();
        if( account.isPresent() ){
            
            Account _account = account.get();
            _account.deposit(Float.parseFloat(request.getOrDefault("amount", "0")));
            this.repository.save(_account);
            
            map.put("name", _account.getName());
            map.put("amount", request.getOrDefault("amount", "0"));
        }
        else{
            
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return map;
    }
    
    @DeleteMapping
    public Map<String, String> withdraw(@RequestBody Map<String, String> request, HttpServletResponse response){
        
        Optional<Account> account = this.repository.findByAccountNumberAndPassword(
            request.getOrDefault("accountNumber", ""),
            request.getOrDefault("password", "")
        );
        
        HashMap<String, String> map = new HashMap<>();
        if( account.isPresent() ){
            
            Account _account = account.get();
            _account.withdraw(Float.parseFloat(request.getOrDefault("amount", "0")));
            this.repository.save(_account);
            
            map.put("name", _account.getName());
            map.put("balance", Float.toString(_account.getBalance()));
        }
        else{
            
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return map;
    }
}