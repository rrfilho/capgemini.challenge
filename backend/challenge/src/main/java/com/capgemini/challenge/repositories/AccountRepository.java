package com.capgemini.challenge.repositories;

import com.capgemini.challenge.entities.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
    public Optional<Account> findByAccountNumberAndPassword(String accountNumber, String password);
    
    public Optional<Account> findByAccountNumber(String accountNumber);
}