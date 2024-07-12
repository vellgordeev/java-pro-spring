package ru.flamexander.java.pro.spring.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.flamexander.java.pro.spring.dtos.CreateAccountDto;
import ru.flamexander.java.pro.spring.entities.Account;
import ru.flamexander.java.pro.spring.repositories.AccountsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;

    private static final Logger logger = LoggerFactory.getLogger(AccountsService.class.getName());

    public List<Account> getAllAccounts(Long clientId) {
        return accountsRepository.findAllByClientId(clientId);
    }

    public Optional<Account> getAccountById(Long clientId, Long id) {
        return accountsRepository.findByIdAndClientId(id, clientId);
    }

    public Account createNewAccount(Long clientId, CreateAccountDto createAccountDto) {
        if (createAccountDto.getInitialBalance() == null) {
            throw new RuntimeException("Создаваемый счет не может иметь null баланс");
        }
        Account account = new Account(clientId, createAccountDto.getInitialBalance());
        account = accountsRepository.save(account);
        logger.info("Account id = {} created from {}", account.getId(), createAccountDto);
        return account;
    }
}
