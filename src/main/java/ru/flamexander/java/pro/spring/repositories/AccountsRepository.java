package ru.flamexander.java.pro.spring.repositories;

import ru.flamexander.java.pro.spring.entities.Account;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface AccountsRepository {
    List<Account> findAllByClientId(String clientId);
    Optional<Account> findById(String clientId, String id);
    Account createNew(Account account);
}
