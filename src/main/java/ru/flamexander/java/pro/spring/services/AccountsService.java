package ru.flamexander.java.pro.spring.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.flamexander.java.pro.spring.dtos.CreateAccountDto;
import ru.flamexander.java.pro.spring.entities.Account;
import ru.flamexander.java.pro.spring.repositories.AccountsRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountsService {
    private static final Logger logger = LoggerFactory.getLogger(AccountsService.class.getName());

    private final AccountsRepository accountsRepository;

    public List<Account> getAllAccounts(String clientId) {
        return accountsRepository.findAllByClientId(clientId);
    }

    public Account getAccountById(String clientId, String id) {
        return accountsRepository.findById(clientId, id).get();
    }

    public Account createNewAccount(String clientId, CreateAccountDto createAccountDto) {
        if (createAccountDto.getInitialBalance() == null) {
            throw new RuntimeException("Создаваемый счет не может иметь null баланс");
        }
        Account account = new Account(clientId, createAccountDto.getInitialBalance());
        account = accountsRepository.createNew(account);
        logger.info("Account id = {} created from {}", account.getId(), createAccountDto);
        return account;
    }
}
