package ru.flamexander.java.pro.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.flamexander.java.pro.spring.entities.Account;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByClientId(Long clientId);
    Optional<Account> findByIdAndClientId(Long id, Long clientId);
}