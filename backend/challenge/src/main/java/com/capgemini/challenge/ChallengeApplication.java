package com.capgemini.challenge;

import com.capgemini.challenge.entities.Account;
import com.capgemini.challenge.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChallengeApplication{

    public static void main(String[] args){
        
        SpringApplication.run(ChallengeApplication.class, args);
    }
    
    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {
        return args -> {
            repository.save(new Account(1, "Cliente 1", "1111", "1111", 1000.0f));
            repository.save(new Account(2, "Cliente 2", "2222", "2222", 1000.0f));
            repository.save(new Account(3, "Cliente 3", "3333", "3333", 1000.0f));
        };
    }
}