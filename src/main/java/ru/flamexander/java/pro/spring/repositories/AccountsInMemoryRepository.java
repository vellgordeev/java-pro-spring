package ru.flamexander.java.pro.spring.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.flamexander.java.pro.spring.entities.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountsInMemoryRepository implements AccountsRepository {
    private List<Account> accounts;

    @PostConstruct
    public void init() {
        this.accounts = new ArrayList<>();
        this.accounts.add(new Account("1", "1", 1000L));
        this.accounts.add(new Account("2", "1", 1000L));
        this.accounts.add(new Account("3", "1", 1000L));
        this.accounts.add(new Account("4", "2", 1000L));
    }

    @Override
    public List<Account> findAllByClientId(String clientId) {
        return accounts.stream().filter(a -> a.getClientId().equals(clientId)).collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findById(String clientId, String id) {
        return accounts.stream().filter(a -> a.getClientId().equals(clientId) && a.getId().equals(id)).findFirst();
    }

    @Override
    public Account createNew(Account account) {
        String newId = String.valueOf(accounts.stream().mapToInt(a -> Integer.parseInt(a.getId())).max().orElse(0) + 1);
        account.setId(newId);
        accounts.add(account);
        return account;
    }
}
